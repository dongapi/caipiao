package com.ldm.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.ldm.bean.CaipiaoBean;
import com.ldm.bean.CaipiaoResultBean;
import com.ldm.bean.HistoryBean_3D;
import com.ldm.bean.RecordBean;
import com.ldm.service.CaipiaoResultService;
import com.ldm.util.CP_3D_Util;


public class Caipiao_3D_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Map<String, HistoryBean_3D> records = new HashMap<String, HistoryBean_3D>();
    public Caipiao_3D_Servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String httpUrl = "http://apis.baidu.com/apistore/lottery/lotteryquery";
		String cpCode = request.getParameter("cpCode");
		String httpArg = "lotterycode="+cpCode+"&recordcnt=20";
		String jsonResult = CaipiaoResultService.request(httpUrl, httpArg);
		CaipiaoResultBean crb = JSON.parseObject(jsonResult,CaipiaoResultBean.class);
		if(null != crb && null !=crb.getRetData()){
			List<CaipiaoBean> list=  crb.getRetData().getData();
			if(null != list && list.size()>0){
				System.out.println("查询期数："+list.size());
				Map<String, Object> baiResult = CP_3D_Util.getBai(list) ;
				Map<String, Object> shiResult = CP_3D_Util.getShi(list) ;
				Map<String, Object> geResult = CP_3D_Util.getGe(list) ;
				List<Integer> baiWei = (List<Integer>) baiResult.get("list");
				List<Integer> shiWei = (List<Integer>) shiResult.get("list");
				List<Integer> geWei = (List<Integer>) geResult.get("list");
				//组合数
				int count = baiWei.size()*shiWei.size()*geWei.size();
				//所有组合
				StringBuffer sb = new StringBuffer();
				for(Integer i : baiWei){
					for(Integer j :shiWei){
						for(Integer k : geWei){
							sb.append(""+i+j+k+",");
						}
					}
				}
				request.setAttribute("baiWei", baiWei.toString().replaceAll(" ", ""));
				request.setAttribute("shiWei", shiWei.toString().replaceAll(" ", ""));
				request.setAttribute("geWei", geWei.toString().replaceAll(" ", ""));
				
				//排除说明
				request.setAttribute("baiMsg", baiResult.get("msg"));
				request.setAttribute("shiMsg", shiResult.get("msg"));
				request.setAttribute("geMsg", geResult.get("msg"));
				
				//号码组合
				String zuhe = "";
				if(sb.toString().length()>1){
					zuhe = sb.substring(0,sb.length()-1) ;
				}
				request.setAttribute("zuhe", zuhe);
				request.setAttribute("count", count);
				
				//大底
				Map<String, String> daDiMap = CP_3D_Util.daDi(list);
				request.setAttribute("daDi", daDiMap);
				
				//保存历史记录--开始
				HistoryBean_3D hb3 = new HistoryBean_3D();
				hb3.setBaiCode(baiWei.toString().replaceAll(" ", ""));
				hb3.setShiCode(shiWei.toString().replaceAll(" ", ""));
				hb3.setGeCode(geWei.toString().replaceAll(" ", ""));
				hb3.setBaiMsg((String) baiResult.get("msg"));
				hb3.setShiMsg((String) shiResult.get("msg"));
				hb3.setGeMsg((String) geResult.get("msg"));
				records.put(String.valueOf(Integer.valueOf(list.get(0).getExpect())+1), hb3);
				if(records.size()>21){
					Set<Integer> set = new TreeSet<Integer>();
					for(String a:records.keySet()){
						set.add(Integer.valueOf(a));
					}
					records.remove(set.iterator().next().toString());
				}
				for(CaipiaoBean cb:list){
					//往页面传送的数据中放入历史数据
					cb.setHistoryBean_3D(records.get(cb.getExpect()));
					//预测中了的数字标红
					String baiStr = cb.getOpenCode().substring(0, 1);//开奖百位号码
					String shiStr = cb.getOpenCode().substring(2, 3);//开奖十位号码
					String geStr = cb.getOpenCode().substring(4);//开奖个位号码
					if(null != cb.getHistoryBean_3D()){
						String baiCode = cb.getHistoryBean_3D().getBaiCode();
						String shiCode = cb.getHistoryBean_3D().getShiCode();
						String geCode = cb.getHistoryBean_3D().getGeCode();
						if(baiCode.contains(baiStr)){
							baiCode.replace(baiStr, "<font color='red'>"+baiStr+"</font>");
							cb.getHistoryBean_3D().setBaiCode(baiCode);
						}
						if(shiCode.contains(shiStr)){
							shiCode.replace(shiStr, "<font color='red'>"+shiStr+"</font>");
							cb.getHistoryBean_3D().setShiCode(shiCode);
						}
						if(geCode.contains(geStr)){
							geCode.replace(geStr, "<font color='red'>"+geStr+"</font>");
							cb.getHistoryBean_3D().setGeCode(geCode);;
						}
					}
				}
				
				//保存历史记录--结束
			}
			request.setAttribute("list", list);
		}
		String flg = request.getParameter("flg");
		if(null != flg){
			request.getRequestDispatcher("WEB-INF/3Dmethod.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("WEB-INF/3Dresult.jsp").forward(request, response);
		}
	}

}
