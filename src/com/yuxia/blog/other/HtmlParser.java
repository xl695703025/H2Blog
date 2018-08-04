package com.yuxia.blog.other;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlParser {
	/**
	 * 获取第一张图片
	 */
	public static String getImg(String s)
    {
        String regex;
        List<String> list = new ArrayList<String>();
        regex = "src=\"(.*?)\"";
        Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
        Matcher ma = pa.matcher(s);
        while (ma.find())
        {
            list.add(ma.group());
        }
        if(list.size()>0){
            return list.get(0).substring(5,list.get(0).length()-1);
        }else{
        	return "";
        }
        
    }
	/**
	 * 获取html纯文本
	 */
	public static String parseHtml(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
	        String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
	        String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
	        p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
	        m_script = p_script.matcher(htmlStr);
	        htmlStr = m_script.replaceAll(" "); // 过滤script标签
	        p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
	        m_style = p_style.matcher(htmlStr);
	        htmlStr = m_style.replaceAll(" "); // 过滤style标签
	        p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
	        m_html = p_html.matcher(htmlStr);
	        htmlStr = m_html.replaceAll(" "); // 过滤html标签
	        textStr = htmlStr;
	    } catch (Exception e) {System.err.println("Html2Text: " + e.getMessage()); }
		//剔除空格行
		textStr=textStr.replaceAll("[ ]+", " ");
		textStr=textStr.replaceAll("(?m)^\\s*$(\\n|\\r\\n)", "  ");
		return textStr;// 返回文本字符串
	}
	public static String parseHtml(String inputString,int length) {
		String text=parseHtml(inputString);
		if(length>text.length())
			return text.substring(0,text.length());
		else
			return text.substring(0,length)+"...";
	}
	
	
	public static void main(String[] args) {
		System.out.println(getImg("<h1><img src=\"www.baidu.com\" /><em>测试内容</em></h1><p><img style=\"max-width: 100%;\" src=\"http://139.199.153.159:8080/H2Blog/statics/uploadImg/1531135019324.jpg\"></p><p><br></p>"));
		System.out.println(parseHtml("<h1><img src=\"www.baidu.com\" /><em>测试内容</em></h1><p><img style=\"max-width: 100%;\" src=\"http://139.199.153.159:8080/H2Blog/statics/uploadImg/1531135019324.jpg\"></p>sss<p>ss<br>sss</p>",5));
	}
}
