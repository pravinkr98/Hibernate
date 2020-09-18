package com.ps.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import lombok.Data;

@Data
@Entity
@Table(name = "PERSON_INFO")
public class PersonInfo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Type(type = "int")
	private Integer pid;
	
	@Column(length = 20)
	@Type(type = "string")
	private String pname;
	
	@Column(length = 20)
	@Type(type = "string")
	private String paddrs;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dob;
	
	@Temporal(TemporalType.DATE)
	private Date dom;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date doj;
	    
}
