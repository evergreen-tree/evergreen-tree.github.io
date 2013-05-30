package com.palo.dashboard.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.palo.dashboard.po.Result;

public class ProcessUtil {
	public static boolean isProcessRunning(String serviceName) {
		BufferedReader reader = null;
		try {
			Process p = Runtime.getRuntime().exec(Constants.TASKLIST);
			reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				if (line.contains(serviceName)) {
					return true;
				}
			}
			return false;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public static Result startProcess(String filePath) {
		Result result = new Result();
		File objFile = new File(filePath);

		try {
			Process process = Runtime.getRuntime().exec(
					"cmd /C start " + (objFile + "\\start.vbs").replaceAll(" ", "\" \""), null, objFile);
			System.out.println("starting with command :" + objFile + "\\start.vbs");
			if (filePath.indexOf("core") > 0) {
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			result.setStatus("running");
			InputStream resultStream = process.getInputStream();
			if (resultStream.available() > 0) {
				byte[] tmp = new byte[resultStream.available()];
				resultStream.read(tmp);
				System.out.println(new String(tmp));
			}
			resultStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Process:" + filePath + "not start successfully");
			result.setStatus("failed");
			e.printStackTrace();

		}
		return result;
	}

	public static Result shutdownProcess(String processName) {
		Result result = new Result();
		try {
			System.out.println("stop with command: " + "taskkill /F /IM " + processName);
			Runtime.getRuntime().exec("taskkill /F /IM " + processName);
			result.setStatus("stopped");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("stop process: " + processName + "failed");
			result.setStatus("failed");
		}
		return result;
	}
}
