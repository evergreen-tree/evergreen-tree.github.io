package com.dear.document;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.dispacher.log.LogFactory;

public class TaskQueue {
	private TaskQueue() {

	}

	private static TaskQueue instance = new TaskQueue();

	static Queue<ParsingTask> taskQueue = new ConcurrentLinkedQueue<ParsingTask>();

	public static TaskQueue getInstance() {
		return instance;
	}

	public static ParsingTask getNextTask() {
		return taskQueue.remove();
	}

	public static boolean hasMoreTask() {
		return instance.size() > 0;
	}

	public synchronized void put(ParsingTask task) {
		taskQueue.add(task);
	}

	public int size() {
		return taskQueue.size();
	}

	public ParsingTask remove() {
		return taskQueue.remove();
	}
}
