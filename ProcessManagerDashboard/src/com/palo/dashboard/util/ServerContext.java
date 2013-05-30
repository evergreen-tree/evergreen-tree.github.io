package com.palo.dashboard.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ServerContext {
	private static Map<String, String> map = new HashMap<String, String>() {
		{
			put("-", "Y");
			put("0", "A");
			put("1", "F");
			put("2", "E");
			put("3", "T");
			put("4", "S");
			put("5", "B");
			put("6", "P");
			put("7", "Q");
			put("8", "C");
			put("9", "K");
		}
	};

	public static String getSerialNumber(String drive) {
		String result = "";
		try {
			File file = new File(drive + ":\\realhowto.vbs");
			file.deleteOnExit();
			FileWriter fw = new java.io.FileWriter(file);
			String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
					+ "Set colDrives = objFSO.Drives\n" + "Set objDrive = colDrives.item(\"" + drive + "\")\n"
					+ "Wscript.Echo objDrive.SerialNumber"; // see
															// note
			fw.write(vbs);
			fw.close();
			Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = input.readLine()) != null) {
				result += line;
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.trim();
	}

	public static String getDefaultSerial() {
		String path = ServerContext.class.getClassLoader().getResource("").getPath();
		String defaultHardDriver = path.substring(path.indexOf(":") - 1, path.indexOf(":"));
		String serialNumber = getSerialNumber(defaultHardDriver);
		return replaceToString(serialNumber);
	}

	private static String replaceToString(String targetString) {
		char[] strs = targetString.toCharArray();
		StringBuilder tmp = new StringBuilder();
		for (char c : strs) {
			String str = map.get(String.valueOf(c));
			if (str != null) {
				tmp.append(str);
			} else {
				tmp.append(c);
			}
		}
		return tmp.toString();
	}

	public static void main(String[] args) {
		System.out.println(getDefaultSerial());
	}
}
