package com.zhichenhaixin.certificatemanage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhichenhaixin.certificatemanage.model.CertificateInfo;
import com.zhichenhaixin.certificatemanage.model.CertificateVo;

/**
 * 数字证书相关数据库持久类
 * @author pwl
 *
 */
@Repository
public interface CertificateDao {

	List<CertificateInfo> query(CertificateVo vo);
	
	int count(CertificateVo vo);
	
	CertificateInfo queryById(int id);

	int insert(CertificateInfo certificateInfo);

	int update(CertificateInfo certificateInfo);

	int delete(int id);
}
