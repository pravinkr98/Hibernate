package com.ps.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class PremiumInsurancePolicy implements Serializable{

	private Long pid;
	private String policyName;
	private String policyType;
	private String company;
	private Integer tenure;
	
}
