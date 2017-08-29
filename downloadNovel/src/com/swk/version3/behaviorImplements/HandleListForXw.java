package com.swk.version3.behaviorImplements;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.swk.version3.bean.Chapter;
import com.swk.version3.behaviorInterface.HandleChapterListBehavior;

/**
 * 
 * @author 慎伟康
 * 
 * @date 2017年8月29日 下午12:16:34
 * 
 * @Description TODO
 *	下载《仙王》所需处理章节列表的行为实现。
 *		章节名称及URL都在a标签中：<a style=""=style="" href="15194855.html">第一章 吹牛落下的祸根!</a>
 *		筛选算法为：URL以".html"结尾且长度限制
 */
public class HandleListForXw implements HandleChapterListBehavior {

	@Override
	public List<Chapter> handleChapterList(Document doc) {
		//返回结果
		List<Chapter> chapterList = new ArrayList<Chapter>();
		
		//根据标签获取章节列表
		Elements list = doc.getElementsByTag("a");
		for (Element element : list) {
			String chapterName = element.text();//章节名
			String chapterHref = element.attr("href");//链接
			
			//根据网页情况，筛选需要的
			if(chapterHref.endsWith(".html") && "16792034.html".length() == chapterHref.length()){
				Chapter chapter = new Chapter();
				chapter.setChapterName(chapterName);
				chapter.setChapterHref("http://www.23us.cc/html/83/83623/" + chapterHref);
				chapterList.add(chapter);
			}
		}
		
		return chapterList;
	}

}
