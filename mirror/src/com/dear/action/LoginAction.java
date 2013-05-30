package com.dear.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dear.constant.Constants;
import com.dear.database.DataFetcher;
import com.dear.database.HsqlDBConnectionGenerater;
import com.dear.po.DocumentHistory;
import com.dispacher.context.RequestContext;
import com.dispacher.context.ResponseContext;
import com.holder.DBContextHolder;
import com.holder.DBUTil;

public class LoginAction {
	DataFetcher dataFetcher = new DataFetcher();

	public void doLogin() {
		List<DocumentHistory> result = dataFetcher.fetchFirstPageDocumentHistory();
		ResponseContext.setReturnAttr("historyList", result);
		ResponseContext.setReturnAttr("curPage", 1);
		int countNum = dataFetcher.getDocumentHistoryCount();
		int pageSize = (countNum % Constants.PAGE_SIZE == 0) ? countNum / Constants.PAGE_SIZE : countNum
				/ Constants.PAGE_SIZE + 1;
		ResponseContext.setReturnAttr("allDocumentHistoryCount", countNum);
		ResponseContext.setReturnAttr("DocumentHistorPageCount", pageSize);
		ResponseContext.forword("/WEB-INF/jsp/frame.jsp");
	}

	public void alreadyLogin() {
		int targetPage = Integer.valueOf(RequestContext.getParameter("targetPage").toString());
		List<DocumentHistory> result = dataFetcher.fetchDocumentHistoryByPage(targetPage);
		ResponseContext.setReturnAttr("historyList", result);
		ResponseContext.setReturnAttr("curPage", targetPage);
		int countNum = dataFetcher.getDocumentHistoryCount();
		int pageSize = (countNum % Constants.PAGE_SIZE == 0) ? countNum / Constants.PAGE_SIZE : countNum
				/ Constants.PAGE_SIZE + 1;
		ResponseContext.setReturnAttr("allDocumentHistoryCount", countNum);
		ResponseContext.setReturnAttr("DocumentHistorPageCount", pageSize);
		ResponseContext.forword("/WEB-INF/jsp/frame.jsp");
	}
}
