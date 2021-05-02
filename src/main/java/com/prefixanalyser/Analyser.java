package com.prefixanalyser;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberToCarrierMapper;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.util.Locale;

public class Analyser {
	
	public static NumberInfo getNumberInfo(String number) {
		
		FileManager.fetchFile();
		PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
		String searchMsisdn;
		if(!number.startsWith("+")){
			searchMsisdn = "+".concat(number);
		}else {
			searchMsisdn = number;
		}
//		searchMsisdn = "+".concat("90525507732");
		
		Phonenumber.PhoneNumber pn = null;
		
		try {
			pn = phoneNumberUtil.parse(searchMsisdn, null);
		} catch (NumberParseException e) {
			e.printStackTrace();
		}
		
		
		int prefix1 = pn.getCountryCode();
		long nationalNumber = pn.getNationalNumber();
		int operatorLength = phoneNumberUtil.getLengthOfNationalDestinationCode(pn);
		String countryName = phoneNumberUtil.getRegionCodeForNumber(pn);
		PhoneNumberUtil.PhoneNumberType numberType = phoneNumberUtil.getNumberType(pn);
		// For find OperatorCode Number
		String carrierNumber = Long.toString(nationalNumber).substring(0, operatorLength);
		
		//System.out.println("1: " + nationalNumber);
		//System.out.println(prefix1);
		//System.out.println("2: " + operatorLength);
		//System.out.println("operator number " + operatorNumber);
		//System.out.println("3: " + phoneNumberUtil.getNumberType(pn));
		//System.out.println("4: " + countryName);
		// System.out.println("4: " + phoneNumberUtil.getSupportedRegions());
		
		Phonenumber.PhoneNumber swissMobileNumber = new Phonenumber.PhoneNumber().setCountryCode(prefix1).setNationalNumber(nationalNumber);
		
		PhoneNumberToCarrierMapper carrierMapper = PhoneNumberToCarrierMapper.getInstance();
		String carrierName = carrierMapper.getNameForNumber(swissMobileNumber, Locale.ENGLISH);
		System.out.println(carrierName);
		
		NumberInfo numberInfo = new NumberInfo();
		
		numberInfo.setCountryName(countryName);
		numberInfo.setCarrierName(carrierName);
		
		if (carrierName == null || carrierName.isEmpty()) {
			
			String gt = String.valueOf(pn.getCountryCode()) + String.valueOf(pn.getNationalNumber());
			
			String gtVal = AnalyserGlobals.gtMap.get(gt.substring(0, 8));
			//gelen gt numberinin valuesuna onceden cacheledgimiz gtMap den ulasmaya calisiyouz.
			if (gtVal == null) {
				gtVal = AnalyserGlobals.gtMap.get(gt.substring(0, 7));
			}
			if (gtVal == null) {
				gtVal = AnalyserGlobals.gtMap.get(gt.substring(0, 6));
			}
			if (gtVal == null) {
				gtVal = AnalyserGlobals.gtMap.get(gt.substring(0, 5));
			}
			if (gtVal == null) {
				gtVal = AnalyserGlobals.gtMap.get(gt.substring(0, 4));
			}
			if (gtVal == null) {
				gtVal = AnalyserGlobals.gtMap.get(gt.substring(0, 3));
			}
			if (gtVal == null) {
				gtVal = AnalyserGlobals.gtMap.get(gt.substring(0, 2));
			}
			if (gtVal == null) {
				gtVal = AnalyserGlobals.gtMap.get(gt.substring(0, 1));
			}
			System.out.println(gtVal);
			String [] gtKeyValues;
			
			if (gtVal != null) {
				gtKeyValues = gtVal.split("___");
				
				numberInfo.setCountryName(gtKeyValues[0]);
				numberInfo.setCarrierName(gtKeyValues[1]);
			}
			
		}
		numberInfo.setPhoneType(numberType.toString());
		
		return numberInfo;
	}
}
