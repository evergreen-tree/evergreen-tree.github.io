package com.dear.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dear.constant.Constants;
import com.dear.po.DocumentHistory;
import com.holder.DBContextHolder;
import com.holder.DBUTil;

public class DataFetcher {
	private static final int FIRST_PAGE_START = 1;

	public List<DocumentHistory> fetchFirstPageDocumentHistory() {
		List<DocumentHistory> result = fetchDocumentHistoryByPage(FIRST_PAGE_START);
		return result;
	}

	public int getDocumentHistoryCount() {
		String sql = "select count(1) as COUNTNUM from DocumentHistory";
		try {
			DBContextHolder.setContextConnection(HsqlDBConnectionGenerater.generaterDefaultConnection());
			List<Map<String, Object>> result = DBUTil.getResult(sql);
			Integer countNum = Integer.valueOf(String.valueOf(result.get(0).get("COUNTNUM")));
			return countNum;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("error while execute sql [" + sql + "]", e);
		}
	}

	public List<DocumentHistory> fetchDocumentHistoryByPage(int page) {
		List<DocumentHistory> result = new ArrayList<DocumentHistory>();
		String sql = "select limit " + getFirstRecordNum(page) + " " + Constants.PAGE_SIZE
				+ " id,name FROM DocumentHistory";
		try {
			DBContextHolder.setContextConnection(HsqlDBConnectionGenerater.generaterDefaultConnection());
			result = DBUTil.getResult(sql, DocumentHistory.class);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("error while execute sql [" + sql + "]", e);
		}
		return result;
	}

	/**
	 * 
	 * @param page
	 * @return
	 */
	private int getFirstRecordNum(int page) {
		return (page - 1) * Constants.PAGE_SIZE;
	}
}
