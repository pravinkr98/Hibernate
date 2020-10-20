package com.ps.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BankAccount implements Serializable {
	private Integer acno;
	private String holderName;
	private Float balance;
	private String status;

}
