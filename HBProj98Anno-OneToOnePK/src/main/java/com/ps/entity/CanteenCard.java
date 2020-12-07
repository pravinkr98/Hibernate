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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CANTEENCARD_ANNO_OTO_PK")
@Setter
@Getter
@RequiredArgsConstructor
public class CanteenCard implements Serializable {

	@Id
	@GenericGenerator(name = "gen1",strategy = "foreign",parameters = @Parameter(name="property",value = "empDetails"))
	@GeneratedValue(generator = "gen1")
	private Integer cardId;
	
	@NonNull
	@Type(type = "string")
	@Column(length = 15)
	private String canteenName;
	
	@NonNull
	@Type(type = "string")
	@Column(length = 15)
	private String cardType;
	
	@NonNull
	@Type(type = "string")
	@Column(length = 20)
	private String location;	
	
	@OneToOne(targetEntity = Employee.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "cardDetails")
	private Employee empDetails;
	
	public CanteenCard() {
		System.out.println("CanteenCard: 0-param constructor");
	}

	@Override
	public String toString() {
		return "CanteenCard [cardId=" + cardId + ", canteenName=" + canteenName + ", cardType=" + cardType
				+ ", location=" + location + "]";
	}
		
}
