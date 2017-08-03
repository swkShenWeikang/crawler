package com.swk.version3.behaviorInterface;

import java.util.HashMap;
import java.util.List;
import org.jsoup.nodes.Document;

/**
 * 
 * @copyright 神农大学生软件创新中心 版权所有 © 2017
 * 
 * @author 慎伟康
 * 
 * @version 1.0
 * 
 * @date 2017年8月2日 下午8:57:24
 * 
 * @Description TODO
 *		解析小说章节列表行为接口
 */
public interface HandleChapterListBehavior {
	
	/**
	 * 在获取的章节列表Document对象中解析出所有的章节名称及单章URL
	 * @param doc
	 *     从章节列表网页获取的Document对象
	 * @return
	 *     章节列表数组，一个map中有两个参数
	 *         chapterName    单章名称
	 *         chapterHref    单章URL
	 */
	public List<HashMap<String, String>> handleChapterList(Document doc);
	
}
