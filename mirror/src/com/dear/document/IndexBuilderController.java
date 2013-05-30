package com.dear.document;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.lucene.queryParser.ParseException;

import com.dear.constant.Constants;
import com.dear.document.indexer.DocIndexer;
import com.dear.document.searcher.DefaultSearcher;
import com.dear.document.searcher.SearchResult;
import com.dispacher.log.LogFactory;

public class IndexBuilderController {
	private static String docLocationStatic = null;

	private static String indexLocationStatic = null;

	private static IndexBuilderController instance = new IndexBuilderController();

	private static IndexBuildExecutor executor = new IndexBuildExecutor();

	public static IndexBuilderController getInstance() {
		return instance;
	}

	/**
	 * 该方法只允许调用一次
	 * 
	 * @param docLocation
	 */
	public synchronized static void setDocLocation(String docLocation) {
		LogFactory.getDefaultLog().log(IndexBuilderController.class.getName() + " | location :" + docLocation);
		if (docLocationStatic == null) {
			docLocationStatic = docLocation;
		} else {
			throw new RuntimeException("docLocation should not be changed after server started!");
		}

	}

	/**
	 * 该方法只允许调用一次
	 * 
	 * @param docLocation
	 */
	public synchronized static void setIndexLocation(String indexLocation) {
		if (indexLocationStatic == null) {
			indexLocationStatic = indexLocation;
		} else {
			throw new RuntimeException("indexLocationStatic should not be changed after server started!");
		}

	}

	/**
	 * this will do one whole build for the index
	 */
	public void wholeBuild() {

	}

	/**
	 * increase build which will just build one file
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public void increaseBuild(File file) throws IOException {
		LogFactory.getDefaultLog().log("add file to build index queue, file=" + file.getAbsolutePath());
		DocIndexer indexer = new DocIndexer(new File(indexLocationStatic));
		ParsingTask task = new ParsingTask();
		task.setDocPath(file);
		task.setIndexer(indexer);
		TaskQueue.getInstance().put(task);
		protectedStartParsing();
	}

	/**
	 * 
	 */
	public void protectedStartParsing() {
		if (!executor.isRunning()) {
			synchronized (IndexBuilderController.class) {
				if (!executor.isRunning()) { // double check
					executor.runningParse();
				}
			}
		}
	}

	public List<SearchResult> doSearch(String queryString) {
		return doSearch(queryString, 1);
	}

	public List<SearchResult> doSearch(String queryString, int page) {
		DefaultSearcher searcher = new DefaultSearcher();
		try {
			return searcher.doSearch(indexLocationStatic, queryString, page);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
