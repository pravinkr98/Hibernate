package com.ps.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.Type;

import lombok.Data;

@Data
@Entity
public class JobSeeker implements Serializable {
	
	@Id
	@GeneratedValue
	@Type(type = "int")
	private Integer jsId;
	
	@Type(type = "string")
	@Column(length = 15)
	private String jsName;
	
	@Type(type = "string")
	@Column(length = 15)	
	private String jsAddr;
	
	//Lob property
	//@Type(type = "binary")
	@Lob
	private byte[] photo;
	
	//Lob property
	//@Type(type = "character")
	@Lob
	private char[] resume;	
	
	@Type(type = "boolean")
	private boolean active;

}
