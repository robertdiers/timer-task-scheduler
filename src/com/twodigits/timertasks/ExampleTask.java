package com.twodigits.timertasks;

import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

/**
 * example task
 * @author robert.diers
 */
public class ExampleTask extends TimerTask {	
	
	private BatchInterface batch;
	
	public ExampleTask(BatchInterface batch) {
		this.batch = batch;
	}
	
	public void run() {
		try {			
	    	if (TimerStore.getInstance().isJobserver()) {
	    		batch.exampleBatch();
	    	}	    	
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		//reschedule
	    	TimerStore.getInstance().scheduleTimerTask(new ExampleTask(batch), getNextExecution());
    	}
    }
	
//	/**
//	 * tomorrow 4 o'clock
//	 * @return
//	 */
//	public Date getNextExecution() {
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.HOUR_OF_DAY, 4);
//		cal.set(Calendar.MINUTE, 0);
//		cal.set(Calendar.SECOND, 0);
//		cal.set(Calendar.MILLISECOND, 0);
//		cal.add(Calendar.DAY_OF_YEAR, 1);
//		return cal.getTime();
//	}

	/**
	 * 10 seconds
	 * @return
	 */
	public Date getNextExecution() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, 10);
		return cal.getTime();
	}
   
}