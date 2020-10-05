package com.ps.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class InsurancePolicyDTO implements Serializable {

	private int serialNo;
	private Long policyId;
	private String policyName;
	private String policyType;
	private String company;
	private Integer tenure;
	
}
