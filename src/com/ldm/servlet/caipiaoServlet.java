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
import com.ldm.bean.MethodBean;
import com.ldm.bean.RecordBean;
import com.ldm.service.CaipiaoResultService;
import com.ldm.util.CP_bj11x5;


public class caipiaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Map<String, RecordBean> records_bj = new HashMap<String, RecordBean>();
    private static Map<String, RecordBean> records_sh = new HashMap<String, RecordBean>();
    public caipiaoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String httpUrl = "http://apis.baidu.com/apistore/lottery/lotteryquery";
		String cpCode = request.getParameter("cpCode");
		if("bj11x5".equals(cpCode)){
			request.setAttribute("area", "北京");
		}else if("sh11x5".equals(cpCode)){
			request.setAttribute("area", "上海");
		}
		String httpArg = "lotterycode="+cpCode+"&recordcnt=20";
		String jsonResult = CaipiaoResultService.request(httpUrl, httpArg);
		CaipiaoResultBean crb = JSON.parseObject(jsonResult,CaipiaoResultBean.class);
		List<CaipiaoBean> list=  crb.getRetData().getData();
		if(list!=null && list.size()>0){
			Map<String, Object> map =  CP_bj11x5.getONE(list);
			@SuppressWarnings("unchecked")
			List<Integer> ONElist = (List<Integer>) map.get("list");
			MethodBean mb = (MethodBean) map.get("bean");
			System.out.println(ONElist.toString());
			RecordBean rb = new RecordBean();
			rb.setONElist(ONElist);
			rb.setMethodBean(mb);
			if("bj11x5".equals(cpCode)){
				records_bj.put(String.valueOf(Integer.valueOf(list.get(0).getExpect())+1), rb);
				if(records_bj.size()>21){
					Set<Integer> set = new TreeSet<Integer>();
					 for(String a:records_bj.keySet()){
						 set.add(Integer.valueOf(a));
					 }
					records_bj.remove(set.iterator().next().toString());
				}
				System.out.println(records_bj);
				for(CaipiaoBean cb:list){
					cb.setRecordBean(records_bj.get(cb.getExpect()));
					String tmpONEstr = cb.getOpenCode().substring(0, 2);//开奖首位号码
					int tmpONE = Integer.valueOf(tmpONEstr);
					if(null != cb.getRecordBean()){
						List<Integer> tmplist = cb.getRecordBean().getONElist();
						if(tmplist.contains(tmpONE)){
							String redOpenCode ="<font color='red'>"+tmpONEstr+"</font>"+cb.getOpenCode().substring(2);
							cb.setOpenCode(redOpenCode);
						}
					}
				}
			}else if("sh11x5".equals(cpCode)){
				records_sh.put(String.valueOf(Integer.valueOf(list.get(0).getExpect())+1), rb);
				if(records_sh.size()>21){
					Set<Integer> set = new TreeSet<Integer>();
					 for(String a:records_sh.keySet()){
						 set.add(Integer.valueOf(a));
					 }
					 records_sh.remove(set.iterator().next().toString());
				}
				System.out.println(records_sh);
				for(CaipiaoBean cb:list){
					cb.setRecordBean(records_sh.get(cb.getExpect()));
					String tmpONEstr = cb.getOpenCode().substring(0, 2);//开奖首位号码
					int tmpONE = Integer.valueOf(tmpONEstr);
					if(null != cb.getRecordBean()){
						List<Integer> tmplist = cb.getRecordBean().getONElist();
						if(tmplist.contains(tmpONE)){
							String redOpenCode ="<font color='red'>"+tmpONEstr+"</font>"+cb.getOpenCode().substring(2);
							cb.setOpenCode(redOpenCode);
						}
					}
				}
			}
			
			System.out.println("list长度："+list.size());
			request.setAttribute("numONE", ONElist.toString());
			request.setAttribute("bean", mb);
		}
		request.setAttribute("list", list);
		request.getRequestDispatcher("WEB-INF/CPresult.jsp").forward(request, response);
	}

}
