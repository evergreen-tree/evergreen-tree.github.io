package com.dear.document.indexer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.dear.database.DerbyDBConnectionGenerater;
import com.dear.database.HsqlDBConnectionGenerater;
import com.dear.document.DocumentConstants;
import com.dear.po.DocumentHistory;
import com.holder.DBContextHolder;
import com.holder.DBUTil;

/**
 * this Indexer used to index word file
 * 
 * @author wenbdong
 * 
 */
public class DocIndexer {

	private IndexWriter selfWriter;

	/**
	 * default indexer for doc
	 * 
	 * @param indexFolder
	 * @throws IOException
	 */
	public DocIndexer(File indexFolder) throws IOException {
		Directory dir = new SimpleFSDirectory(indexFolder);
		// 创建IndexWriter对象,第一个参数是Directory,第二个是分词器,第三个表示是否是创建,如果为false为在此基础上面修改,第四表示表示分词的最大值，比如说new
		// MaxFieldLength(2)，就表示两个字一分，一般用IndexWriter.MaxFieldLength.LIMITED
		boolean isCreate = false;
		String[] checkFiles = indexFolder.list();
		if (checkFiles != null && checkFiles.length > 0) {
			isCreate = false;
		} else {
			isCreate = true;
		}
		this.selfWriter = new IndexWriter(dir, new StandardAnalyzer(Version.LUCENE_30), isCreate,
				IndexWriter.MaxFieldLength.UNLIMITED);
	}

	/**
	 * index one file
	 * 
	 * @param folderName
	 * @throws IOException
	 */
	public void indexFolder(String folderName) throws IOException {
		indexFolder(new File(folderName));
	}

	/**
	 * will do nothing if this is not one folder
	 * 
	 * @param folder
	 * @throws IOException
	 */
	public void indexFolder(File folder) throws IOException {
		if (!folder.isDirectory()) {
			return;
		}
		for (File file : folder.listFiles()) {
			if (file.isDirectory()) {
				indexFolder(file);
			} else {
				indexDocFile(file);
			}
		}
	}

	/**
	 * 
	 * @param file
	 * @throws IOException
	 */
	public void indexDocFile(File file) throws IOException {
		if (file == null) {
			return;
		}
		IndexWriter indexWriter = selfWriter;
		Document doc = new Document();
		String text = getTextFromDocument(file);
		DocumentHistory history = insertDataBaseData(text, file);
		// create data on field and put into index writer
		doc.add(new Field(DocumentConstants.KEY_FILE_ID, history.getID(), Field.Store.YES, Field.Index.NOT_ANALYZED));
		doc.add(new Field(DocumentConstants.KEY_DOCUMENT_CONTENT, text, Field.Store.YES, Field.Index.ANALYZED));
		doc.add(new Field(DocumentConstants.KEY_FILE_NAME, file.getName(), Field.Store.YES, Field.Index.NOT_ANALYZED));
		doc.add(new Field(DocumentConstants.KEY_CREATE_DATE, DateTools.dateToString(new Date(), DateTools.Resolution.DAY),
				Field.Store.YES, Field.Index.NOT_ANALYZED));
		indexWriter.addDocument(doc);
	}

	private DocumentHistory insertDataBaseData(String text, File file) {
		try {
			DBContextHolder.setContextConnection(HsqlDBConnectionGenerater.generaterDefaultConnection());
			DocumentHistory history = new DocumentHistory();
			history.setID(UUID.randomUUID().toString());
			history.setName(file.getName());
			history.setFullText(text);
			DBUTil.save(history);
			return history;
		} catch (SQLException e) {
			System.out.println("error while insert the data.");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 读取文件的内容
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private static String getTextFromDocument(File file) throws IOException {
		InputStream in = new FileInputStream(file);
		if (file.getName().toLowerCase().endsWith(".doc")) {
			WordExtractor w = new WordExtractor(in);
			return w.getText();
		} else {
			XWPFWordExtractor w = new XWPFWordExtractor(new XWPFDocument(in));
			return w.getText();
		}
	}

	private boolean isClosed = false;

	public boolean isIndexerClosed() {
		return isClosed;
	}

	public void closeIndexer() throws CorruptIndexException, IOException {
		selfWriter.optimize();
		selfWriter.close();
		isClosed = true;
	}
	
	public static void main(String[] args) {
		File file = new File("d:/项目经理IBM-华为.docx");
		try {
			System.out.println(getTextFromDocument(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
