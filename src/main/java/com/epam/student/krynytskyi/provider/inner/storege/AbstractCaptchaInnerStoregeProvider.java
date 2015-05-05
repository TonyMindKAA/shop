package com.epam.student.krynytskyi.provider.inner.storege;

import com.epam.student.krynytskyi.containers.CapthcaBeanContainer;
import com.epam.student.krynytskyi.containers.CapthcaBeanContainerImpl;
import com.epam.student.krynytskyi.provider.CaptchaProvider;

public abstract class AbstractCaptchaInnerStoregeProvider implements
		CaptchaProvider {
	private CapthcaBeanContainer capthcaBeanContainer = new CapthcaBeanContainerImpl();

	public CapthcaBeanContainer getCapthcaBeanContainer() {
		return capthcaBeanContainer;
	}

	public void setCapthcaBeanContainer(
			CapthcaBeanContainer capthcaBeanContainer) {
		this.capthcaBeanContainer = capthcaBeanContainer;
	}
}
