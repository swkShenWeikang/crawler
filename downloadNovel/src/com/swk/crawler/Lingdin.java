package com.swk.crawler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * @copyright ：神农大学生软件创新中心 版权所有 © 2017
 * 
 * @author 14信息慎伟康
 * 
 * @version 1.0
 * 
 * @date 2017年6月8日 下午1:24:26
 * 
 * @Description TODO
 *		本文件可下载《灵鼎》	http://www.xbiquge520.com/mulu/3500/
 *						http://www.xbiquge520.com/book_3500/1607328.html
 */
public class Lingdin {
	
	/**
	 * 根据章节列表网页的URL，获取所有章节名及对应的链接
	 * @return
	 */
	public static List<HashMap<String, String>> getChapterList(){
		List<HashMap<String, String>> chapterList = new ArrayList<HashMap<String, String>>();
		
		//章节列表网页的URL
		String url = "http://www.xbiquge520.com/mulu/3500/";
		
		try {
			//根据URL获取HTML文档
			Document doc = Jsoup.connect(url).get();
			
			//根据标签获取章节列表
			Elements list = doc.getElementsByTag("a");
			for (Element element : list) {
				
				String chapterName = element.text();//章节名
				String chapterHref = element.attr("href");//链接
				
				//根据网页情况，筛选需要的
				if(chapterHref.endsWith(".html") && chapterHref.startsWith("/book_3500")){
					HashMap<String, String> chapter = new HashMap<String, String>();
					chapter.put("chapterName", chapterName);
					chapter.put("chapterHref", "http://www.xbiquge520.com" + chapterHref);
					chapterList.add(chapter);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return chapterList;
	}
	
	/**
	 * 根据单章网页URL获取章节文本
	 * @param url
	 * @return
	 */
	public static String getChapter(String url){
		String chapter = null;
		StringBuilder sb = new StringBuilder("\r\n");
		try {
			//根据URL获取单章网页
			Document doc = Jsoup.connect(url).get();
			
			//获取章节文本
			Element ele = doc.getElementById("novel-content");
			chapter = ele.text();
			
			if(chapter.contains("百 度 搜 索")){
				int index = chapter.indexOf("百 度 搜 索");
				chapter = chapter.substring(0, index-1);
			}
			//处理文本，分段
			String[] ss = chapter.split(" ");
			
			for (String string : ss) {
//				if(string.length() >= 4){
//					sb.append(string.substring(3)).append("\r\n");
//				}else{
//				}
				sb.append(string).append("\r\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//return chapter;
		return sb.toString();
	}
	
	/**
	 * 下载小说
	 * @throws IOException
	 */
	public static void download() throws IOException{
		StringBuilder chapterUrl = new StringBuilder();
		StringBuilder chapterText = new StringBuilder();
		
		File file = new File("src/com/swk/novel/lingdin.txt");//小说本地目录
		File errorFile = new File("src/com/swk/novel/error.txt");//错误章节目录
		Writer w = new FileWriter(file);
		Writer w2 = new FileWriter(errorFile);
		
		//获取章节列表
		List<HashMap<String, String>> chapterList = Lingdin.getChapterList();
		
		long l1 = System.currentTimeMillis();//计时
		
//		for (HashMap<String, String> chapter : chapterList) {
//			chapterUrl = new StringBuilder(chapter.get("chapterHref"));
//			chapterText = new StringBuilder(Wuliandianfeng.getChapter(chapterUrl.toString()));
//			
//			if(chapterText == null || chapterText.length() < 20){
//				w2.write(chapter.get("chapterName"));//写入错误章节名
//				w.write(chapter.get("chapterName"));//写入章节名
//			}else{
//				w.write(chapter.get("chapterName"));//写入章节名
//				System.out.println(chapter.get("chapterName"));//输出章节名
//				w.write(chapterText.toString());//写入文本
//			}
//		}
		
		for(int i = 9; i < chapterList.size(); i++){
			chapterUrl = new StringBuilder(chapterList.get(i).get("chapterHref"));
			chapterText = new StringBuilder(Lingdin.getChapter(chapterUrl.toString()));
			
			if(chapterText == null || chapterText.length() < 20){
				w2.write(chapterList.get(i).get("chapterName"));//写入错误章节名
				w.write(chapterList.get(i).get("chapterName"));//写入章节名
			}else{
				w.write(chapterList.get(i).get("chapterName"));//写入章节名
				System.out.println(chapterList.get(i).get("chapterName"));//输出章节名
				w.write(chapterText.toString());//写入文本
				w.write("\r\n");
			}
		}
		
		w2.flush();
		w2.close();
		w.flush();
		w.close();
		
		long l2 = System.currentTimeMillis();//计时
		System.out.println("ok...");
		System.out.println(l2 - l1);
	}

	
	public static void main(String[] args) throws IOException {
//		List<HashMap<String, String>> l = Lingdin.getChapterList();
//		for (HashMap<String, String> h : l) {
//			System.out.println(h.get("chapterHref") + "    " + h.get("chapterName"));
//		}
//		System.out.println(l.size());
		
//		String url = "http://www.xbiquge520.com/book_3500/1607328.html";
//		String s = Lingdin.getChapter(url);
//		System.out.println(s);
		
		Lingdin.download();
	}

}
