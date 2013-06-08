package com.grom.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.grom.model.wp_postmeta;
import com.grom.model.wp_posts;

public class ExcelParser {
	public Map<wp_posts, List<wp_postmeta>> parseFile(File file) {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));
			HSSFSheet sheet = workbook.getSheetAt(0);
			int num = sheet.getLastRowNum();
			Map<wp_posts, List<wp_postmeta>> maps = new HashMap<wp_posts, List<wp_postmeta>>();
			for (int i = 1; i < num; i++) {
				List<wp_postmeta> metas = new ArrayList<wp_postmeta>();
				System.out.println(i);
				HSSFRow row = sheet.getRow(i);
				wp_posts post = new wp_posts();
				post.setPost_title(row.getCell(2).getStringCellValue());
				post.setPost_content(row.getCell(9).getStringCellValue());
				post.setPost_content_filtered("欢迎");
				post.setPost_excerpt("");

				wp_postmeta image = new wp_postmeta();
				image.setMeta_key("imageUrl");
				image.setMeta_value(row.getCell(1).getStringCellValue());
				metas.add(image);
				System.out.println(row.getCell(1).getStringCellValue());

				wp_postmeta link = new wp_postmeta();
				link.setMeta_key("link");
				link.setMeta_value(row.getCell(3).getStringCellValue());
				metas.add(link);

				wp_postmeta businesses = new wp_postmeta();
				businesses.setMeta_key("businesses");
				businesses.setMeta_value(row.getCell(4).getStringCellValue());
				metas.add(businesses);

				wp_postmeta businesses_link = new wp_postmeta();
				businesses_link.setMeta_key("businesses_link");
				businesses_link.setMeta_value(row.getCell(5).getStringCellValue());
				metas.add(businesses_link);

				wp_postmeta oldprice = new wp_postmeta();
				oldprice.setMeta_key("oldprice");
				oldprice.setMeta_value(String.valueOf(row.getCell(7).getStringCellValue()));
				metas.add(oldprice);
				wp_postmeta price = new wp_postmeta();
				price.setMeta_key("price");
				price.setMeta_value(String.valueOf(row.getCell(8).getStringCellValue()));
				metas.add(price);
				maps.put(post, metas);
			}
			return maps;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}