package com.exlservice.search.util;

import com.exlservice.search.model.GeneratorOptions;

public class JobTitleGenerator extends GeneratorBase {
	
	public JobTitleGenerator(GeneratorOptions options)
	{
		super(options);
	}
	
	public String newRandomJobTileName()
	{
		return randListElement(options.getJobTitles());
	}	
}
