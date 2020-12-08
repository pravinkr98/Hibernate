package com.ps.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class InsurancePolicy implements Serializable{

	private Long policyId;
	private String policyName;
	private String policyType;
	private String company;
	private Integer tenure;
	
	public InsurancePolicy() {
		System.out.println("InsurancePolicy :: 0-param constructor");
	}
	
}
