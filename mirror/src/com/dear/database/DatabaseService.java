package com.dear.database;

import java.sql.SQLException;
import java.util.List;

import com.dear.po.DocumentHistory;

public interface DatabaseService {
	boolean isIndexCreated(String name);

	DatabaseService instance = new DatabaseService() {
		public boolean isIndexCreated(String name) {
			try {
				List<DocumentHistory> result = BasicDatabaseWork.executeQuery(
						"select * from DocumentHistory where name='" + name + "'", DocumentHistory.class);
				if (result == null || result.size() == 0) {
					return false;
				}
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	};
}
