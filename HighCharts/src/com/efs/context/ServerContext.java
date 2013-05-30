package com.efs.context;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ServerContext {
	private ServerContext() {
	}

	public static String getSerialNumber(String drive) {
		String result = "";
		try {
			File file = File.createTempFile("realhowto", ".vbs");
			file.deleteOnExit();
			FileWriter fw = new java.io.FileWriter(file);
			String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
					+ "Set colDrives = objFSO.Drives\n" + "Set objDrive = colDrives.item(\"" + drive + "\")\n"
					+ "Wscript.Echo objDrive.SerialNumber"; // see note
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

	public static String getDiskSeries() {
		String result = "";
		String cmd = "wmic diskdrive";
		try {
			Process p = Runtime.getRuntime().exec(new String[] { "cmd.exe", "/C", cmd });
			InputStream resultStream = p.getInputStream();
			if (resultStream.available() > 0) {
				byte[] tmp = new byte[resultStream.available()];
				resultStream.read(tmp);
				System.out.println(new String(tmp));
			}
			resultStream.close();
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(getDiskSeries());
		String sn = ServerContext.getSerialNumber("C");
		System.out.println(sn);
		javax.swing.JOptionPane.showConfirmDialog((java.awt.Component) null, sn, "Serial Number of C:",
				javax.swing.JOptionPane.DEFAULT_OPTION);
	}
}
