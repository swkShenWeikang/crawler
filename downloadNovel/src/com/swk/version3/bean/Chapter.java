package com.swk.version3.bean;

/**
 * 
 * @copyright 神农大学生软件创新中心版权所有 © 2017
 * 
 * @author 慎伟康
 * 
 * @version 1.0
 * 
 * @date 2017年8月15日 下午12:25:17
 * 
 * @Description TODO
 *		
 */
public class Chapter {
	
	public Chapter() {
		super();
	}
	
	private String chapterName;//单章名称
	private String chapterHref;//单章地址
	
	public String getChapterName() {
		return chapterName;
	}
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	public String getChapterHref() {
		return chapterHref;
	}
	public void setChapterHref(String chapterHref) {
		this.chapterHref = chapterHref;
	}
	
}
