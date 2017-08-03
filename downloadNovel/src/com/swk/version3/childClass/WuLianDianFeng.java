package com.swk.version3.childClass;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import com.swk.version3.behaviorImplements.ConnectWithoutLogin;
import com.swk.version3.behaviorImplements.HandleContentById;
import com.swk.version3.behaviorImplements.HandleListForWldf;
import com.swk.version3.parentClass.BaseNovel;

/**
 * 
 * @copyright 神农大学生软件创新中心 版权所有 © 2017
 * 
 * @author 慎伟康
 * 
 * @version 1.0
 * 
 * @date 2017年8月3日 下午5:02:20
 * 
 * @Description TODO
 *    本文件可下载《武炼巅峰》
 *        http://www.xxbiquge.com/0_347/
 *        http://www.xxbiquge.com/0_347/8862332.html
 */
public class WuLianDianFeng extends BaseNovel {
	
	/**
	 * 空参构造器，将合适的解析算法组合起来。
	 */
	public WuLianDianFeng() {
		super();
		setConnectWebPageBehavior(new ConnectWithoutLogin());
		setHandleChapterContentBehavior(new HandleContentById());
		setHandleChapterListBehavior(new HandleListForWldf());
	}
	
	
	@Test
	public void testList(){//测试获取章节列表方法
		String url = "http://www.xxbiquge.com/0_347/";
		List<HashMap<String, String>> list = getChapterList(url);
		for (HashMap<String, String> h : list) {
			System.out.println(h.get("chapterHref") + "--->" + h.get("chapterName"));
		}
		System.out.println(list.size());
	}
	
	
	@Test
	public void testChapter(){//测试获取单章文本方法
		String url = "http://www.xxbiquge.com/0_347/8862332.html";
		String s = getChapter(url);
		System.out.println(s);
	}
	
	
	//下载小说主方法
	public static void main(String[] args) {
		String url = "http://www.xxbiquge.com/0_347/";
		String pathname = "src/com/swk/novel/wldf.txt";
		
		WuLianDianFeng wldf = new WuLianDianFeng();
		wldf.download(url, pathname, 30);
//		wldf.download(url, pathname);
		
	}//main

}//class
