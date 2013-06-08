package com.grom.database;

import java.io.File;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.grom.excel.ExcelParser;
import com.grom.model.wp_postmeta;
import com.grom.model.wp_posts;
import com.holder.DBContextHolder;
import com.holder.DBUTil;

public class MySQLDatabaseService {
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/taobao", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int insertPost(wp_posts post) {
		DBContextHolder.setContextConnection(getConnection());
		DBUTil.save(post);
		List<Map<String, Object>> result = DBUTil.getResult("select ID from wp_posts where post_title = '"
				+ post.getPost_title() + "'");
		Object tmp = result.get(0).get("ID");
		return Integer.valueOf(tmp.toString());
	}

	public void insertPostMeta(int id, List<wp_postmeta> metas) {
		DBContextHolder.setContextConnection(getConnection());
		for (wp_postmeta meta : metas) {
			meta.setPost_id(id);
			DBUTil.save(meta);
		}
	}

	public static void main(String[] args) {
		MySQLDatabaseService mySQLDatabaseService = new MySQLDatabaseService();
		Map<wp_posts, List<wp_postmeta>> result = new ExcelParser().parseFile(new File(
				"F:\\workspace\\taobaoke\\src\\2013-06-08-32219736.xls"));
		for (wp_posts post : result.keySet()) {
			int id = mySQLDatabaseService.insertPost(post);
			mySQLDatabaseService.insertPostMeta(id, result.get(post));
		}
	}
}
