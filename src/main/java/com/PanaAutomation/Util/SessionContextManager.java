package com.PanaAutomation.Util;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionContextManager {
	private int waitForElement=25;
	private static final Logger logger = LoggerFactory.getLogger(SessionContextManager.class);
	private static SessionContextManager ourInstance = new SessionContextManager();
	private static final AtomicInteger threadSeq = new AtomicInteger((int) (System.currentTimeMillis() % 0xcafe));

    //getting the instance of the object
    public static SessionContextManager getInstance() {
        return ourInstance;
    }
	
    public int getWaitForElement() {
        return waitForElement;
    }
    public void setWaitForElement(int waitForElement) {
        this.waitForElement = waitForElement;
    }
}
