package com.epam.student.krynytskyi.containers;

import com.epam.student.krynytskyi.beans.captcha.CaptchaBean;

public interface CapthcaBeanContainer {
	CaptchaBean getById(int id);

	CaptchaBean getById(String id);

	CaptchaBean insert(CaptchaBean capthcaBean);

	int remove(CaptchaBean capthcaBean);

	int remove(int id);

	int remove(String id);
	
	int size();

	void cleanOld(long timeOut);
}
