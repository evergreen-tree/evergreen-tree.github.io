package com.dear.action;

import java.io.File;
import java.io.IOException;

import com.dear.bo.UploadResult;
import com.dear.database.DatabaseService;
import com.dear.document.IndexBuilderController;
import com.dear.util.FileLocationUtil;
import com.dispacher.config.GlobalConfig;
import com.dispacher.context.RequestContext;
import com.dispacher.context.ResponseContext;
import com.dispacher.upload.UploadHolder;

public class UploadAction {
	public void gotoUpload() {
		ResponseContext.forword("/WEB-INF/jsp/process/upload.jsp");
	}

	public void doUpload() {
		String targetFolder = FileLocationUtil.getDocFileLocation();
		String message = "";
		File file = UploadHolder.saveFileTo(targetFolder + "/");
		if (DatabaseService.instance.isIndexCreated(file.getName())) {
			message = "file already indexed";
			ResponseContext.setReturnAttr("message", message);
			gotoUpload();
			return;
		}
		message = triggerFullTextParser(file);
		ResponseContext.setReturnAttr("message", message);
		gotoUpload();
	}

	public UploadResult doAjaxUpload() {
		String targetFolder = FileLocationUtil.getDocFileLocation();
		String message = "";
		File file = UploadHolder.saveFileTo(targetFolder + "/");
		ResponseContext.getCommonResponse().setContentType("text/html; charset=UTF-8");
		UploadResult result = new UploadResult();
		result.setFileName(file.getName());
		if (DatabaseService.instance.isIndexCreated(file.getName())) {
			message = "file already indexed";
			result.setStatus("false");
			result.setMessage(message);
			return result;
		}
		message = triggerFullTextParser(file);
		result.setStatus("true");
		return result;
	}

	private String triggerFullTextParser(File filePath) {
		try {
			System.out.println("file is going to be parsed: file:" + filePath);
			IndexBuilderController.getInstance().increaseBuild(filePath);
			return filePath.getName() + " build Sucessfully!";
		} catch (IOException e) {
			e.printStackTrace();
			return "build failed, e=" + e.getMessage();
		}

	}
}
