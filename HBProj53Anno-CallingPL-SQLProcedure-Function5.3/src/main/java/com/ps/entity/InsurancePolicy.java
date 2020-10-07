package com.ps.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

import lombok.Data;

@Data
@Entity
public class InsurancePolicy implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long policyId;
	private String policyName;
	private String policyType;
	private String company;
	private Integer tenure;
	
	public InsurancePolicy() {
		System.out.println("InsurancePolicy :: 0-param constructor"+this.getClass()+" -->"+this.hashCode()+"  "+System.identityHashCode(this));
	}
}
