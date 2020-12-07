package com.ps.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PASSPORT_ANNO_OTO_FK")
@Setter
@Getter
@RequiredArgsConstructor
public class Passport implements Serializable {

	@Id
	@GeneratedValue(generator = "gen1",strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "gen1",sequenceName = "PSPT_NO_SEQ",initialValue = 10000,allocationSize = 1)
	private Integer passportNo;
	
	@NonNull
	@Type(type = "string")
	@Column(length = 15)
	private String passportName;
	
	@NonNull
	@Type(type = "string")
	@Column(length = 20)
	private String passportCountry;
	
	@NonNull
	private LocalDate passportExpiry;
	
	@OneToOne(targetEntity = Person.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	@JoinColumn(name = "PERSON_ID",referencedColumnName = "PERSONID")
	private Person personDetails;
	
	public Passport() {
		System.out.println("Passport: 0-param constructor");
	}

	@Override
	public String toString() {
		return "Passport [passportNo=" + passportNo + ", passportName=" + passportName + ", passportCountry="
				+ passportCountry + ", passportExpiry=" + passportExpiry + "]";
	}
	
}
