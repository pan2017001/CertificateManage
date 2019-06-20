package com.zhichenhaixin.certificatemanage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zhichenhaixin.certificatemanage.model.User;


/**
 * 跳转页面的Controller
 * 注解一定要写Controller,RestController会直接
 * 返回文本消息给页面。
 * @version V1.0
 * @author pwl
 * @date 2018年6月6日 上午10:24:29
 * @Description 
 */
@Controller
public class PageController {
	 
	 @RequestMapping("/home")
     public String homeHtml(){
		//验证是否登陆
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		User user = (User)request.getSession().getAttribute("user");
		if(user != null){
			 return "/home";
		}
    	return "/login";
     }
	 
	 @RequestMapping("/login")
     public String loginHtml(){
    	 return "/login";
     }
	 @RequestMapping("/mytest")
	 public String mytest(){
		 return "/mytest";
	 }
}
