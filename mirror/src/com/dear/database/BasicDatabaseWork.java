package com.dear.database;

import java.sql.SQLException;
import java.util.List;

import com.holder.DBContextHolder;
import com.holder.DBUTil;

public class BasicDatabaseWork {
	public static <E> List<E> executeQuery(String sql, Class<E> type) throws SQLException {
		DBContextHolder.setContextConnection(HsqlDBConnectionGenerater.generaterDefaultConnection());
		return DBUTil.getResult(sql, type);
	}
}
