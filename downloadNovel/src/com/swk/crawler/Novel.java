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
 *		本文件可下载《九阳帝尊》	http://www.31xs.net/0/349/
 */
public class Novel {
	
	/**
	 * 根据章节列表网页的URL，获取所有章节名及对应的链接
	 * @return
	 */
	public static List<HashMap<String, String>> getChapterList(){
		List<HashMap<String, String>> chapterList = new ArrayList<HashMap<String, String>>();
		
		//章节列表网页的URL
		String url = "http://www.31xs.net/0/349/";
		
		try {
			//根据URL获取HTML文档
			Document doc = Jsoup.connect(url).get();
			
			//根据标签获取章节列表
			Elements list = doc.getElementsByTag("a");
			for (Element element : list) {
				
				String chapterName = element.text();//章节名
				String chapterHref = element.attr("href");//链接
				
				//根据网页情况，筛选需要的
				if(chapterHref.endsWith(".html") && chapterHref.length() < 15){
					HashMap<String, String> chapter = new HashMap<String, String>();
					chapter.put("chapterName", chapterName);
					chapter.put("chapterHref", url + chapterHref);
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
			Element ele = doc.getElementById("content");
			chapter = ele.text();
			
			//处理文本，分段
			String[] ss = chapter.split(" ");
			
			for (String string : ss) {
				sb.append(string.substring(3)).append("\r\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	/**
	 * 下载小说
	 * @throws IOException
	 */
	public static void download() throws IOException{
		StringBuilder chapterUrl = new StringBuilder();
		StringBuilder chapterText = new StringBuilder();
		
		File file = new File("src/com/swk/novel/aaa.txt");//小说本地目录
		Writer w = new FileWriter(file);
		
		//获取章节列表
		List<HashMap<String, String>> chapterList = Novel.getChapterList();
		
		long l1 = System.currentTimeMillis();//计时
		
		for (HashMap<String, String> chapter : chapterList) {
			chapterUrl = new StringBuilder(chapter.get("chapterHref"));
			chapterText = new StringBuilder(Novel.getChapter(chapterUrl.toString()));
			
			w.write(chapter.get("chapterName"));//写入章节名
			System.out.println(chapter.get("chapterName"));//输出章节名
			w.write(chapterText.toString());//写入文本
		}
		
//		for(int i = 0; i < 3; i++){
//			chapterUrl = new StringBuilder(chapterList.get(i).get("chapterHref"));
//			chapterText = new StringBuilder(Novel.getChapter(chapterUrl.toString()));
//			
//			w.write(chapterList.get(i).get("chapterName"));//写入章节名
//			System.out.println(chapterList.get(i).get("chapterName"));//输出章节名
//			w.write(chapterText.toString());//写入文本
//		}
		
		w.flush();
		w.close();
		
		long l2 = System.currentTimeMillis();//计时
		System.out.println("ok...");
		System.out.println(l2 - l1);
	}

	
	public static void main(String[] args) throws IOException {
		Novel.download();
	}

}
