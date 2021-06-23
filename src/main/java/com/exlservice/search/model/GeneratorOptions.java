package com.exlservice.search.model;

import java.util.ArrayList;
import java.util.List;

//Default PII to be populated
public class GeneratorOptions {
	private List<String> firstNames = new ArrayList<>();
	private List<String> lastNames = new ArrayList<>();
	private List<String> jobTitles = new ArrayList<>();
	private List<String> emailDomains = new ArrayList<>();
	
	private GeneratorRange startDateRange = new GeneratorRange();
	private GeneratorRange endDateRange = new GeneratorRange();
	
	public List<String> getFirstNames() {
		return firstNames;
	}
	public void setFirstNames(List<String> names) {
		this.firstNames = names;
	}

	public List<String> getLastNames() {
		return lastNames;
	}
	public void setLastNames(List<String> names) {
		this.lastNames = names;
	}
	
	public GeneratorRange getStartDateRange() {
		return startDateRange;
	}
	public void setStartRange(GeneratorRange startDateRange) {
		this.startDateRange = startDateRange;
	}
	public GeneratorRange getEndDateRange() {
		return endDateRange;
	}
	public void setEndDateRange(GeneratorRange endDateRange) {
		this.endDateRange = endDateRange;
	}
	public List<String> getJobTitles() {
		return jobTitles;
	}
	public void setJobTitles(List<String> jobTitles) {
		this.jobTitles = jobTitles;
	}
	public List<String> getEmailDomains() {
		return emailDomains;
	}
	public void setEmailDomains(List<String> emailDomains) {
		this.emailDomains = emailDomains;
	}	
}
