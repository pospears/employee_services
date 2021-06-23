package com.exlservice.search.util;

import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.exlservice.search.model.GeneratorOptions;
import com.exlservice.search.model.GeneratorRange;

public class DateGenerator extends GeneratorBase {

	private static Logger logger = LogManager.getLogger(DateGenerator.class);
		
	public DateGenerator(GeneratorOptions options)
	{
		super(options);
	}
	
	public LocalDate newRandomStartDate()
	{
		return newDate(options.getStartDateRange());
	}

	public LocalDate newRandomEndDate()
	{
		return newDate(options.getEndDateRange());
	}

	private LocalDate newDate(GeneratorRange range)
	{
		try
		{
	        int year = randBetween(range.getStart(), range.getEnd());
	        int month = randBetween(1, 12);
	        int dayOfMonth = randBetween(1, 28);
	    
	        return LocalDate.of(year, month, dayOfMonth);
		}
		catch (Exception e)
		{
			logger.error("Error generating date!", e);
			throw new RuntimeException(e);
		}		
	}	
}
