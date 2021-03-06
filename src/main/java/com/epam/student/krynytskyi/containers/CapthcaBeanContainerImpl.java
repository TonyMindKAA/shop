package com.epam.student.krynytskyi.containers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.epam.student.krynytskyi.beans.captcha.CaptchaBean;

public class CapthcaBeanContainerImpl implements CapthcaBeanContainer {
	
	private Map<String, CaptchaBean> captchas = new HashMap<>();

	@Override
	public CaptchaBean getById(int id) {
		return getById(Integer.toString(id));
	}

	@Override
	public CaptchaBean getById(String id) {
		checkNull(id);
		return captchas.get(id);
	}

	@Override
	public CaptchaBean insert(CaptchaBean capthcaBean) {
		synchronized (captchas) {
			captchas.put(capthcaBean.getId(), capthcaBean);
			return capthcaBean;
		}
	}

	@Override
	public int remove(CaptchaBean capthcaBean) {
		checkNull(capthcaBean);
		return remove(capthcaBean.getId());
	}

	@Override
	public int remove(int id) {
		return remove(Integer.toString(id));
	}

	@Override
	public int remove(String id) {
		checkNull(id);
		captchas.remove(id);
		return 0;
	}

	private void checkNull(Object object) {
		if (object == null)
			throw new IllegalArgumentException();
	}

	@Override
	public int size() {
		return captchas.size();
	}

	@Override
	public void cleanOld(long timeOut) {
		synchronized (captchas) {
			java.util.Iterator<Entry<String, CaptchaBean>> iterator = captchas.entrySet().iterator();
			while(iterator.hasNext()) {
				checkCaptcha(iterator,timeOut);
			}
		}
	}

	private void checkCaptcha(java.util.Iterator<Entry<String, CaptchaBean>> iterator, long timeOut) {
		long time = iterator.next().getValue().getCreateTime().getTime();
		long currentTime = new Date().getTime();
		if((currentTime -time ) >timeOut){
			iterator.remove();
		}
	}
}
