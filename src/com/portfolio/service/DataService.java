package com.portfolio.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.portfolio.vo.Fund;

/**
 * This class will handle the retrieval of data from the input file
 * @author Joed Cueto
 *
 */
public class DataService {
	
	public List<Fund> getFundData(String file) throws Exception {
		//String file = "C:\\test\\input.txt";
		
		List<Fund> funds = new ArrayList<Fund>();
	    BufferedReader reader = new BufferedReader(new FileReader(file));
	    String currentLine = reader.readLine();
	    while (currentLine != null) {
	    	Fund fund = getFundByLine(currentLine);
	        funds.add(fund);
	        currentLine = reader.readLine();
	    }
	     
	    reader.close();
	    
	    return funds;
	}
	
	private Fund getFundByLine(String line) throws Exception {
		Fund fund = new Fund();
		
		String[] values = line.split(",");
		
		if(values.length == 3) {
			fund.setFundParentId(values[0].trim());
			fund.setFundId(values[1].trim());
			
			Double marketValue;
			try {
				marketValue = Double.parseDouble(values[2].trim());
			} catch (NumberFormatException e) {
				throw new Exception("Invalid format for market value provided");
			}
			
			fund.setValue(marketValue);
		} else {
			throw new Exception("Invalid line format");
		}
	
		return fund;
		
	}

}
