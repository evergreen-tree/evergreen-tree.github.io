package com.efs.log;

import java.util.Date;

public final class ConsoleLog {
	public static int debug = 1;
	public static int info = 2;
	public static int error = 3;

	private static int logLevel = 2;

	public static void setLogLevel(int logLevel) {
		ConsoleLog.logLevel = logLevel;
	}

	public static void debug(String debugString) {
		if (logLevel >= debug) {
			System.out.println(new Date() + " | debug | " + debugString);
		}
	}

	public static void info(String infoString) {
		if (logLevel >= info) {
			System.out.println(new Date() + " | debug | " + infoString);
		}
	}

	public static void info(Object[] infoList) {
		if (logLevel >= info) {
			System.out.println(new Date() + " | debug | " + infoList);
			System.out.println("objectList : ");
			for (Object o : infoList) {
				System.out.print(o);
			}
			System.out.println("------------------");
		}
	}

	public static void error(String errorString) {
		if (logLevel >= error) {
			System.out.println(new Date() + " | debug | " + errorString);
		}
	}
}
