package com.epam.student.krynytskyi.containers;

import com.epam.student.krynytskyi.beans.CapthcaBean;

public interface CapthcaBeanContainer {
	CapthcaBean getById(int id);

	CapthcaBean getById(String id);

	CapthcaBean insert(CapthcaBean capthcaBean);

	int remove(CapthcaBean capthcaBean);

	int remove(int id);

	int remove(String id);
	
	int size();

	void cleanOld(long timeOut);
}
