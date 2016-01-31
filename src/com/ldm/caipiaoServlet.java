package com.ldm;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.ldm.util.CP_bj11x5;


public class caipiaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Map<String, CaipiaoBean> records = new HashMap<String, CaipiaoBean>();
    public caipiaoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String httpUrl = "http://apis.baidu.com/apistore/lottery/lotteryquery";
		String httpArg = "lotterycode=bj11x5&recordcnt=20";
		String jsonResult = CaipiaoResultService.request(httpUrl, httpArg);
		CaipiaoResultBean crb = JSON.parseObject(jsonResult,CaipiaoResultBean.class);
		List<CaipiaoBean> list=  crb.getRetData().getData();
		if(list!=null && list.size()>0){
			Map<String, Object> map =  CP_bj11x5.getONE(list);
			List<Integer> ONElist = (List<Integer>) map.get("list");
			MethodBean mb = (MethodBean) map.get("bean");
			System.out.println(ONElist.toString());
			CaipiaoBean cb = new CaipiaoBean();
			cb.setONElist(ONElist.toString());
			cb.setMethodBean(mb);
			records.put(list.get(0).getExpect(), cb);
			if(records.size()>15){
				records.remove(String.valueOf((Integer.valueOf(list.get(0).getExpect())-15)));
			}
			request.setAttribute("numONE", ONElist.toString());
			request.setAttribute("bean", mb);
			request.setAttribute("records", records);
		}
		request.setAttribute("list", list);
		request.getRequestDispatcher("WEB-INF/CPresult.jsp").forward(request, response);
	}

}
