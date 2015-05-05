package com.epam.student.krynytskyi.provider;

import com.epam.student.krynytskyi.provider.inner.storege.CaptchaCookieProvider;
import com.epam.student.krynytskyi.provider.inner.storege.CaptchaHiddenProvider;
import com.epam.student.krynytskyi.provider.session.CaptchaSessionProvider;

public class CaptchaProviderFactory {
	
	private static final String HIDDEN_PROVIDER = "hidden";
	private static final String COOKIES_PROVIDER = "cookies";
	private static final String SESSION_PROVIDER = "session";

	public CaptchaProvider getProvider(String item) {
		if (item == null)
			return null;
		switch (item.toLowerCase()) {
		case SESSION_PROVIDER:
			return new CaptchaSessionProvider();
		case COOKIES_PROVIDER:
			return new CaptchaCookieProvider();
		case HIDDEN_PROVIDER:
			return new CaptchaHiddenProvider();
		default:
			return null;
		}
	}
}
