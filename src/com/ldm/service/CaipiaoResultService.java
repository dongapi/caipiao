package com.ldm.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.ldm.bean.CaipiaoBean;
import com.ldm.bean.CaipiaoResultBean;

public class CaipiaoResultService {
	

	/**
	 * @param urlAll
	 *            :请求接口
	 * @param httpArg
	 *            :参数
	 * @return 返回结果
	 */
	public static String request(String httpUrl, String httpArg) {
	    BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();
	    httpUrl = httpUrl + "?" + httpArg;

	    try {
	        URL url = new URL(httpUrl);
	        HttpURLConnection connection = (HttpURLConnection) url
	                .openConnection();
	        connection.setRequestMethod("GET");
	        // 填入apikey到HTTP header
	        connection.setRequestProperty("apikey",  "b8b359a4d07e5c565577748d0e596547");
	        connection.connect();
	        InputStream is = connection.getInputStream();
	        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        String strRead = null;
	        while ((strRead = reader.readLine()) != null) {
	            sbf.append(strRead);
	            sbf.append("\r\n");
	        }
	        reader.close();
	        result = sbf.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}
	
	public static void main(String[] args) {
		String httpUrl = "http://apis.baidu.com/apistore/lottery/lotteryquery";
		String httpArg = "lotterycode=fc3d&recordcnt=20";
		String jsonResult = request(httpUrl, httpArg);
		CaipiaoResultBean crb = JSON.parseObject(jsonResult,CaipiaoResultBean.class);
		for(CaipiaoBean cb:crb.getRetData().getData()){
			System.out.print(cb.getExpect()+"  ");
			System.out.print(cb.getOpenTime()+"  ");
			System.out.print(cb.getOpenCode());
			System.out.println("");
		}
		System.out.println(crb.getRetData().getData().size());
	}


}
