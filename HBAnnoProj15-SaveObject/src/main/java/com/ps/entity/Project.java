package com.ps.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;

@Entity
//@Table(name = "PROJECT")
@DynamicInsert(true)
//@DynamicUpdate(true)
@Proxy(lazy = true,proxyClass = IProject.class)
//@Proxy(lazy = false)
public final class Project implements Serializable,IProject{

	private Long projId;
	private String projName;
	private Integer teamSize;
	private String company;
	
	@Id
	@Column(name = "PROJID")
	@Type(type = "long")
	public Long getProjId() {
		return projId;
	}
	public void setProjId(Long projId) {
		this.projId = projId;
	}
	
	@Column(name = "PROJNAME",length = 21,nullable = false,unique = true)
	@Type(type = "string")
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	
	@Column(name = "TEAMSIZE")
	@Type(type = "int")
	public Integer getTeamSize() {
		return teamSize;
	}
	public void setTeamSize(Integer teamSize) {
		this.teamSize = teamSize;
	}
	
	@Column(name = "COMPANY",length = 24,nullable = true)
	@Type(type = "string")
	//@Transient
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	@Override
	public String toString() {
		return "Project [projId=" + projId + ", projName=" + projName + ", teamSize=" + teamSize + ", company="
				+ company + "]";
	}
}
