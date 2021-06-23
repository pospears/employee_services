package com.exlservice.search.util;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.exlservice.search.model.GeneratorOptions;

public class GeneratorBase {

	protected GeneratorOptions options;
	
	protected GeneratorBase(GeneratorOptions options)
	{
		this.options = options;
	}
	
	protected int randBetween(int low, int high)
	{		
		int result = ThreadLocalRandom.current().nextInt(high-low) + low;		
		return result;
	}
	
	protected String randListElement(List<String> list)
	{
		int rnd = ThreadLocalRandom.current().nextInt(list.size());
	    return list.get(rnd);
		
	}
}
