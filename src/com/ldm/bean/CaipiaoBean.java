package com.ldm.bean;

import java.util.Map;

/**
 * @author Administrator
 *
 */
public class CaipiaoBean {
	
	private String expect;
	private String openCode;
	private String openTime;
	private String openTimeStamp;
	private RecordBean recordBean;
	private HistoryBean_3D historyBean_3D;
	private String liangMa;
	private String pclm;//排除两码
	private String tjhm;//推荐号码
	private int tjhmCount;
	private Map<String, Object> baiResult;//单选百位
	private Map<String, Object> shiResult;//单选十位
	private Map<String, Object> geResult;//单选个位
	private String dxtjhm;//单选推荐号码
	private int dxCount;//单选推荐号码总数
	private String zwhm;//詹 4+6方法出的单选号码
	private String hbhm;//金+詹 合并后的单选号码
	private String hbCount;//合并后的单选号码总数
	
	public String getZwhm() {
		return zwhm;
	}
	public void setZwhm(String zwhm) {
		this.zwhm = zwhm;
	}
	public String getHbhm() {
		return hbhm;
	}
	public void setHbhm(String hbhm) {
		this.hbhm = hbhm;
	}
	public String getHbCount() {
		return hbCount;
	}
	public void setHbCount(String hbCount) {
		this.hbCount = hbCount;
	}
	public String getDxtjhm() {
		return dxtjhm;
	}
	public void setDxtjhm(String dxtjhm) {
		this.dxtjhm = dxtjhm;
	}
	public int getDxCount() {
		return dxCount;
	}
	public void setDxCount(int dxCount) {
		this.dxCount = dxCount;
	}
	public Map<String, Object> getBaiResult() {
		return baiResult;
	}
	public void setBaiResult(Map<String, Object> baiResult) {
		this.baiResult = baiResult;
	}
	public Map<String, Object> getShiResult() {
		return shiResult;
	}
	public void setShiResult(Map<String, Object> shiResult) {
		this.shiResult = shiResult;
	}
	public Map<String, Object> getGeResult() {
		return geResult;
	}
	public void setGeResult(Map<String, Object> geResult) {
		this.geResult = geResult;
	}
	public int getTjhmCount() {
		return tjhmCount;
	}
	public void setTjhmCount(int tjhmCount) {
		this.tjhmCount = tjhmCount;
	}
	public String getTjhm() {
		return tjhm;
	}
	public void setTjhm(String tjhm) {
		this.tjhm = tjhm;
	}
	public String getPclm() {
		return pclm;
	}
	public void setPclm(String pclm) {
		this.pclm = pclm;
	}
	public String getLiangMa() {
		return liangMa;
	}
	public void setLiangMa(String liangMa) {
		this.liangMa = liangMa;
	}
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
