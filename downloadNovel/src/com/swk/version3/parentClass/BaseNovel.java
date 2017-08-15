package com.swk.version3.parentClass;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.jsoup.nodes.Document;

import com.swk.version3.bean.Chapter;
import com.swk.version3.behaviorInterface.ConnectWebPageBehavior;
import com.swk.version3.behaviorInterface.HandleChapterContentBehavior;
import com.swk.version3.behaviorInterface.HandleChapterListBehavior;

/**
 * 
 * @copyright 神农大学生软件创新中心 版权所有 © 2017
 * 
 * @author 慎伟康
 * 
 * @version 1.0
 * 
 * @date 2017年8月2日 下午9:17:56
 * 
 * @Description TODO
 *    小说下载抽象类，提供了这个小爬虫模式化的小说下载套路：
 *        通过小说章节列表网页获取全部章节的URL，然后逐一获取单章文本，写入本地文件。
 *    由于各小说网站前端实现不一定相同，因此解析章节列表以及解析单章文本封装成各自一族算法。子类需要组合适合它的算法即可。
 *    基于策略模式
 */
public abstract class BaseNovel {
	
	/**
	 * 封装的3类行为
	 *     连接网页行为
	 *     解析小说章节列表行为
	 *     解析小说单章文本行为
	 */
	private ConnectWebPageBehavior connectWebPageBehavior;
	private HandleChapterListBehavior handleChapterListBehavior;
	private HandleChapterContentBehavior handleChapterContentBehavior;
	
	/**
	 * 动态设定行为
	 * @param connectWebPageBehavior
	 */
	public void setConnectWebPageBehavior(
			ConnectWebPageBehavior connectWebPageBehavior) {
		this.connectWebPageBehavior = connectWebPageBehavior;
	}

	public void setHandleChapterListBehavior(
			HandleChapterListBehavior handleChapterListBehavior) {
		this.handleChapterListBehavior = handleChapterListBehavior;
	}

	public void setHandleChapterContentBehavior(
			HandleChapterContentBehavior handleChapterContentBehavior) {
		this.handleChapterContentBehavior = handleChapterContentBehavior;
	}


	/**
	 * 获取小说章节列表
	 * @return
	 */
	public List<Chapter> getChapterList(String url){
		
		//根据URL获取HTML文档
		Document doc = connectWebPageBehavior.getDoc(url);
		if(doc == null){
			System.out.println("获取章节列表失败！");
			return null;
		}
		
		return handleChapterListBehavior.handleChapterList(doc);
	}
	
	
	/**
	 * 根据单章网页URL获取章节文本
	 * @param url	单章小说网址
	 * @return
	 */
	public String getChapter(String url){
		//根据URL获取单章网页
		Document doc = connectWebPageBehavior.getDoc(url);
		if(doc == null){
			System.out.println("获取章节失败！");
			return null;
		}
		
		return handleChapterContentBehavior.handleChapterContent(doc);
	}
	 
	/**
	 * 下载最新的指定数量的章节
	 * @param pathname
	 *     指定下载文件地址
	 * @param num
	 *     指定下载几章
	 */
	public void download(String url, String pathname, int num){
		//小说本地目录
		File file = new File(pathname);
		Writer writer = null;
		
		//临时URL和文本
		StringBuilder chapterUrl = new StringBuilder();
		StringBuilder chapterText = new StringBuilder();
		
		//获取章节列表
		List<Chapter> chapterList = this.getChapterList(url);
		
		//计时
		long l1 = System.currentTimeMillis();
		
		try {
			writer = new FileWriter(file);
			
			//下载最新的指定数量的章节
			for(int i = chapterList.size() - num; i < chapterList.size(); i++){
				//先获取单章网页URL，然后获取单章文本
				chapterUrl = new StringBuilder(chapterList.get(i).getChapterHref());
				chapterText = new StringBuilder(this.getChapter(chapterUrl.toString()));
				
				if(chapterText == null || chapterText.length() < 20){
					System.out.println("获取失败，重新获取..." + chapterList.get(i).getChapterName());
					i--;//章节内容获取出错，返回，重新获取该章节，直到获取成功
				}else{
					//写入章节名及文本
					writer.write("\r\n\r\n" + chapterList.get(i).getChapterName() + "\r\n");
					writer.write(chapterText.toString());
					System.out.println(chapterList.get(i).getChapterName());//输出章节名
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{//关闭资源
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		long l2 = System.currentTimeMillis();//计时
		System.out.println("ok...");
		System.out.println((l2 - l1) / 1.0 / 1000 + "s...");
		
	}
	
	
	/**
	 * 下载整本小说
	 * @param pathname	指定下载文件地址
	 */
	public void download(String url, String pathname){
		//获取章节列表长度
		List<Chapter> chapterList = this.getChapterList(url);
		this.download(url, pathname, chapterList.size());
	}
	
}
