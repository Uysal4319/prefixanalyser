package com.prefixanalyser.service;

import com.prefixanalyser.Analyser;
import com.prefixanalyser.NumberInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/number")
public class AnalyserService {
	
	@GetMapping(path = "/analyze")
	public ResponseEntity<?> getInfo(@RequestBody String number) {
		NumberInfo numberInfo = null;
		try {
			numberInfo = Analyser.getNumberInfo(number);
			
		} catch (Exception e) {
			ResponseEntity.notFound();
		}
		
		return ResponseEntity.ok(new NumberInfoResponse(numberInfo));
	}
}
