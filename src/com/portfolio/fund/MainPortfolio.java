package com.portfolio.fund;

import java.util.List;
import java.util.Map;

import com.portfolio.service.DataService;
import com.portfolio.service.FundService;
import com.portfolio.util.OutputUtil;
import com.portfolio.vo.Fund;
import com.portfolio.vo.FundWeight;

public class MainPortfolio {

	public static void main(String[] args) {
		
		if(args.length == 0) {
			OutputUtil.output("File not supplied on the command line. Please try again");
		} else {
			DataService dataService = new DataService();
			FundService fundService = new FundService();
			try {
				
				String file = args[0];
				List<Fund> funds = dataService.getFundData(file);			
							
				Map<String, List<Fund>> groupedFunds = fundService.manageFundsByParentId(funds);
				
				Map<String, Double> parentAggregateFundValue =  fundService.generateParentFundAggregateValue(groupedFunds);
				
				List<FundWeight> weights = fundService.getFundWeights(groupedFunds, parentAggregateFundValue);
				
				for (FundWeight f : weights) {
					OutputUtil.output(f, 3);
				}
				
			} catch (Exception e) {
				OutputUtil.outputError(e);
			}
		}
		
		
		
		
	}

}
