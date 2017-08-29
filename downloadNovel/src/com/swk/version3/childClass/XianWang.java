package com.swk.version3.childClass;

import java.util.List;

import org.junit.Test;

import com.swk.version3.bean.Chapter;
import com.swk.version3.behaviorImplements.ConnectWithoutLogin;
import com.swk.version3.behaviorImplements.HandleContentById;
import com.swk.version3.behaviorImplements.HandleListForXw;
import com.swk.version3.parentClass.BaseNovel;

/**
 * 
 * @author 慎伟康
 * 
 * @date 2017年8月29日 下午12:11:11
 * 
 * @Description TODO
 *		本文件可下载《仙王》
 *			http://www.23us.cc/html/83/83623/
 *			http://www.23us.cc/html/83/83623/15293188.html
 */
public class XianWang extends BaseNovel {

	public XianWang() {
		super();
		setConnectWebPageBehavior(new ConnectWithoutLogin());
		setHandleChapterContentBehavior(new HandleContentById());
		setHandleChapterListBehavior(new HandleListForXw());
	}
	
	@Test
	public void testList(){//测试获取章节列表方法
		String url = "http://www.23us.cc/html/83/83623/";
		List<Chapter> list = getChapterList(url);
		for (Chapter chapter : list) {
			System.out.println(chapter.getChapterHref() + "-->" + chapter.getChapterName());
		}
		System.out.println(list.size());
	}
	
	@Test
	public void testChapter(){//测试获取单章文本方法
		String url = "http://www.23us.cc/html/83/83623/15293188.html";
		String s = getChapter(url);
		System.out.println(s);
	}
	
	public static void main(String[] args) {
		String url = "http://www.23us.cc/html/83/83623/";
		String pathname = "src/com/swk/novel/xiangwang.txt";
		
		XianWang xw = new XianWang();
//		xw.download(url, pathname, 10);
		xw.download(url, pathname);
	}
	
}
