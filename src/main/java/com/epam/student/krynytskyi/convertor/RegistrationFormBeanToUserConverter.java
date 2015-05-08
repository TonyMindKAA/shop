package com.epam.student.krynytskyi.convertor;

import com.epam.student.krynytskyi.beans.RegistrationFormBean;
import com.epam.student.krynytskyi.entity.Role;
import com.epam.student.krynytskyi.entity.User;

public class RegistrationFormBeanToUserConverter {
	private static final String CLIENT_ROLE_VALUE = "CLIENT";
	private static final int CLIENT_ROLE_ID = 1;

	public User convert(RegistrationFormBean formBean){
		User newUser =  new User();
		Role role = new Role();
		role.setId(CLIENT_ROLE_ID);
		role.setRole(CLIENT_ROLE_VALUE);
		newUser.setEmail(formBean.getEmailAddress());
		newUser.setLastName(formBean.getLastName());
		newUser.setName(formBean.getName());
		newUser.setPassword(formBean.getPassword());
		newUser.setPhone(formBean.getPhone());
		newUser.setImg("default.jpg");
		newUser.setRole(role);
		return newUser;
	}
}
