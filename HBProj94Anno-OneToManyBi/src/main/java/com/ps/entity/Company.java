package com.ps.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "COMPANY_OTM_BI")
@Setter
@Getter
@RequiredArgsConstructor
public class Company implements Serializable {

	@Id
	@GeneratedValue
	@Type(type = "int")
	private Integer compId;
	
	@NonNull
	@Type(type = "string")
	@Column(length = 15)
	private String compName;
	
	@NonNull
	@Type(type = "string")
	@Column(length = 15)
	private String addrs;
	
	@NonNull
	@Type(type = "string")
	@Column(length = 15)
	private String type;
	
	@OneToMany(targetEntity = Project.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "COMPANY_ID", referencedColumnName = "COMPID")
	private Set<Project> projects;
	
	public Company() {
			System.out.println("Company : 0-param constructor");
	}

	@Override
	public String toString() {
		return "Company [compName=" + compName + ", addrs=" + addrs + ", type=" + type + "]";
	}
	
}
