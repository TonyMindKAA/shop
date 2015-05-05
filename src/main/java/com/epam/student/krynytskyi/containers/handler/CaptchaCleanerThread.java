package com.epam.student.krynytskyi.containers.handler;

import com.epam.student.krynytskyi.provider.CaptchaProvider;

public class CaptchaCleanerThread extends Thread {
	private CaptchaProvider provider;
	private boolean isRun = true;
	private long timeOut ;
	private long timeVerification ;
	
	public CaptchaCleanerThread(CaptchaProvider provider,  long timeOut, long timeVerification ) {
		this.provider = provider;
		this.timeVerification = timeVerification;
		this.timeOut = timeOut;
	}

	@Override
	public void run() {
		while (isRun) {
			try {
				Thread.sleep(timeVerification);
				provider.cleanOld(timeOut);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void shutDown(){
		isRun = false;
	}

}
