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
 * @date 2017年8月2日 下午9:06:14
 * 
 * @Description TODO
 *		连接网页行为接口
 */
public interface ConnectWebPageBehavior {
	
	/**
	 * 通过URL获取Document对象
	 * @param url
	 *     网页地址
	 * @return
	 *     Document对象
	 */
	public Document getDoc(String url);
	
}
