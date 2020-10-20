package com.ps.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "BANKACCOUNT")
@SQLDelete(sql = "UPDATE BANKACCOUNT SET STATUS='closed' WHERE ACNO=?")
@Where(clause = "STATUS NOT IN('closed','blocked')")
public class BankAccount implements Serializable {
	@Id
	@GeneratedValue
	private Integer acno;
	private String holderName;
	private Float balance;
	private String status;

}
