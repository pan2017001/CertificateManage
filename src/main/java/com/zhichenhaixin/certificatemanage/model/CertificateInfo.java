package com.zhichenhaixin.certificatemanage.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * 数字证书实体类
 * @author pwl
 *
 */
@Entity
@Component
@Getter
@Setter
public class CertificateInfo {
	@Id
	private int id;
	private String userName;
	private String country;
	private String countryName;
	private String email;
	private String locality;
	private String orgName;
	private String orgUnit;
	private String province;
	private String sourceKey;
	private String type;
	private String validDays;
	private String ip;
	private long time;
	
	private String keyFile;
	private String keyFileContent;
	private String caCertFile;
	private String caCertFileContent;
	private String caKeyFile;
	private String caKeyFileContent;
	private String certFile;
	private String certFileContent;
}
