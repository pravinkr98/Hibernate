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

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table
@RequiredArgsConstructor
public class PoliticalLeader implements Serializable {

	@Id
	@GeneratedValue(generator = "gen1", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "gen1", sequenceName = "AADHAR_SEQ", initialValue = 1000, allocationSize = 1)
	@Type(type = "long")
	private long leaderAadharNo;
	@NonNull
	@Type(type = "string")
	@Column(length = 15)
	private String leaderName;
	@NonNull
	@Type(type = "string")
	@Column(length = 15)
	private String leaderPosition;
	@NonNull
	@Type(type = "string")
	@Column(length = 15)
	private String leaderArea;
	
	@ManyToOne(targetEntity = PoliticalParty.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "PARTY_ID", referencedColumnName = "PARTYID")
	//@LazyToOne(LazyToOneOption.PROXY)
	private PoliticalParty party;
	
	public PoliticalLeader() {
		System.out.println("PoliticalLeader : 0-param constructor");
	}

	@Override
	public String toString() {
		return "PoliticalLeader [leaderAadharNo=" + leaderAadharNo + ", leaderName=" + leaderName + ", leaderPosition="
				+ leaderPosition + ", leaderArea=" + leaderArea + "]";
	}
		
}
