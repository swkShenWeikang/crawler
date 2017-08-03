package com.swk.version3.behaviorImplements;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import com.swk.version3.behaviorInterface.ConnectWebPageBehavior;

/**
 * 
 * @copyright 神农大学生软件创新中心 版权所有 © 2017
 * 
 * @author 慎伟康
 * 
 * @version 1.0
 * 
 * @date 2017年8月2日 下午9:34:18
 * 
 * @Description TODO
 *    通过jsoup连接网页，且无需用户登录
 */
public class ConnectWithoutLogin implements ConnectWebPageBehavior {

	@Override
	public Document getDoc(String url) {
		//返回结果
		Document doc = null;
		
		//模拟浏览器
		String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31";
		
		try {
			doc = Jsoup.connect(url).timeout(5000).userAgent(userAgent).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}

}
