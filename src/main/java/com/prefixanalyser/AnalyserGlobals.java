package com.prefixanalyser;

import java.util.HashMap;
import java.util.Map;

public class AnalyserGlobals {
	//key:  imsi  value : Countryname___CarrierName
	public static Map<String, String> imsiMap = new HashMap<String, String>();
	//key: Gt Number value:Countryname___CarrierName
	public static Map<String, String> gtMap = new HashMap<String, String>();
}
