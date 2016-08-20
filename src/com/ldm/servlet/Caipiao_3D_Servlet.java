package com.ldm.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.ldm.bean.CaipiaoBean;
import com.ldm.bean.CaipiaoResultBean;
import com.ldm.bean.RecordBean;
import com.ldm.service.CaipiaoResultService;
import com.ldm.util.CP_3D_Util;


public class Caipiao_3D_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Map<String, RecordBean> records = new HashMap<String, RecordBean>();
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
			String zuhe = "";
			if(sb.toString().length()>1){
				zuhe = sb.substring(0,sb.length()-1) ;
			}
			request.setAttribute("zuhe", zuhe);
			request.setAttribute("count", count);
		}
		request.setAttribute("list", list);
		request.getRequestDispatcher("WEB-INF/3Dresult.jsp").forward(request, response);
	}

}
