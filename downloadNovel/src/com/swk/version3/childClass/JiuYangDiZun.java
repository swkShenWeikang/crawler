package com.swk.version3.childClass;

import java.util.List;
import org.junit.Test;
import com.swk.version3.bean.Chapter;
import com.swk.version3.behaviorImplements.ConnectWithoutLogin;
import com.swk.version3.behaviorImplements.HandleContentById;
import com.swk.version3.behaviorImplements.HandleListForJydz;
import com.swk.version3.parentClass.BaseNovel;

/**
 * 
 * @copyright 神农大学生软件创新中心 版权所有 © 2017
 * 
 * @author 慎伟康
 * 
 * @version 1.0
 * 
 * @date 2017年8月3日 下午3:32:00
 * 
 * @Description TODO
 *    本文件可下载《九阳帝尊》
 *        http://www.31xs.net/0/349/
 *        http://www.31xs.net/0/349/6342797.html
 */
public class JiuYangDiZun extends BaseNovel {
	
	/**
	 * 空参构造器，将合适的解析算法组合起来。
	 */
	public JiuYangDiZun() {
		super();
		setConnectWebPageBehavior(new ConnectWithoutLogin());
		setHandleChapterContentBehavior(new HandleContentById());
		setHandleChapterListBehavior(new HandleListForJydz());
	}


	@Test
	public void testList(){//测试获取章节列表方法
		String url = "http://www.31xs.net/0/349/";
		List<Chapter> list = getChapterList(url);
		for (Chapter chapter : list) {
			System.out.println(chapter.getChapterHref() + "-->" + chapter.getChapterName());
		}
		System.out.println(list.size());
	}
	
	
	@Test
	public void testChapter(){//测试获取单章文本方法
		String url = "http://www.31xs.net/0/349/6342797.html";
		String s = getChapter(url);
		System.out.println(s);
	}
	
	
	//下载小说主方法
	public static void main(String[] args) {
		String url = "http://www.31xs.net/0/349/";
		String pathname = "src/com/swk/novel/jydz.txt";
		
		JiuYangDiZun jydz = new JiuYangDiZun();
		jydz.download(url, pathname, 30);
//		jydz.download(url, pathname);
		
	}//main

}//class
