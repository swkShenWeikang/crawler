package com.swk.version3.behaviorImplements;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.swk.version3.behaviorInterface.HandleChapterContentBehavior;

/**
 * 
 * @copyright 神农大学生软件创新中心 版权所有 © 2017
 * 
 * @author 慎伟康
 * 
 * @version 1.0
 * 
 * @date 2017年8月3日 下午3:04:37
 * 
 * @Description TODO
 *    适用于网站将小说文本写在id为content的标签内
 *        <div id="content">
 */
public class HandleContentById implements HandleChapterContentBehavior {

	@Override
	public String handleChapterContent(Document doc) {
		//返回结果
		StringBuilder sb = new StringBuilder("\r\n");
		
		//获取章节文本
		Element ele = doc.getElementById("content");
		//处理HTML中的&nbsp;非传统空格，转换为正常空格。
		String chapter = ele.text().replace(Jsoup.parse("&nbsp;").text(), " ");
		//处理文本，根据空格分段
		String[] ss = chapter.split(" ");
		//将各段写入字符串，且换行
		for (String string : ss) {
			if(string.length() > 1){
				sb.append(string).append("\r\n");
			}
		}
		return sb.toString();
	}

}
