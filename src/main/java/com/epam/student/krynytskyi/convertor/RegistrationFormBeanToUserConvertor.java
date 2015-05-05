package com.epam.student.krynytskyi.convertor;

import com.epam.student.krynytskyi.beans.RegistrationFormBean;
import com.epam.student.krynytskyi.entity.User;

public class RegistrationFormBeanToUserConvertor {
	public User convert(RegistrationFormBean formBean){
		User newUser =  new User();
		newUser.setEmail(formBean.getEmailAddress());
		newUser.setLastName(formBean.getLastName());
		newUser.setName(formBean.getName());
		newUser.setPassword(formBean.getPassword());
		newUser.setPhone(formBean.getPhone());
		return newUser;
	}
}
