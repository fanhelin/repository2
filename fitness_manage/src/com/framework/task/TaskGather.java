package com.framework.task;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * 
 * @todo gather task to a queue 
 *
 * @author fhr
 * @dateTime 2016 2016-11-19 下午3:12:30
 */

public class TaskGather {

	private static TaskGather taskGather = null;
	private static Object locker = new Object();
	
	private LinkedBlockingDeque<IAsynTask> taskQueue = null;
	
	
	private TaskGather() {
		taskQueue = new LinkedBlockingDeque<IAsynTask>();
	}
	
	private static TaskGather Instance(){
		if(taskGather == null){
			  synchronized (locker){
				  taskGather = new TaskGather(); 
			  }
		}
		return taskGather ;
	}
	
	public static LinkedBlockingDeque<IAsynTask> getTaskQueue(){
		return Instance().taskQueue ;
	}
	
	public static void putTask (IAsynTask task){
		try {
				Instance().taskQueue.put(task) ;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
