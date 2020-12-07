package com.ps.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PROJECT_OTM_BI")
@Setter
@Getter
@RequiredArgsConstructor
public class Project implements Serializable {

	@Id
	@GeneratedValue(generator = "gen1", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "gen1", sequenceName = "PROJ_ID_SEQ", initialValue = 1000, allocationSize = 1)
	@Type(type = "long")
	private Long projId;
	
	@NonNull
	@Type(type = "string")
	@Column(length = 15)
	private String projName;
	
	@NonNull
	@Type(type = "string")
	@Column(length = 15)
	private String projType;
	
	@NonNull
	@Type(type = "int")
	private Integer teamSize;
	
	@ManyToOne(targetEntity = Company.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "COMPANY_ID", referencedColumnName = "COMPID")
	private Company company;
	
	public Project() {
		System.out.println("Project : 0-param constructor");
	}

	@Override
	public String toString() {
		return "Project [projName=" + projName + ", projType=" + projType + ", teamSize=" + teamSize + "]";
	}
	
}
