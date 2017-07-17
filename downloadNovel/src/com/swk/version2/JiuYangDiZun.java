package com.swk.version2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

/**
 * 
 * @copyright ：神农大学生软件创新中心 版权所有 © 2017
 * 
 * @author 14信息慎伟康
 * 
 * @version 1.0
 * 
 * @date 2017年7月16日 下午5:55:38
 * 
 * @Description TODO
 *		本文件可下载《九阳帝尊》	http://www.31xs.net/0/349/
 *							http://www.31xs.net/0/349/6342797.html
 */
public class JiuYangDiZun extends BaseNovel{
	
	
	@Override
	public List<HashMap<String, String>> getChapterList() {
		//返回结果
		List<HashMap<String, String>> chapterList = new ArrayList<HashMap<String, String>>();
		
		//章节列表网页的URL，这里写死是因为每个网站情况不一定相同
		String url = "http://www.31xs.net/0/349/";
		//根据URL获取HTML文档
		Document doc = getDoc(url);
		if(doc == null){
			System.out.println("获取章节列表失败！");
			return null;
		}
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
		
		return chapterList;
	}
	
	@Test
	public void testList(){
		List<HashMap<String, String>> list = getChapterList();
		for (HashMap<String, String> h : list) {
			System.out.println(h.get("chapterHref") + "    " + h.get("chapterName"));
		}
		System.out.println(list.size());
	}

	////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public String getChapter(String url) {
		//返回结果
		StringBuilder sb = new StringBuilder("\r\n");
		
		//根据URL获取单章网页
		Document doc = getDoc(url);
		if(doc == null){
			System.out.println("获取章节失败！");
			return null;
		}
		
		//获取章节文本
		Element ele = doc.getElementById("content");
		String chapter = ele.text();
		
		//处理文本，分段
		String[] ss = chapter.split(" ");
		
		for (String string : ss) {
			if(string.length() >= 4){
				sb.append(string.substring(3)).append("\r\n");
			}else{
				sb.append(string).append("\r\n");
			}
		}
		
		return sb.toString();
	}

	@Test
	public void testChapter(){
		String url = "http://www.31xs.net/0/349/6342797.html";
		String s = getChapter(url);
		System.out.println(s);
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	

	public static void main(String[] args) {
		JiuYangDiZun jiu = new JiuYangDiZun();
		String pathname = "src/com/swk/novel/jydz.txt";
		jiu.download(pathname, 20);
//		jiu.download(pathname);
	}
}
