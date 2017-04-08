package com.ldm.bean;

import java.util.List;

/**
 * 开彩网_开奖数据模型
 *
 */
public class CaipiaoResultBean_kcw {
	
	private String rows;
	private String code;
	private String info;
	private List<CaipiaoBean> data;
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public List<CaipiaoBean> getData() {
		return data;
	}
	public void setData(List<CaipiaoBean> data) {
		this.data = data;
	}
	

}
