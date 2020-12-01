package com.ps.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Doctor implements Serializable {
	
	private Integer did;
	@NonNull
	private String dname;
	@NonNull
	private String hospital;
	@NonNull
	private String qualification;
	private List<Patient> patients;
	
	public Doctor() {
		System.out.println("Doctor :: 0-param constructor");
	}

	@Override
	public String toString() {
		return "Doctor [did=" + did + ", name=" + dname + ", hospital=" + hospital + ", qualification=" + qualification
				+ "]";
	}	

}
