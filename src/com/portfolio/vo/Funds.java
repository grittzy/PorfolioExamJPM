package com.portfolio.vo;

import java.util.ArrayList;
import java.util.List;

public class Funds {
	
	private List<Fund> funds;

	public List<Fund> getFunds() {
		if(funds == null) {
			funds = new ArrayList<Fund>();
		}
		return funds;
	}

	
	
	

}
