package com.dear.action;

import java.sql.SQLException;
import java.util.List;

import com.dear.database.HsqlDBConnectionGenerater;
import com.dear.po.DocumentHistory;
import com.dispacher.context.RequestContext;
import com.dispacher.context.ResponseContext;
import com.holder.DBContextHolder;
import com.holder.DBUTil;

public class ReadAction {
	public void readFile() {
		String fileName = (String) RequestContext.getParameter("id");
		String sql = "select * from DocumentHistory where id='" + fileName + "'";
		try {
			DBContextHolder.setContextConnection(HsqlDBConnectionGenerater.generaterDefaultConnection());
			List<DocumentHistory> historys = DBUTil.getResult(sql, DocumentHistory.class);
			DocumentHistory his = historys.get(0);
			his.setFullText(his.getFullText().replaceAll("\n", "<br />"));
			ResponseContext.setReturnAttr("history", historys.get(0));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResponseContext.forword("/WEB-INF/jsp/process/read.jsp");
	}
}
