package com.ps.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.hibernate.annotations.Type;

import lombok.Data;

@Data
@Entity
@DiscriminatorValue("cheque")
public class ChequePayment extends Payment {

	@Type(type = "long")
	private Long chequeNo;
	@Type(type = "string")
	@Column(length = 10)
	private String chequeType;
	private LocalDate expiryDate;
	
	@Override
	public String toString() {
		return super.toString() +" ChequePayment [chequeNo=" + chequeNo + ", chequeType=" + chequeType + ", expiryDate=" + expiryDate
				+ "]";
	}
	
}
