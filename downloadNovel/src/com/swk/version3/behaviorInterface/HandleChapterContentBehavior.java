package com.swk.version3.behaviorInterface;

import org.jsoup.nodes.Document;

/**
 * 
 * @copyright 神农大学生软件创新中心 版权所有 © 2017
 * 
 * @author 慎伟康
 * 
 * @version 1.0
 * 
 * @date 2017年8月2日 下午8:57:59
 * 
 * @Description TODO
 *		解析小说单章文本行为接口
 */
public interface HandleChapterContentBehavior {

	/**
	 * 在获取的单章节Document对象中解析出单章文本
	 * @param doc
	 *     从单章网页获取的Document对象
	 * @return
	 *     单章小说文本
	 */
	public String handleChapterContent(Document doc);
}
