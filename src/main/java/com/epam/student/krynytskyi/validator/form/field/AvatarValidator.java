package com.epam.student.krynytskyi.validator.form.field;

import java.util.List;

import javax.servlet.http.Part;

public class AvatarValidator {
	public boolean validate(Part avatar,List<String> listImgEnding) {
		if(avatar == null || listImgEnding == null)
			return false;
		String pictureEnding = getAvaterEnding(avatar);
		for (String string : listImgEnding) {
			if(string.equals(pictureEnding)){
				return true;
			}
		}
		return false;
	}

	private String getAvaterEnding(Part avatar) {
		String name = avatar.getName();
		int lastIndexOf = name.lastIndexOf(".");
		if (lastIndexOf > 0)
			return name.substring(lastIndexOf+1, name.length());
		else
			return "";
	}
}
