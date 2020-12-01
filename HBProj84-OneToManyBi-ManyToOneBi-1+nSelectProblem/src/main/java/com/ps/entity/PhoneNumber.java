package com.ps.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
public class PhoneNumber implements Serializable {
	
	private int regNo;
	@NonNull
	private long phone;
	@NonNull
	private String type;
	@NonNull
	private String provider;
	private UserInfo user;
	
	public PhoneNumber() {
		System.out.println("PhoneNumber :: 0-param constructor "+this.getClass());
	}

	@Override
	public String toString() {
		return "PhoneNumber [regNo=" + regNo + ", phone=" + phone + ", type=" + type + ", provider=" + provider + "]";
	}
	
	
}
