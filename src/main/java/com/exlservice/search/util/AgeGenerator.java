package com.exlservice.search.util;

import java.util.Random;

public class AgeGenerator {
	
	static Random random;
	static Integer MIN_AGE = 18;
	static Integer MAX_AGE = 69;
	
	public AgeGenerator() {}

	public Integer newRandomAge() {
		random = new Random();
		return random.nextInt(MAX_AGE - MIN_AGE + 1) + MIN_AGE;
	  }
}
