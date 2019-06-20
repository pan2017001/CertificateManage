package com.zhichenhaixin.certificatemanage.utils;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.zhichenhaixin.certificatemanage.constant.Constant;


/**
 * 返回消息工具类
 * 
 * @version V1.0
 * @author pwl
 * @date 2017年12月27日 下午1:52:48
 * @Description
 */
@Component
public abstract class ResultMsgUtil {
	/**
	 * 返回成功结果集
	 * 
	 * @param data 可选数据
	 * @return json结果集
	 */
	public static JSONObject success(Object data) {
		JSONObject json = new JSONObject();
		json.put(Constant.CODE_NAME, Constant.SUCCESS);
		json.put(Constant.RES_MSG, "操作成功");
		json.put(Constant.RES_DATA, data);
		return json;
	}
	/**
	 * 返回成功结果集
	 * 
	 * @param data 可选数据
	 * @param topic 主题  可选数据
	 * @return json结果集
	 */
	public static JSONObject success(Object data,String topic) {
		JSONObject json = new JSONObject();
		json.put(Constant.CODE_NAME, Constant.SUCCESS);
		json.put(Constant.RES_MSG, "操作成功");
		json.put(Constant.RES_DATA, data);
		json.put(Constant.RES_TOPIC, topic);
		return json;
	}

	/**
	 * 返回默认成功结果
	 * 
	 * @return json结果集
	 *
	 */
	public static JSONObject success() {
		JSONObject json = new JSONObject();
		json.put(Constant.CODE_NAME, Constant.SUCCESS);
		json.put(Constant.RES_MSG, "操作成功");
		return json;
	}

	/**
	 * 返回失败结果集
	 * 
	 * @param data 可选数据
	 * @return json结果集
	 */
	public static JSONObject error(Object data) {
		JSONObject json = new JSONObject();
		json.put(Constant.CODE_NAME, Constant.ERROR);
		json.put(Constant.RES_MSG, "操作失败");
		json.put(Constant.RES_DATA, data);
		return json;
	}

	/**
	 * 返回失败结果集
	 * 
	 * @param msg 错误消息
	 * @return json结果集
	 */
	public static JSONObject error(String msg) {
		JSONObject json = new JSONObject();
		json.put(Constant.CODE_NAME, Constant.ERROR);
		json.put(Constant.RES_MSG, msg);
		json.put(Constant.RES_DATA, null);
		return json;
	}
	/**
	 * 返回失败结果集
	 * 
	 * @param data 错误消息
	 * @param topic 主题
	 * @return json结果集
	 */
	public static JSONObject error(Object data,String topic) {
		JSONObject json = new JSONObject();
		json.put(Constant.CODE_NAME, Constant.ERROR);
		json.put(Constant.RES_MSG, "操作失败");
		json.put(Constant.RES_DATA, data);
		json.put(Constant.RES_TOPIC, topic);
		return json;
	}

	/**
	 * 表格数据组装
	 * 
	 * @param total 总条数
	 * @param rows 当前查询的行信息
	 * @return 表格组装的json格式数据
	 * 
	 */
	public static JSONObject tableData(Object total, Object rows) {
		JSONObject json = new JSONObject();
		json.put("total", total);
		json.put("rows", rows);
		return json;
	}
}
