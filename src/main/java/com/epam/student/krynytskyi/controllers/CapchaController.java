package com.epam.student.krynytskyi.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.student.krynytskyi.beans.CapthcaBean;
import com.epam.student.krynytskyi.provider.CaptchaProvider;
import com.github.cage.Cage;

public class CapchaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CaptchaProvider capthcaPrvider;
	private ServletOutputStream outputStream;
	
	@Override
	public void init() throws ServletException {
		capthcaPrvider =	 (CaptchaProvider)getServletContext().getAttribute("captchaProvider");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		writeCaptchaToStream(req, resp, capthcaPrvider);
	}

	private void writeCaptchaToStream(HttpServletRequest req,
			HttpServletResponse resp, CaptchaProvider capthcaPrvider)
			throws IOException {
		Cage cage = new Cage();
		outputStream = resp.getOutputStream();
		CapthcaBean captcha = capthcaPrvider.getCaptcha(req);
		cage.draw(captcha.getCaptchaValue(), outputStream);
		outputStream.close();
	}
}
