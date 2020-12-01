package com.ps.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table
@RequiredArgsConstructor
public class PoliticalParty implements Serializable {

	@Id
	@GeneratedValue
	private Integer partyId;
	@NonNull
	@Type(type = "string")
	@Column(length = 15)
	private String partyName;
	@NonNull
	@Type(type = "string")
	@Column(length = 15)
	private String partySymbol;
	@NonNull
	@Type(type = "string")
	@Column(length = 15)
	private String flagColor;
	
	public PoliticalParty() {
		System.out.println("PoliticalParty : 0-param constructor");
	}

	@Override
	public String toString() {
		return "PoliticalParty [partyId=" + partyId + ", partyName=" + partyName + ", partySymbol=" + partySymbol
				+ ", flagColor=" + flagColor + "]";
	}
	
}
