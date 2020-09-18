package com.ps.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "HB_BANK_ACCOUNT")
public class BankAccount implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Type(type = "long")
	private Long acno;
	
	@Column(length = 20)
	@Type(type = "string")
	private String holderName;
	
	@Type(type = "double")
	private Double balance;
	
	@Column(length = 20)
	@Type(type = "string")
	private String type;
	
	@CreationTimestamp
	private LocalDateTime openingDate;	//to hold account opening date

	@UpdateTimestamp
	private LocalDateTime lastUpdate;	//for last update

	@Version
	private Integer versionCount;
}
