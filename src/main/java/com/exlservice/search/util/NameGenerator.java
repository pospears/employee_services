package com.exlservice.search.util;

import com.exlservice.search.model.GeneratorOptions;

public class NameGenerator extends GeneratorBase {
	
	public NameGenerator(GeneratorOptions options)
	{
		super(options);
	}
	
	public String newRandomFirstName()
	{
		return randListElement(options.getFirstNames());
	}	
	
	public String newRandomLastName()
	{
		return randListElement(options.getLastNames());
	}
}
