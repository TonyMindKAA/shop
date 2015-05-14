package com.epam.student.krynytskyi.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.epam.student.krynytskyi.beans.login.LoginFormBean;
import com.epam.student.krynytskyi.entity.User;

public class LoginTag extends SimpleTagSupport{
	@Override
	public void doTag() throws JspException, IOException {
		User user = (User) getJspContext().findAttribute("user");
		LoginFormBean loginFormBean = (LoginFormBean) getJspContext().findAttribute("loginFormDate");
		String email = loginFormBean == null? "":loginFormBean.getEmail();
		if(user != null){
			getJspContext().getOut().write("<h2>Name: "+user.getName()+"</h2>"+
					"<form action=\"logOut\" method=\"post\">"+
					"<input type=\"hidden\" name=\"id\" value=\""+ user.getId() +"\"/>"+
					"<button type=\"submit\" class=\"btn btn-default\">log out</button>"+
				"</form>");
		}else{
			getJspContext().getOut().write(
						"<h2>Login to your account</h2>"+
						"<form action=\"login\" method=\"post\">"+
							"<input type=\"email\" name=\"email\" value=\""+ email +"\" placeholder=\"Email Address\"/>"+
							"<input type=\"password\" name=\"password\" placeholder=\"password\"/>"+
							"<button type=\"submit\" class=\"btn btn-default\">Login</button>"+
						"</form>");
		}
	}
}
