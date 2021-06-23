package com.exlservice.search.util;

import com.exlservice.search.model.GeneratorOptions;

public class EmailGenerator extends GeneratorBase {
	
	public EmailGenerator(GeneratorOptions options)
	{
		super(options);
	}
	
	public String newRandomEmail()
	{
		return randListElement(options.getEmailDomains());
	}
}
