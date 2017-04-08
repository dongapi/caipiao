package com.ldm.common;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class TimerListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
		ses.scheduleWithFixedDelay(new Runnable() {
			public void run() {
				
			}
		}, 0, 1, TimeUnit.HOURS);
	}
}
