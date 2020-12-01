package com.ps.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Student implements Serializable {

	private Integer sid;
	@NonNull
	private String sname;
	@NonNull
	private String sadd;
	private LibraryMembership libraryDetails;
	
	public Student() {
		System.out.println("Student : 0-param constructor");
	}

	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sname=" + sname + ", sadd=" + sadd + "]";
	}
		
}
