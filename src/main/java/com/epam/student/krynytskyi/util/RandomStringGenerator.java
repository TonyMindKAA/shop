package com.epam.student.krynytskyi.util;

import java.util.Random;

public class RandomStringGenerator {
	public static  int  genrate(int to){
		Random random2 = new Random();
		return  random2.nextInt(to);
	} 
}
