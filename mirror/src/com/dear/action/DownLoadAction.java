package com.dear.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.dear.database.HsqlDBConnectionGenerater;
import com.dear.po.DocumentHistory;
import com.dear.util.FileLocationUtil;
import com.dispacher.context.RequestContext;
import com.dispacher.context.ResponseContext;
import com.holder.DBContextHolder;
import com.holder.DBUTil;

public class DownLoadAction {
	public void downLoadFile() {
		String fileId = (String) RequestContext.getParameter("fileId");
		String sql = "select * from DocumentHistory where id='" + fileId + "'";
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			DBContextHolder.setContextConnection(HsqlDBConnectionGenerater.generaterDefaultConnection());
			List<DocumentHistory> historys = DBUTil.getResult(sql, DocumentHistory.class);
			DocumentHistory history = historys.get(0);
			HttpServletResponse response = ResponseContext.getCommonResponse();
			response.setContentType("application/msword");
			response.setHeader("Content-disposition", "attachment; filename=" + history.getName());

			String targetFolder = FileLocationUtil.getDocFileLocation();
			bis = new BufferedInputStream(new FileInputStream(targetFolder + "/" + history.getName()));
			bos = new BufferedOutputStream(response.getOutputStream());

			byte[] buff = new byte[2048];
			int bytesRead;

			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
