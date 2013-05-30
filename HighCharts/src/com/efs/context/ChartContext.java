package com.efs.context;

import java.util.HashMap;
import java.util.Map;

public class ChartContext {
	private Map<String, String> contextMap = new HashMap<String, String>();

	private static ThreadLocal<ChartContext> threadMap = new ThreadLocal<ChartContext>() {
		protected ChartContext initialValue() {
			return new ChartContext();
		}
	};

	private ChartContext() {
	}

	public static ChartContext getThreadSafeChartContext() {
		return threadMap.get();
	}

	public static void setAttribute(String key, String value) {
		getThreadSafeChartContext().contextMap.put(key, value);
	}

	public static String getAttribute(String key) {
		return getThreadSafeChartContext().contextMap.get(key);
	}

	public static void refresh() {
		threadMap.set(new ChartContext());
	}
}
