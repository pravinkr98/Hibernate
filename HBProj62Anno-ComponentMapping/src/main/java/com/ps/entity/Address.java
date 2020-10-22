package com.ps.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Type;

import lombok.Data;

@Data
@Embeddable
public class Address {
	@Type(type = "string")
	@Column(length = 10)
	private String doorNo;
	@Type(type = "string")
	@Column(length = 15)
	private String streetName;
	@Type(type = "string")
	@Column(length = 15)
	private String areaName;
	@Type(type = "string")
	@Column(length = 15)
	private String district;
	@Type(type = "string")
	@Column(length = 10)
	private String state;
	@Type(type = "string")
	@Column(length = 8)
	private String country;
	@Type(type = "long")
	private long pincode;

}
