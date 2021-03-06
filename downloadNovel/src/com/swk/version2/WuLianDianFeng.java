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
 * @date 2017年7月17日 上午10:29:46
 * 
 * @Description TODO
 *		本文件可下载《武炼巅峰》	http://www.xxbiquge.com/0_347/
 *							http://www.xxbiquge.com/0_347/8862332.html
 */
public class WuLianDianFeng extends BaseNovel {
	
	@Override
	public List<HashMap<String, String>> getChapterList() {
		//返回结果
		List<HashMap<String, String>> chapterList = new ArrayList<HashMap<String, String>>();
		
		//章节列表网页的URL，这里写死是因为每个网站情况不一定相同
		String url = "http://www.xxbiquge.com/0_347/";
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
			if(chapterHref.endsWith(".html") && chapterHref.startsWith("/0_347")){
				HashMap<String, String> chapter = new HashMap<String, String>();
				chapter.put("chapterName", chapterName);
				chapter.put("chapterHref", "http://www.xxbiquge.com" + chapterHref);
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
	
	

	@Test
	public void testChapter(){
		String url = "http://www.xxbiquge.com/0_347/8862332.html";
		String s = getChapter(url);
		System.out.println(s);
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	
	
	
	public static void main(String[] args) {
		WuLianDianFeng wldf = new WuLianDianFeng();
		String pathname = "src/com/swk/novel/wldf.txt";
		wldf.download(pathname, 10);
//		wldf.download(pathname);
		
	}

}
