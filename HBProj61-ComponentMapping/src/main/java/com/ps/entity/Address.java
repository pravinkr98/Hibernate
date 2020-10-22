package com.ps.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Address {
	private String doorNo;
	private String streetName;
	private String areaName;
	private String district;
	private String state;
	private String country;
	private long pincode;

}
