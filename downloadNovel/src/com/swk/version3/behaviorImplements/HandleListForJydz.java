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
 * @copyright 神农大学生软件创新中心 版权所有 © 2017
 * 
 * @author 慎伟康
 * 
 * @version 1.0
 * 
 * @date 2017年8月3日 下午3:21:06
 * 
 * @Description TODO
 *    下载《九阳帝尊》所需处理章节列表的行为实现。
 *        章节名称及URL都在a标签中：<a href="5893203.html">第一章 仙山下的失足少年</a>
 *        筛选算法为：URL以".html"结尾且整体长度不超过15
 */
public class HandleListForJydz implements HandleChapterListBehavior {

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
			if(chapterHref.endsWith(".html") && chapterHref.length() < 15){
				Chapter chapter = new Chapter();
				chapter.setChapterName(chapterName);
				chapter.setChapterHref("http://www.31xs.net/0/349/" + chapterHref);
				chapterList.add(chapter);
			}
		}
		
		return chapterList;
	}

}
