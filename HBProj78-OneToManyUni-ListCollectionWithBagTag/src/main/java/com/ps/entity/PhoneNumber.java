package com.ps.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
//@NoArgsConstructor
@RequiredArgsConstructor
public class PhoneNumber implements Serializable {
	
	private Integer regNo;
	@NonNull
	private long phone;
	@NonNull
	private String type;
	@NonNull
	private String provider;
	private Integer unid;
	
	public PhoneNumber() {
		System.out.println("PhoneNumber :: 0-param constructor "+this.hashCode());
	}
}
