package com.twodigits.timertasks;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer Store Singleton
 * @author robert.diers
 *
 */
public class TimerStore {
	
	private static TimerStore instance = new TimerStore();
	private Timer timer = new Timer();
	
	private TimerStore() {}
	
	public static TimerStore getInstance() {
		return instance;
	}
	
	/**
	 * schedule all tasks
	 */
	public void scheduleTasks(BatchInterface batch) {
		try {			
			if (isJobserver()) {			
				
				//Jira Import
				ExampleTask prt = new ExampleTask(batch);
				scheduleTimerTask(prt, prt.getNextExecution());				
				
			}
		} catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	
	/**
	 * check if we are running the job server
	 * @return
	 */
	public boolean isJobserver() {
		try {
			
			//TODO please add logic here if you want to execute on dedicated instance only (for example server name check with config)
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
    		return false;
    	}
	}
	
	/**
	 * cancel all tasks
	 */
	public void cancelTasks() {
		try {
			//cancel all planned timer tasks
			timer.cancel();
			System.out.println("TimerStore: timer canceled");
		} catch (Exception e) {
			e.getMessage();
    	}
	}
	
	/**
	 * schedule one-time-execution
	 * @param task
	 * @param date
	 */
	public void scheduleTimerTask(TimerTask task, Date date) {
		try {
			timer.schedule(task, date);
			System.out.println("TimerStore: " + task.getClass().getSimpleName() + " scheduled at " + date);
		} catch (Exception e) {
			e.getMessage();
    	}
	}
	
	/**
	 * schedule interval
	 * @param task
	 * @param date
	 * @param period
	 */
	public void scheduleAtFixedRate(TimerTask task, Date date, long period) {
		try {
			timer.scheduleAtFixedRate(task, date, period);
			System.out.println("TimerStore: " + task.getClass().getSimpleName() + " scheduled at " + date + " every "+(period/1000)+" sec");
		} catch (Exception e) {
			e.getMessage();
    	}
	}

}