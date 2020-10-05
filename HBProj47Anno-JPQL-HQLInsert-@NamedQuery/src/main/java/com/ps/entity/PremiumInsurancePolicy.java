package com.ps.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PREMIUM_INSURANCEPOLICY")
@NamedQuery(name = "HQL_PREMIUM_POLICY_TRNASFER_QUERY",
			query = "INSERT INTO com.ps.entity.PremiumInsurancePolicy(pid,policyName,policyType,company,tenure) SELECT i.policyId,i.policyName,i.policyType,i.company,i.tenure FROM com.ps.entity.InsurancePolicy AS i WHERE tenure>=:min" )
public class PremiumInsurancePolicy implements Serializable{

	@Id
	private Long pid;
	private String policyName;
	private String policyType;
	private String company;
	private Integer tenure;
	
}
