package com.prefixanalyser.service;

import com.prefixanalyser.NumberInfo;

import java.io.Serializable;

public class NumberInfoResponse implements Serializable {
	public NumberInfo numberInfo;
	
	public NumberInfoResponse(NumberInfo numberInfo) {
		this.numberInfo = numberInfo;
	}
	
	public NumberInfo getNumberInfo() {
		return numberInfo;
	}
	
	public void setNumberInfo(NumberInfo numberInfo) {
		this.numberInfo = numberInfo;
	}
}
