package com.swk.version2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * 
 * @copyright ：神农大学生软件创新中心 版权所有 © 2017
 * 
 * @author 14信息慎伟康
 * 
 * @version 1.0
 * 
 * @date 2017年7月17日 上午10:04:43
 * 
 * @Description TODO
 *		小说爬虫父类，写有公共方法
 */
public class BaseNovel {
	
	/**
	 * 获取网络页面
	 * @param url
	 * @return
	 */
	public static Document getDoc(String url){
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
	
	/**
	 * 获取小说章节列表，，，各网站前端实现不同，该方法必须覆写
	 * @return
	 */
	public List<HashMap<String, String>> getChapterList(){
		return null;
	}
	
	/**
	 * 根据单章网页URL获取章节文本，，，目前实现代码，小说内容在id为content的div下，若其他网站实现不同，必须覆写
	 * @param url	单章小说网址
	 * @return
	 */
	public String getChapter(String url){
		//返回结果
		StringBuilder sb = new StringBuilder("\r\n");
		
		//根据URL获取单章网页
		Document doc = getDoc(url);
		if(doc == null){
			System.out.println("获取章节失败！");
			return null;
		}
		
		//获取章节文本
		Element ele = doc.getElementById("content");
		//处理HTML中的&nbsp;非传统空格，转换为正常空格。
		String chapter = ele.text().replace(Jsoup.parse("&nbsp;").text(), " ");
		
		//处理文本，分段
		String[] ss = chapter.split(" ");
		
		for (String string : ss) {
			if(string.length() > 1){
				sb.append(string).append("\r\n");
			}
		}
		return sb.toString();
	}
	
	/**
	 * 下载最新的指定数量的章节
	 * @param pathname	指定下载文件地址
	 * @param num		指定下载几章
	 */
	public void download(String pathname, int num){
		//小说本地目录
		File file = new File(pathname);
		Writer writer = null;
		
		//临时URL和文本
		StringBuilder chapterUrl = new StringBuilder();
		StringBuilder chapterText = new StringBuilder();
		
		//获取章节列表
		List<HashMap<String, String>> chapterList = this.getChapterList();
		
		long l1 = System.currentTimeMillis();//计时
		try {
			writer = new FileWriter(file);
			
			//下载最新的指定数量的章节
			for(int i = chapterList.size() - num; i < chapterList.size(); i++){
				chapterUrl = new StringBuilder(chapterList.get(i).get("chapterHref"));
				chapterText = new StringBuilder(this.getChapter(chapterUrl.toString()));
				
				if(chapterText == null || chapterText.length() < 20){
					System.out.println("获取失败，重新获取..." + chapterList.get(i).get("chapterName"));
					i--;//章节内容获取出错，返回，重新获取该章节，直到获取成功
				}else{
					writer.write("\r\n\r\n" + chapterList.get(i).get("chapterName") + "\r\n");//写入章节名
					writer.write(chapterText.toString());//写入文本
					System.out.println(chapterList.get(i).get("chapterName"));//输出章节名
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		long l2 = System.currentTimeMillis();//计时
		System.out.println("ok...");
		System.out.println((l2 - l1) / 1.0 / 1000 + "秒...");
		
	}
	
	/**
	 * 下载整本小说
	 * @param pathname	指定下载文件地址
	 */
	public void download(String pathname){
		//获取章节列表长度
		List<HashMap<String, String>> chapterList = this.getChapterList();
		this.download(pathname, chapterList.size());
	}
	
}
