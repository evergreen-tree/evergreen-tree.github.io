package com.dear.util;

import com.dispacher.config.GlobalConfig;

public class FileLocationUtil {
	private static String dataFileLocation = GlobalConfig.getProperty("hsqldb.file.location");
	private static String file_location = GlobalConfig.getProperty("file.location");
	private static String file_index = GlobalConfig.getProperty("file.index");

	public static String getDBFileLocation() {
		return generateOsLoaction(dataFileLocation);
	}

	public static String getIndexFileLocation() {
		return generateOsLoaction(file_location);
	}

	public static String getDocFileLocation() {
		return generateOsLoaction(file_index);
	}

	private static String generateOsLoaction(String location) {
		String osName = System.getProperty("os.name");
		if (osName.indexOf("Windows") > -1 && !location.startsWith("c:")) {
			location = ("c:" + location).replaceAll("/", "\\\\\\\\");
			System.out.println("File location : " + dataFileLocation);
		}
		return location;
	}
}
