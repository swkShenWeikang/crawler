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
 * @date 2017年7月17日 上午10:16:41
 * 
 * @Description TODO
 *		本文件可下载《一念永恒》	http://www.biqukan.com/1_1094/
 *							http://www.biqukan.com/1_1094/14700525.html
 */
public class YiNianYongHeng extends BaseNovel {
	
	@Override
	public List<HashMap<String, String>> getChapterList() {
		//返回结果
		List<HashMap<String, String>> chapterList = new ArrayList<HashMap<String, String>>();
		
		//章节列表网页的URL，这里写死是因为每个网站情况不一定相同
		String url = "http://www.biqukan.com/1_1094/";
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
			if(chapterHref.endsWith(".html") && chapterHref.startsWith("/1_1094")){
				HashMap<String, String> chapter = new HashMap<String, String>();
				chapter.put("chapterName", chapterName);
				chapter.put("chapterHref", "http://www.biqukan.com" + chapterHref);
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
	
	//////////////////////////////////////////////////////////////////////////////////
	
	
	@Test
	public void testChapter(){
//		http://www.biqukan.com/1_1094/14640944.html    第831章 强者齐聚！
//		http://www.biqukan.com/1_1094/15198284.html    第914章 叵测人心
//		String url = "http://www.biqukan.com/1_1094/14640944.html";
		String url = "http://www.biqukan.com/1_1094/15198284.html";
		String s = getChapter(url);
		System.out.println(s);
	}
	
	//////////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) {
		YiNianYongHeng ynyh = new YiNianYongHeng();
		String pathname = "src/com/swk/novel/ynyh.txt";
		ynyh.download(pathname, 20);
//		ynyh.download(pathname);
		
	}

}
