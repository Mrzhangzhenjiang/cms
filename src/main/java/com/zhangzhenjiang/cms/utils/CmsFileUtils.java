/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: CmsFileUtils.java 
 * @Prject: chengongjun_cms
 * @Package: com.chengongjun.chengongjun_cms.utils 
 * @Description: TODO
 * @author: chj   
 * @date: 2019年7月25日 上午10:06:21 
 * @version: V1.0   
 */
package com.zhangzhenjiang.cms.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * @ClassName: CmsFileUtils
 * @Description: TODO
 * @author: chj
 * @date: 2019年7月25日 上午10:06:21
 */
public class CmsFileUtils {

	/**
	 * @Title: writeFile
	 * @Description: TODO
	 * @param fileName
	 * @param content
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @return: void
	 */
	public static void writeFile(String fileName, String content)
			throws UnsupportedEncodingException, FileNotFoundException, IOException {
		// 创建文件对象
		File file = new File(fileName);
		// 判断父目录是否存在，不存在创建目录
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		// 创建字符缓冲输出流
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf8"));
		// 写入文本内容
		bw.write(content);
		bw.flush();
		bw.close();
	}

	/**
	 * 
	 * @Title: readFile
	 * @Description: 读取指定的文件内容
	 * @param file
	 * @return
	 * @throws IOException
	 * @return: String
	 */
	public static String readFile(File file) throws IOException {
		// 创建sb对象
		StringBuffer sb = new StringBuffer();
		// 读取文章的内容
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf8"));
		// 读取文章内容
		String content = null;
		while ((content = br.readLine()) != null) {
			sb.append(content);
		}
		return sb.toString();
	}
}
