package com.ps.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "MOBILE_CUSTOMER")
public class MobileCustomer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cno;
	
	@Column(length = 20)
	@Type(type = "string")
	private String cname;
	
	@Type(type = "long")
	private Long mobileNo;
	
	@Column(length = 20)
	@Type(type = "string")
	private String callerTune;
	
	@Version
	@Type(type = "int")
	private Integer versionCount;

}
