package com.zhichenhaixin.certificatemanage.model;

import lombok.Getter;
import lombok.Setter;
/**
 * 查询条件实体类
 * @author pwl
 *
 */
@Getter
@Setter
public class CertificateVo {
	
	/*** 用户名  ****/
	private String userName;
	/*** 服务器IP  ****/
	private String ip;
	/*** 公司名称  ****/
	private String orgName;
	/*** 类型  ****/
	private String type;
	/*** MAC地址  ****/
	private String orgUnit;
	/*** 查询开始时间  ****/
	private long startTime;
	/*** 查询截止时间  ****/
	private long endTime;
	/*** 当前页  ****/
	private int currentPage = 1;
	/*** 每页显示记录数  ****/
	private int pageSize = 10;
}
