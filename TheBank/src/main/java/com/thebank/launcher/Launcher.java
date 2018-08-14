package com.thebank.launcher;

import com.thebank.screens.LoginScreen;
import com.thebank.screens.Screen;

public class Launcher {
	//private static Logger log = Logger.getRootLogger();
	public static void main(String[] args) throws InterruptedException {
//		log.trace("trace log");
//		log.debug("debug log");
//		log.info("info log");
//		log.warn("warn log");
//		log.error("error log");
//		log.fatal("fatal log");
//		Thread.sleep(5000);
		Screen s = new LoginScreen();
		while(true) {
			s = s.start();
		}
		
	}

}
