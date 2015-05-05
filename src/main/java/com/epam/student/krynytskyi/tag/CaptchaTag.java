package com.epam.student.krynytskyi.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import java.io.IOException;

public class CaptchaTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		String findAttribute = (String) getJspContext().findAttribute("captchaHiden");
		if(findAttribute != null){
			getJspContext().getOut().write("<img src=\"captcha.png?captchaHiden="+findAttribute+"\">"
					+ "<input type=\"text\" id=\"captcha\" name=\"captcha\" placeholder=\"captcha\"\"/>"
					+ "<input type=\"hidden\" name=\"captchaHiden\" value=\""+ findAttribute+ "\"/>");
		}else{
			getJspContext().getOut().write("<img src=\"captcha\">"
					+ "<input type=\"text\" id=\"captcha\" name=\"captcha\" placeholder=\"captcha\"/>");
		}
	}
}