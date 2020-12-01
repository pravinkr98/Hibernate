package com.ps.entity;

import java.io.Serializable;
import java.util.Set;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class Patient implements Serializable {

	private Integer pid;
	@NonNull
	private String pname;
	@NonNull
	private String problem;
	private Set<Doctor> doctors;
	
	public Patient() {
		System.out.println("Patient :: 0-param constructor");
	}

	@Override
	public String toString() {
		return "Patient [pid=" + pid + ", pname=" + pname + ", problem=" + problem + "]";
	}
	
}
