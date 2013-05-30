package com.dear.document;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.dispacher.log.LogFactory;

public class IndexBuildExecutor {
	ExecutorService pool = Executors.newFixedThreadPool(5);
	private volatile boolean isRunning;

	/**
	 * the real parse method, will this method be block?
	 */
	public void runningParse() {
		TaskQueue taskQueue = TaskQueue.getInstance();
		isRunning = true;
		ParsingTask task = null;
		while (true) {
			LogFactory.getDefaultLog().log("taskQueuse size {" + taskQueue.size() + "},parsing...");
			if (taskQueue.size() > 0) {
				task = taskQueue.remove();
				pool.execute(task);
			} else {
				break;
			}
		}
		isRunning = false;
	}

	public boolean isRunning() {
		return isRunning;
	}
}
