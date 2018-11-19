package com.twodigits.timertasks;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/***
 * StartupHandler
 * @author robert.diers
 */
@ApplicationScoped
public class StartupHandler {
	
	//some application server are executing it twice, we have to check this (for example Payara)
  	private static boolean initializedalreadyexecuted = false;	
  	private static boolean destroyedalreadyexecuted = false;	
	
	@Inject
	private BatchInterface batch; 
      
  	public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
  	
  		if (!initializedalreadyexecuted) {
	    	initializedalreadyexecuted = true;
	    	
	    	//schedule tasks
	    	TimerStore.getInstance().scheduleTasks(batch);
	    	
	    	System.out.println("StartupHandler: applicationStartup done.");   
      	}
    }
   
    public void destroy(@Observes @Destroyed(ApplicationScoped.class) Object init) {    	
      	
      	if (!destroyedalreadyexecuted) {
      		destroyedalreadyexecuted = true;
      		
      		//stop all timers
        	TimerStore.getInstance().cancelTasks();
	    	
        	System.out.println("StartupHandler: applicationShutdown done.");   
      	}
    }

}