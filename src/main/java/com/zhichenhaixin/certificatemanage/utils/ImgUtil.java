package com.zhichenhaixin.certificatemanage.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

// import sun.misc.BASE64Decoder;
// import sun.misc.BASE64Encoder;

/**
 * 图片工具类<br>
 * 1.提供图片base64互相转换功能
 * 
 * @author songxiaotong
 */
public abstract class ImgUtil {
	/**
	 * 日志记录器
	 */
	private static final Logger LOG = Logger.getLogger(ImgUtil.class);

	/**
	 * 将base64编码字符串转换为图片
	 * 
	 * @param imgStr base64编码字符串
	 * @param path 图片路径-具体到文件
	 * @return boolean
	 */
	public static boolean generateImage(String imgStr, String path) {
		if (imgStr == null) {
			return false;
		}
		// BASE64Decoder decoder = new BASE64Decoder();
		OutputStream out = null;
		try {
			// 解密
			// byte[] b = decoder.decodeBuffer(imgStr);
			byte[] b = Base64.getDecoder().decode(imgStr);

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
			LOG.error("base64字符串转换为图片异常", e.fillInStackTrace());
			return false;
		} finally {
			IOUtils.closeQuietly(out);
		}

	}

	/**
	 * 根据图片地址转换为base64编码字符串
	 * 
	 * @param imgFile 图片地址
	 * @return String 图片转换后的base64字符串
	 */
	public static String getImageStr(String imgFile) {
		InputStream inputStream = null;
		byte[] data = null;
		try {
			inputStream = new FileInputStream(imgFile);
			data = new byte[inputStream.available()];
			inputStream.read(data);
		} catch (IOException e) {
			LOG.error("图片转换为base64字符串异常", e.fillInStackTrace());
		} finally {
			IOUtils.closeQuietly(inputStream);
		}
		// 加密
		// BASE64Encoder encoder = new BASE64Encoder();
		// return encoder.encode(data);
		return Base64.getEncoder().encodeToString(data);
	}

	public static String base64toStr(String str) {
		try {
			byte[] bytes = Base64.getDecoder().decode(str);
			return new String(bytes,"GBK");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	/**
	 * 示例
	 */
	public static void main(String[] args) {
		byte[] bytes;
		try {
			bytes = "鲁".getBytes("GBK");
//			byte[] base64Str = Base64.getUrlEncoder().encodeToString("wrNLODk2N0s=");
			bytes = Base64.getDecoder().decode("wrNLODk2N0s=");
			String str1 = new String(bytes,"GBK");
			String str2 = new String(bytes,"UTF-8");
//			int i = (int)'鲁';
			System.out.println(str1 +"------>"+ str2);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
//		String strImg = getImageStr("F://workfile//GoogleDowons//girl.jpg");
//		System.out.println(strImg);
//		System.out.println(strImg.length());
//		generateImage(strImg, "F:" + "//" + System.currentTimeMillis() + ".jpg");
	}
}
