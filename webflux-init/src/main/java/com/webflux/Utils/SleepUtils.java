package com.webflux.Utils;

public class SleepUtils {
	
	public static void sleepSeconds(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
