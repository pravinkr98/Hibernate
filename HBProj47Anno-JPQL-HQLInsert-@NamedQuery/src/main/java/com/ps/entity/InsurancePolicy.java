package com.ps.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class InsurancePolicy implements Serializable{

	@Id
	private Long policyId;
	private String policyName;
	private String policyType;
	private String company;
	private Integer tenure;
	
}
