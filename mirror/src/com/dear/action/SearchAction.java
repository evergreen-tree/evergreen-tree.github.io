package com.dear.action;

import java.sql.SQLException;
import java.util.List;

import com.dear.database.HsqlDBConnectionGenerater;
import com.dear.document.IndexBuilderController;
import com.dear.document.searcher.SearchResult;
import com.dear.po.DocumentHistory;
import com.dispacher.context.RequestContext;
import com.dispacher.context.ResponseContext;
import com.holder.DBContextHolder;
import com.holder.DBUTil;

public class SearchAction {
	/**
	 * 
	 * @return
	 */
	public List<DocumentHistory> getAllHistory() {
		List<DocumentHistory> result = null;
		try {
			DBContextHolder.setContextConnection(HsqlDBConnectionGenerater.generaterDefaultConnection());
			result = DBUTil.getResult("select name from DocumentHistory", DocumentHistory.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void doSearch() {
		String searchStr = getSearchConditionByPersistent();
		if (searchStr.trim().length() == 0) {
			ResponseContext.forword("/WEB-INF/jsp/process/search.jsp");
			return;
		}
		String targetPageStr = RequestContext.getParameter("targetPage") == null ? "1" : RequestContext.getParameter(
				"targetPage").toString();
		int targetPage = Integer.valueOf(targetPageStr);
		List<SearchResult> result = IndexBuilderController.getInstance().doSearch(searchStr, targetPage);
		ResponseContext.setReturnAttr("search", searchStr);
		ResponseContext.setReturnAttr("curPage", targetPage);
		ResponseContext.setReturnAttr("searchResults", result);
		ResponseContext.forword("/WEB-INF/jsp/process/search.jsp");
	}

	/**
	 * try to get the persistented search condition, if not in sesssion will
	 * search request
	 * 
	 * @return
	 */
	private String getSearchConditionByPersistent() {
		Object searchStr = (String) (RequestContext.getParameter("search") == null ? null : RequestContext
				.getParameter("search"));
		if (searchStr == null) {
			searchStr = RequestContext.getCurSession().getAttribute("searchStr") == null ? "" : RequestContext
					.getCurSession().getAttribute("searchStr");
		}
		RequestContext.getCurSession().setAttribute("searchStr", searchStr);
		return String.valueOf(searchStr);
	}

	public void gotoSearch() {
		ResponseContext.forword("/WEB-INF/jsp/process/search.jsp");
	}
}
