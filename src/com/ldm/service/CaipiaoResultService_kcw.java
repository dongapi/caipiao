package com.ldm.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.ldm.bean.CaipiaoBean;
import com.ldm.bean.CaipiaoResultBean_kcw;

/**
 * 开彩网API
 *
 */
public class CaipiaoResultService_kcw {

	public static String request(String cpCode) {
		//例：  http://f.apiplus.cn/fc3d-20.json
	    BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();
	    String httpUrl = "http://f.apiplus.cn/"+cpCode+"-20.json";

	    try {
	        URL url = new URL(httpUrl);
	        HttpURLConnection connection = (HttpURLConnection) url
	                .openConnection();
	        connection.setRequestMethod("GET");
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
		String cpCode ="fc3d";
		String jsonResult = request(cpCode);
		System.out.println(jsonResult);
		CaipiaoResultBean_kcw crb = JSON.parseObject(jsonResult,CaipiaoResultBean_kcw.class);
		for(CaipiaoBean cb:crb.getData()){
			System.out.print(cb.getExpect()+"  ");
			System.out.print(cb.getOpenCode()+"  ");
			System.out.print(cb.getOpenTime()+"  ");
			System.out.print(cb.getOpenTimeStamp());
			System.out.println("");
		}
		System.out.println(crb.getData().size());
	}


}
