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
 * @date 2017年8月3日 下午5:41:51
 * 
 * @Description TODO
 *    下载《一念永恒》所需处理章节列表的行为实现。
 *        章节名称及URL都在a标签中：<a href="/1_1094/5403177.html">第一章 他叫白小纯</a>
 *        筛选算法为：URL以"/1_1094"开始且以".html"结尾
 */
public class HandleListForYnyh implements HandleChapterListBehavior {

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
			if(chapterHref.endsWith(".html") && chapterHref.startsWith("/1_1094")){
				Chapter chapter = new Chapter();
				chapter.setChapterName(chapterName);
				chapter.setChapterHref("http://www.biqukan.com" + chapterHref);
				chapterList.add(chapter);
			}
		}
		
		return chapterList;
	}

}
