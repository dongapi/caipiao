package com.ldm.bean;

public class CaipiaoBean {
	
	private String expect;
	private String openCode;
	private String openTime;
	private String openTimeStamp;
	private RecordBean recordBean;
	private HistoryBean_3D historyBean_3D;
	
	public HistoryBean_3D getHistoryBean_3D() {
		return historyBean_3D;
	}
	public void setHistoryBean_3D(HistoryBean_3D historyBean_3D) {
		this.historyBean_3D = historyBean_3D;
	}
	public RecordBean getRecordBean() {
		return recordBean;
	}
	public void setRecordBean(RecordBean recordBean) {
		this.recordBean = recordBean;
	}
	public String getExpect() {
		return expect;
	}
	public void setExpect(String expect) {
		this.expect = expect;
	}
	public String getOpenCode() {
		return openCode;
	}
	public void setOpenCode(String openCode) {
		this.openCode = openCode;
	}
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public String getOpenTimeStamp() {
		return openTimeStamp;
	}
	public void setOpenTimeStamp(String openTimeStamp) {
		this.openTimeStamp = openTimeStamp;
	}

}
