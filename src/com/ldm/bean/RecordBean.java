package com.ldm.bean;

import java.util.List;

public class RecordBean {
	
	private MethodBean methodBean;
	private List<Integer> ONElist;
	public MethodBean getMethodBean() {
		return methodBean;
	}
	public void setMethodBean(MethodBean methodBean) {
		this.methodBean = methodBean;
	}
	public List<Integer> getONElist() {
		return ONElist;
	}
	public void setONElist(List<Integer> oNElist) {
		ONElist = oNElist;
	}

}
