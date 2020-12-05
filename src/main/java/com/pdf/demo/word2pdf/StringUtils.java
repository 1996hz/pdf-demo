package com.pdf.demo.word2pdf;

public class StringUtils {

	/**
	 * 验证字符串不为空
	 *
	 */
	public static boolean isNotNullOrEmpty(String str) {
		if (str != null && !str.equals(""))
			return true;
		else
			return false;
	}

	/**
	 * 验证字符串为空
	 *
	 */
	public static boolean isNullOrEmpty(String str) {
		if (str == null || str.equals(""))
			return true;
		else
			return false;
	}

	/**
	 * 判断是否为word文档格式
	 *
	 */
	public static boolean isWord(String fileName){
		String fileFromat = fileName.substring(0,fileName.lastIndexOf("."));
		if(".doc".equals(fileFromat) || ".docx".equals(fileFromat)){
			return true;
		}
		return false;
	}
}
