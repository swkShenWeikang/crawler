package com.swk.crawler;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Txt {

	public static void main(String[] args) throws IOException {
		String chapter = null;
		StringBuilder sb = new StringBuilder("\r\n");
		String url = "http://www.31xs.net/0/349/5893285.html";
		
		//根据URL获取单章网页
		Document doc = Jsoup.connect(url).get();
		
		//获取章节文本
		Element ele = doc.getElementById("content");
		chapter = ele.text();
		
		//处理文本，分段
		String[] ss = chapter.split(" ");
		
		for (String string : ss) {
			sb.append(string.substring(2)).append("\r\n");
		}
		System.out.println(sb.toString());
		
	}

}
