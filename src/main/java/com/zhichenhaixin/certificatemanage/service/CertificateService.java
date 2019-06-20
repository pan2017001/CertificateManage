package com.zhichenhaixin.certificatemanage.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.zhichenhaixin.certificatemanage.dao.CertificateDao;
import com.zhichenhaixin.certificatemanage.model.CertificateInfo;
import com.zhichenhaixin.certificatemanage.model.CertificateVo;
import com.zhichenhaixin.certificatemanage.model.PageBean;
import com.zhichenhaixin.certificatemanage.utils.BaseUtil;
import com.zhichenhaixin.certificatemanage.utils.FileUploadUtil;
import com.zhichenhaixin.certificatemanage.utils.ResourceUtil;
import com.zhichenhaixin.certificatemanage.utils.ResultMsgUtil;
import com.zhichenhaixin.certificatemanage.config.ServerConfig;
/**
 * 数字证书服务类
 * @author pwl
 *
 */
@Service("certificateService")
public class CertificateService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CertificateService.class);
	@Autowired
	private CertificateDao dao;
	@Autowired
	private ServerConfig serverConfig;
	
    @Value("${server.servlet.context-path}")
    private String contextPath;
    /**
     * 上传（新增或更新）证书信息，包括信息及评书文件
     * @param json
     * @return
     */
	public JSONObject uploadData(String json){
		LOGGER.info("接收到的客户端JSON：" + json);
		JSONObject obj = JSONObject.parseObject(json);
		String topic = BaseUtil.trim(obj.get("topic"));
		
		String httpPath = serverConfig.getUrl() + contextPath;
		
		CertificateInfo cert = new CertificateInfo();
		
		//新增或更新
		if("addCert".equals(topic) || "modCert".equals(topic)){
			//解析码数
			JSONObject data = JSONObject.parseObject(BaseUtil.trim(obj.get("data")));
			String type = BaseUtil.trim(data.get("type"));
			int id = Integer.parseInt(BaseUtil.trim(data.get("sourceKey"),"0"));
			//文件夹分类路径
			String typePath = "/" + type + "/" + BaseUtil.trim(data.get("time"));
			//组装数据
			cert.setUserName(BaseUtil.trim(data.get("userName")));
			cert.setCountry(BaseUtil.trim(data.get("country")));
			cert.setCountryName(BaseUtil.trim(data.get("countryName")));
			cert.setEmail(BaseUtil.trim(data.get("email")));
			cert.setLocality(BaseUtil.trim(data.get("locality")));
			cert.setOrgName(BaseUtil.trim(data.get("orgName")));
			cert.setOrgUnit(BaseUtil.trim(data.get("orgUnit")));
			cert.setProvince(BaseUtil.trim(data.get("province")));
			cert.setSourceKey(BaseUtil.trim(data.get("sourceKey")));
			cert.setType(BaseUtil.trim(data.get("type")));
			cert.setValidDays(BaseUtil.trim(data.get("validDays")));
			cert.setTime(Long.parseLong(BaseUtil.trim(data.get("time"),"0")));
			cert.setIp(BaseUtil.trim(data.get("ip")));
			
			//上传文件到硬盘中并生成下载的URL
			String path = ResourceUtil.fileUploadPaht + "/" + typePath + "/" + BaseUtil.trim(data.get("keyFile"));
			cert.setKeyFile(BaseUtil.trim(data.get("keyFile")));
			//上传是否成功
			boolean isok = FileUploadUtil.generateDoc(BaseUtil.trim(data.get("keyFileContent")), path);
			path = httpPath + typePath  + path.substring(path.lastIndexOf("/"), path.length());
			//上传成功，把URL写入数据库
			if(isok) cert.setKeyFileContent(path);
			//上传文件到硬盘中并生成下载的URL
			path = ResourceUtil.fileUploadPaht + "/" + typePath + "/" + BaseUtil.trim(data.get("caCertFile"));
			cert.setCaCertFile(BaseUtil.trim(data.get("caCertFile")));
			isok = FileUploadUtil.generateDoc(BaseUtil.trim(data.get("caCertFileContent")), path);
			path = httpPath + typePath + path.substring(path.lastIndexOf("/"), path.length());
			if(isok) cert.setCaCertFileContent(path);
			//上传文件到硬盘中并生成下载的URL
			path = ResourceUtil.fileUploadPaht + "/" + typePath  + "/" + BaseUtil.trim(data.get("caKeyFile"));
			cert.setCaKeyFile(BaseUtil.trim(data.get("caKeyFile")));
			isok = FileUploadUtil.generateDoc(BaseUtil.trim(data.get("caKeyFileContent")), path);
			path = httpPath + typePath + path.substring(path.lastIndexOf("/"), path.length());
			if(isok) cert.setCaKeyFileContent(path);
			//上传文件到硬盘中并生成下载的URL
			path = ResourceUtil.fileUploadPaht + "/" + typePath  + "/" + BaseUtil.trim(data.get("certFile"));
			cert.setCertFile(BaseUtil.trim(data.get("certFile")));
			isok = FileUploadUtil.generateDoc(BaseUtil.trim(data.get("certFileContent")), path);
			path = httpPath + typePath + path.substring(path.lastIndexOf("/"), path.length());
			if(isok) cert.setCertFileContent(path);
			int res = 0;
			//判断是新增还是更新  ID为是0是新增
			if(id > 0){
				cert.setId(id);
				res = dao.update(cert);
			}else{
				res = dao.insert(cert);
			}
			//操作失败
			if(res <=0){
				return ResultMsgUtil.error("",topic);
			}
			//查询最新信息返回
			//cert = dao.queryById(id);
			/*
			if("client".equals(type) || "ca".equals(type)){
				

				
			}else if("server".equals(type)){
				
				
				
				cert.setUserName(BaseUtil.trim(data.get("userName")));
				cert.setCountry(BaseUtil.trim(data.get("country")));
				cert.setCountryName(BaseUtil.trim(data.get("countryName")));
				cert.setEmail(BaseUtil.trim(data.get("email")));
				cert.setLocality(BaseUtil.trim(data.get("locality")));
				cert.setOrgName(BaseUtil.trim(data.get("orgName")));
				cert.setOrgUnit(BaseUtil.trim(data.get("orgUnit")));
				cert.setProvince(BaseUtil.trim(data.get("province")));
				//cert.setSourceKey(BaseUtil.trim(data.get("sourceKey")));
				cert.setType(BaseUtil.trim(data.get("type")));
				cert.setValidDays(BaseUtil.trim(data.get("validDays")));
				cert.setTime(Long.parseLong(BaseUtil.trim(data.get("time"),"0")));
				cert.setIp(BaseUtil.trim(data.get("ip")));
				
				String path = ResourceUtil.fileUploadPaht + "/" + typePath  + "/" + BaseUtil.trim(data.get("keyFile"));
				cert.setKeyFile(BaseUtil.trim(data.get("keyFile")));
				boolean isok = FileUploadUtil.generateDoc(BaseUtil.trim(data.get("keyFileContent")), path);
				path = httpPath + typePath + path.substring(path.lastIndexOf("/"), path.length());
				if(isok) cert.setKeyFileContent(path);
				
				path = ResourceUtil.fileUploadPaht + "/" + typePath  + "/" + BaseUtil.trim(data.get("caCertFile"));
				cert.setCaCertFile(BaseUtil.trim(data.get("caCertFile")));
				isok = FileUploadUtil.generateDoc(BaseUtil.trim(data.get("caCertFileContent")), path);
				path = httpPath + typePath + path.substring(path.lastIndexOf("/"), path.length());
				if(isok) cert.setCaCertFileContent(path);
				
				
				path = ResourceUtil.fileUploadPaht + "/" + typePath  + "/" + BaseUtil.trim(data.get("caKeyFile"));
				cert.setCaKeyFile(BaseUtil.trim(data.get("caKeyFile")));
				isok = FileUploadUtil.generateDoc(BaseUtil.trim(data.get("caKeyFileContent")), path);
				path = httpPath + typePath + path.substring(path.lastIndexOf("/"), path.length());
				if(isok) cert.setCaKeyFileContent(path);
				
				path = ResourceUtil.fileUploadPaht + "/" + typePath  + "/" + BaseUtil.trim(data.get("certFile"));
				cert.setCertFile(BaseUtil.trim(data.get("certFile")));
				isok = FileUploadUtil.generateDoc(BaseUtil.trim(data.get("certFileContent")), path);
				path = httpPath + typePath + path.substring(path.lastIndexOf("/"), path.length());
				if(isok) cert.setCertFileContent(path);
				
				if(id > 0){
					cert.setId(id);
					dao.update(cert);
				}else{
					dao.insert(cert);
				}
			}*/
		}
		return ResultMsgUtil.success(cert,topic);
	}

	/**
	 * 查询
	 * @param json
	 * @return
	 */
	public JSONObject queryData(String json){
		//解析参数
		JSONObject obj = JSONObject.parseObject(json);
		JSONObject data = obj.getJSONObject("data");
		CertificateVo vo = JSONObject.toJavaObject(data, CertificateVo.class);
		//获取总记录数
		int countNums  = dao.count(vo);
		 
		PageHelper.startPage(vo.getCurrentPage(),vo.getPageSize());
		//获取记录
		List<CertificateInfo> list = dao.query(vo);
		//进行分页处理  调用分页控件
		PageBean<CertificateInfo> pageData = new PageBean<>(vo.getCurrentPage(), vo.getPageSize(), countNums);
		pageData.setItems(list);
		return ResultMsgUtil.success(pageData,"searchCert");
	}
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public JSONObject queryDatabyId(String json){
		JSONObject obj = JSONObject.parseObject(json);
		JSONObject data = obj.getJSONObject("data");
		CertificateInfo cert = dao.queryById(data.getIntValue("id"));

		return ResultMsgUtil.success(cert,"searchCert");
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public JSONObject deleteDatabyId(String json){
		JSONObject obj = JSONObject.parseObject(json);
		JSONObject data = obj.getJSONObject("data");
		
		Integer count = dao.delete(data.getIntValue("id"));
		
		JSONObject res = new JSONObject();
		res.put("id", data.getIntValue("id"));
		if(count > 0){
			return ResultMsgUtil.success(res,"delCert");
		}
		return ResultMsgUtil.error(res,"delCert");
	}
}
