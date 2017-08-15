package com.swk.version3.childClass;

import java.util.List;
import org.junit.Test;
import com.swk.version3.bean.Chapter;
import com.swk.version3.behaviorImplements.ConnectWithoutLogin;
import com.swk.version3.behaviorImplements.HandleContentById;
import com.swk.version3.behaviorImplements.HandleListForYnyh;
import com.swk.version3.parentClass.BaseNovel;

/**
 * 
 * @copyright 神农大学生软件创新中心 版权所有 © 2017
 * 
 * @author 慎伟康
 * 
 * @version 1.0
 * 
 * @date 2017年8月3日 下午4:54:25
 * 
 * @Description TODO
 *    本文件可下载《一念永恒》
 *        http://www.biqukan.com/1_1094/
 *        http://www.biqukan.com/1_1094/14700525.html
 */
public class YiNianYongHeng extends BaseNovel {
	
	/**
	 * 空参构造器，将合适的解析算法组合起来。
	 */
	public YiNianYongHeng() {
		super();
		setConnectWebPageBehavior(new ConnectWithoutLogin());
		setHandleChapterContentBehavior(new HandleContentById());
		setHandleChapterListBehavior(new HandleListForYnyh());		
	}

	
	@Test
	public void testList(){//测试获取章节列表方法
		String url = "http://www.biqukan.com/1_1094/";
		List<Chapter> list = getChapterList(url);
		for (Chapter chapter : list) {
			System.out.println(chapter.getChapterHref() + "-->" + chapter.getChapterName());
		}
		System.out.println(list.size());
	}
	
	
	@Test
	public void testChapter(){//测试获取单章文本方法
		String url = "http://www.biqukan.com/1_1094/14700525.html";
		String s = getChapter(url);
		System.out.println(s);
	}
	
	
	//下载小说主方法
	public static void main(String[] args) {
		String url = "http://www.biqukan.com/1_1094/";
		String pathname = "src/com/swk/novel/ynyh.txt";
		
		YiNianYongHeng ynyh = new YiNianYongHeng();
		ynyh.download(url, pathname, 30);
//		ynyh.download(url, pathname);
		
	}//main

}//class
