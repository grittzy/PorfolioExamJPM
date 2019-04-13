package com.portfolio.vo;

public class Fund {
	
	private String fundParentId;
	
	private String fundId;
	
	private Double value;

	public String getFundParentId() {
		return fundParentId;
	}

	public void setFundParentId(String fundParentId) {
		this.fundParentId = fundParentId;
	}

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}
