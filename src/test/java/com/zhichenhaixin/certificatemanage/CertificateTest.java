package com.zhichenhaixin.certificatemanage;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.zhichenhaixin.certificatemanage.service.CertificateService;

import junit.framework.Assert;

public class CertificateTest extends TmallApplicationTests{
	@Autowired
	private CertificateService service;
	@Test
	public void testUploadData(){
		JSONObject obj = new JSONObject();
		obj.put("topic", "addCert");
		JSONObject data = new JSONObject();
		
		data.put("id", "0");
		data.put("userName", "123");
		data.put("type", "client");
		
		obj.put("data", data);
		JSONObject res = service.uploadData(JSONObject.toJSONString(obj));
		System.out.println(JSONObject.toJSONString(res));
		/**
		 * 第一个参数：如果测试不通过，会抛出此消息，此参数可不要；
		         第二个参数：我预期的值，我这里希望他查出来的结果是600；
		         第三个参数：是实际的结果，就是我们调用方法返回的结果
		 */
		//Assert.assertSame("操作失败", 1, service.uploadData(JSONObject.toJSONString(obj)));
	}
}
