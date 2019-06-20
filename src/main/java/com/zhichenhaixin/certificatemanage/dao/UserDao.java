package com.zhichenhaixin.certificatemanage.dao;

import org.springframework.stereotype.Repository;

import com.zhichenhaixin.certificatemanage.model.User;


/**
 * 用户DAO
 * @version V1.0
 * @author pwl
 * @date 2019年5月11日9:13:3
 * @Description 
 */
@Repository
public interface UserDao {
	
	User selectByAccount(String uAccount);
	
}
