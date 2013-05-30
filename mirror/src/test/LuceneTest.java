package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 * 创建索引 Lucene 3.0+
 * 
 * @author Administrator
 * 
 */
public class LuceneTest {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// 保存索引文件的地方
		String indexDir = "C:\\indexDir";
		// 将要搜索word文件的地方
		String dateDir = "C:\\dataDir";
		IndexWriter indexWriter = null;
		// 创建Directory对象
		Directory dir = new SimpleFSDirectory(new File(indexDir));
		// 创建IndexWriter对象,第一个参数是Directory,第二个是分词器,第三个表示是否是创建,如果为false为在此基础上面修改,第四表示表示分词的最大值，比如说new
		// MaxFieldLength(2)，就表示两个字一分，一般用IndexWriter.MaxFieldLength.LIMITED
		indexWriter = new IndexWriter(dir, new StandardAnalyzer(Version.LUCENE_30), false,
				IndexWriter.MaxFieldLength.UNLIMITED);
		File[] files = new File(dateDir).listFiles();
		for (int i = 0; i < files.length; i++) {
			Document doc = new Document();
			InputStream in = new FileInputStream(files[i]);
			XWPFWordExtractor w = new XWPFWordExtractor(new XWPFDocument(in)); 
			// 创建Field对象，并放入doc对象中
			doc.add(new Field("contents", w.getText(), Field.Store.YES, Field.Index.ANALYZED));
			doc.add(new Field("filename", files[i].getName(), Field.Store.YES, Field.Index.NOT_ANALYZED));
			doc.add(new Field("indexDate", DateTools.dateToString(new Date(), DateTools.Resolution.DAY),
					Field.Store.YES, Field.Index.NOT_ANALYZED));
			// 写入IndexWriter
			indexWriter.addDocument(doc);
		}
		// 查看IndexWriter里面有多少个索引
		System.out.println("numDocs" + indexWriter.numDocs());
		indexWriter.close();

	}

}
