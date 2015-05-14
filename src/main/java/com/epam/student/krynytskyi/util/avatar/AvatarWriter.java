package com.epam.student.krynytskyi.util.avatar;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.epam.student.krynytskyi.beans.registration.RegistrationFormBean;
import com.epam.student.krynytskyi.util.hashcode.generator.Sha1HexGenerator;

public class AvatarWriter {
	private static final String DEFALUT_AVATAR_NAME = "default.jpg";
	
	public String writeAvatar(RegistrationFormBean formBean, HttpServletRequest request) throws IOException{
		Part avatar = formBean.getAvatar();
		String avatarName = generateAvatarName(formBean);
		String realPath = request.getServletContext().getRealPath("/")+"resources\\avatars\\";
		if(!avatarName.equals(DEFALUT_AVATAR_NAME)) {
			chackFolder(realPath);
		}
			avatar.write(realPath+avatarName);
		return avatarName;
	}
	
	private void chackFolder(String realPath) {
		File file = new File(realPath);
		if(!file.isDirectory()){
			file.mkdir();
		}
	}


	private String generateAvatarName(RegistrationFormBean formBean) {
		String imgEnding = getAvaterEnding(formBean.getAvatar());
		Sha1HexGenerator hexGenerator = new Sha1HexGenerator();
		try {
			if(imgEnding.equals(""))
				return DEFALUT_AVATAR_NAME;
			else
				return hexGenerator.makeSHA1Hash(formBean.getEmailAddress())+"."+imgEnding;
		} catch (NoSuchAlgorithmException e) {
			return formBean.getEmailAddress().hashCode()+"."+imgEnding;
		}
	}


	private String getAvaterEnding(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			   if (cd.trim().startsWith("filename")) {
			    String fileName = cd.substring(cd.indexOf('=') + 1).trim()
			      .replace("\"", "");
			    return fileName.substring(fileName.lastIndexOf('.')+1);
			   }
			  }
		return "";
	}
}
