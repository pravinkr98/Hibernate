package com.ps.entity;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
//@NoArgsConstructor
@RequiredArgsConstructor
public class UserInfo implements Serializable {

	private int userId;
	@NonNull
	private String userName;
	@NonNull
	private String addrs;
	private Set<PhoneNumber> phones;	//special property to build 1..* association
	
	public UserInfo() {
		System.out.println("UserInfo :: 0-param constructor "+this.getClass());
	}
}
