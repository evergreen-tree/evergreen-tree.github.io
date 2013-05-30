package com.dear.document;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.index.CorruptIndexException;

import com.dear.document.indexer.DocIndexer;
import com.dear.exception.IndexBuildException;
import com.dispacher.log.LogFactory;

public class ParsingTask implements Runnable {
	private File docFile;

	DocIndexer indexer;

	public void setIndexer(DocIndexer indexer) {
		this.indexer = indexer;
	}

	public void setDocPath(File docFile) {
		this.docFile = docFile;
	}

	/**
	 * doing the parse work
	 */
	public void run() {
		try {
			LogFactory.getDefaultLog().log("parsing file {" + docFile + "}");
			indexer.indexDocFile(docFile);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IndexBuildException(e);
		} finally {
			try {
				if (!indexer.isIndexerClosed()) {
					indexer.closeIndexer();
				}
			} catch (CorruptIndexException e) {
				e.printStackTrace();
				throw new IndexBuildException(e);
			} catch (IOException e) {
				e.printStackTrace();
				throw new IndexBuildException(e);
			}
		}
	}

}
