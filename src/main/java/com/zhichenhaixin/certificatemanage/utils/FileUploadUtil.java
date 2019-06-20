package com.zhichenhaixin.certificatemanage.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

/**
 * 文件上传工具类<br>
 * 
 * 
 * @author songxiaotong
 */
public abstract class FileUploadUtil {
	/**
	 * 日志记录器
	 */
	private static final Logger LOG = Logger.getLogger(FileUploadUtil.class);

	/**
	 * 将字符串转换为文件
	 * 
	 * @param data 字符串内容
	 * @param path 文件路径-具体到文件
	 * @return boolean
	 */
	public static boolean generateDoc(String data, String path) {
		if (data == null) {
			return false;
		}
		OutputStream out = null;
		
		path = BaseUtil.replaceAll(path);
		
		File file = new File(path.substring(0, path.lastIndexOf("/")));
		//判断文件是否存在，不存在就创建
		if(!file.exists()){
			file.mkdirs();
		}
		try {
			byte[] b = data.getBytes();

			// 处理数据
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			out = new FileOutputStream(path);
			out.write(b);
			out.flush();
			return true;
		} catch (IOException e) {
			LOG.error("转换为文件异常", e.fillInStackTrace());
			return false;
		} finally {
			IOUtils.closeQuietly(out);
		}

	}
public static void main(String[] args) {
	String a = "\\qdf\\a3/a//32/text.txt";
	System.out.println(BaseUtil.replaceAll(a));
}
}
