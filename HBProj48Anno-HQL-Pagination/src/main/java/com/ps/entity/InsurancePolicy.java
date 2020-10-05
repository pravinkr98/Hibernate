package com.ps.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@NamedQuery(name = "GET_POLICY_COUNT", query = "SELECT COUNT(*) FROM com.ps.entity.InsurancePolicy")
@NamedQuery(name = "GET_ALL_POLICIES", query = "FROM com.ps.entity.InsurancePolicy")
public class InsurancePolicy implements Serializable {

	@Id
	private Long policyId;
	private String policyName;
	private String policyType;
	private String company;
	private Integer tenure;
	
}
