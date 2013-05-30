package com.dear.document.searcher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

import com.dear.constant.Constants;
import com.dear.document.DocumentConstants;

public class DefaultSearcher {
	public List<SearchResult> doSearch(String fileLocation, String queryString, int page) throws IOException,
			ParseException {
		List<SearchResult> searchResults = new ArrayList<SearchResult>();
		Directory dir = new SimpleFSDirectory(new File(fileLocation));
		IndexSearcher indexSearch = new IndexSearcher(dir);
		QueryParser queryParser = new QueryParser(Version.LUCENE_30, "contents",
				new StandardAnalyzer(Version.LUCENE_30));
		Query query = queryParser.parse(queryString);
		TopDocs hits = indexSearch.search(query, page * Constants.LUCENE_PAGE_SIZE);
		System.out.println("find " + hits.totalHits + " hits");
		for (int i = (page - 1) * Constants.LUCENE_PAGE_SIZE; i < hits.scoreDocs.length; i++) {
			ScoreDoc sdoc = hits.scoreDocs[i];
			Document doc = indexSearch.doc(sdoc.doc);
			SearchResult searchResult = new SearchResult();
			searchResult.setId(doc.get(DocumentConstants.KEY_FILE_ID));
			searchResult.setFileName(doc.get(DocumentConstants.KEY_FILE_NAME));
			searchResult.setContents(doc.get(DocumentConstants.KEY_DOCUMENT_CONTENT).substring(0, 200)
					.replaceAll("\n", "<br/>")
					+ " ....");
			searchResults.add(searchResult);
			System.out.println(doc.get("filename"));
		}
		indexSearch.close();
		return searchResults;
	}
}
