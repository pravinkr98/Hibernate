package com.ps.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "PROGRAMMER_PROJECT_INFO")
public class ProgrammerProjectInfo implements Serializable {
	
	@EmbeddedId
	@Type(type = "int")
	private PrgmrProjId id;
	
	@Column(length = 20)
	@Type(type = "string")
	private String pname;
	
	@Column(length = 20)
	@Type(type = "string")
	private String projName;
	
	@Type(type = "int")
	private Integer deptNo;

}
