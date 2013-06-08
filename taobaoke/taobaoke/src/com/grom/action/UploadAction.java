package com.grom.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.dispacher.context.ResponseContext;
import com.dispacher.upload.UploadHolder;
import com.grom.database.MySQLDatabaseService;
import com.grom.excel.ExcelParser;
import com.grom.model.wp_postmeta;
import com.grom.model.wp_posts;

public class UploadAction {
	public void doUpload() {
		String message = "";
		File file = UploadHolder.saveFileTo("/var/www");
		MySQLDatabaseService mySQLDatabaseService = new MySQLDatabaseService();
		Map<wp_posts, List<wp_postmeta>> result = new ExcelParser().parseFile(file);
		for (wp_posts post : result.keySet()) {
			int id = mySQLDatabaseService.insertPost(post);
			mySQLDatabaseService.insertPostMeta(id, result.get(post));
		}
		ResponseContext.setReturnAttr("message", message);
		ResponseContext.forword("success.jsp");
	}
}
