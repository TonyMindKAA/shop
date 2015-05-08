package com.epam.student.krynytskyi.controllers;


import com.epam.student.krynytskyi.beans.CaptchaBean;
import com.epam.student.krynytskyi.provider.CaptchaProvider;
import com.github.cage.Cage;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CapchaController extends HttpServlet {
    private static final Logger log = Logger.getLogger(CapchaController.class);
    private static final long serialVersionUID = 1L;
    private CaptchaProvider capthcaPrvider;

    @Override
    public void init() throws ServletException {
        capthcaPrvider = (CaptchaProvider) getServletContext().getAttribute("captchaProvider");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        writeCaptchaToStream(req, resp, capthcaPrvider);
    }

    private void writeCaptchaToStream(HttpServletRequest req, HttpServletResponse resp, CaptchaProvider capthcaPrvider)
            throws IOException {
        Cage cage = new Cage();
        ServletOutputStream outputStream = resp.getOutputStream();
        CaptchaBean captcha = capthcaPrvider.getCaptcha(req);
        cage.draw(captcha.getCaptchaValue(), outputStream);
        outputStream.close();
    }
}
