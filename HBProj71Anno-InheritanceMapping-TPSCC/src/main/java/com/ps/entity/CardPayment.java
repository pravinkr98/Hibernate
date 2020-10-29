package com.ps.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Data;

@Entity
@Data
@Table(name = "CARDPAYMENT_TPSCC")
public class CardPayment extends Payment {

	@Type(type = "long")
	private Long cardNo;
	@Type(type = "string")
	@Column(length = 10)
	private String cardType;
	@Type(type = "string")
	@Column(length = 10)
	private String cardGateway;
	
	@Override
	public String toString() {
		return super.toString() +" CardPayment [cardNo=" + cardNo + ", cardType=" + cardType + ", cardGateway=" + cardGateway
				+  "]";
	}	
	
}
