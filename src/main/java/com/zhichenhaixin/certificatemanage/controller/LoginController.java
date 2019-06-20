package com.zhichenhaixin.certificatemanage.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.zhichenhaixin.certificatemanage.model.User;
import com.zhichenhaixin.certificatemanage.service.UserService;
import com.zhichenhaixin.certificatemanage.utils.BaseUtil;
import com.zhichenhaixin.certificatemanage.utils.ResultMsgUtil;
/**
 * 用户登陆
 * @version V1.0
 * @author pwl
 * @date 2019年5月11日10:13:3
 * @Description 
 */
@RestController
public class LoginController { 
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	@Autowired 
	private UserService userService;
	
	@PostMapping(value = "/user/login")
	public JSONObject login(@RequestBody String json){
		//解析参数
		JSONObject obj = JSONObject.parseObject(json);
		JSONObject data = obj.getJSONObject("data");
		User user = new User();
		user.setuName(BaseUtil.trim(data.get("uName")));
		user.setuAccount(BaseUtil.trim(data.get("uName")));
		user.setuPwd(BaseUtil.trim(data.get("uPwd")));
		/*if(!StringUtils.isBlank(user.getuName())){
			user.setuAccount(user.getuName());
		}*/
		if (userService.checkLogin(user.getuAccount(), user.getuPwd())) {			
			// 保存用户session
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			request.getSession().setAttribute("user", userService.getUser(user.getuAccount()));
			return ResultMsgUtil.success("","loginIn");
		}	
		
		return ResultMsgUtil.error("用户名或密码错误","loginIn");
	}

}
