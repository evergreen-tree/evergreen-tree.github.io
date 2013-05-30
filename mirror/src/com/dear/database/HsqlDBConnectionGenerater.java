package com.dear.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.dear.util.FileLocationUtil;
import com.dispacher.config.GlobalConfig;
import com.holder.DBContextHolder;
import com.holder.DBUTil;

public class HsqlDBConnectionGenerater {
	private static String dataFileLocation = GlobalConfig.getProperty("hsqldb.file.location");
	static {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			dataFileLocation = FileLocationUtil.getDBFileLocation();
			System.out.println("File location : " + dataFileLocation);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	static void init() {
		File dataFile = new File(dataFileLocation);
		if (!dataFile.exists()) {
			dataFile.mkdirs();
		}
	}

	public static Connection generaterDefaultConnection() throws SQLException {
		if (!isDatabaseExists()) {
			init();
		}
		return DriverManager.getConnection("jdbc:hsqldb:file:" + dataFileLocation, "sa", "");
	}

	public static void main(String[] args) {
		try {
			generaterDefaultConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return
	 */
	public static boolean isDatabaseExists() {
		File dataFile = new File(dataFileLocation);
		if (dataFile.exists()) {
			return true;
		}
		return false;
	}

	public static void initDatabase() throws SQLException {
		String sql = "create table DocumentHistory(id varchar(60),name varchar(60),candidate varchar(255) , career varchar(255),fullText varchar(20000));";
		DBContextHolder.setContextConnection(generaterDefaultConnection());
		DBUTil.update(sql);
	}
}
