package com.ps.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Data;

@Data
@Entity
@PrimaryKeyJoinColumn(name = "PAYMENT_ID", referencedColumnName = "PID")
@Table(name = "CHEQUEPAYMENT_TPSC")
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
