package com.prefixanalyser;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileManager {
	
	public static void fetchFile(){
		
		String csvFile = "MCCMNCs_v2.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		
		
		try {
			
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				
				String[] imsiString = line.split(cvsSplitBy);
				//System.out.println(imsiString[0]);
				String value = imsiString[imsiString.length - 1] + "___" + imsiString[imsiString.length - 2];// Dosyada '_' tek alt cizgili ayrac olmasi durumuna karsin '___' üc alt cizgili ayrac  kullanilmistir.
				//key : imsi  value : countryName___carrierName
				AnalyserGlobals.imsiMap.put(imsiString[0], value);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(AnalyserGlobals.imsiMap.size());
		
		String csvFileForGt = "Gt_List.csv";
		BufferedReader brGt = null;
		String lineGt = "";
		String cvsSplitByGt = ",";
		
		
		try {
			
			brGt = new BufferedReader(new FileReader( csvFileForGt));
			while ((lineGt = brGt.readLine()) != null) {
				
				String[] gtString = lineGt.split(cvsSplitByGt);
				//System.out.println(imsiString[0]);
				
				String key = gtString[gtString.length - 2] + gtString[gtString.length - 1];
				String value = gtString[1] + "___" + gtString[0]; // Dosyada '_' tek alt cizgili ayrac olmasi durumuna karsin '___' uc alt cizgili ayrac  kullanilmistir.
				//key : Gt number  value : countryName__carrierName
				AnalyserGlobals.gtMap.put(key, value);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (brGt != null) {
				try {
					brGt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
