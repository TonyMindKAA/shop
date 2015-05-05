package com.epam.student.krynytskyi.util;

public class ObjectUtil {
	private ObjectUtil() {	}
	public static  void  checkNull(Object object) {
		if(object == null)
			throw new IllegalArgumentException();
	}
	
}
