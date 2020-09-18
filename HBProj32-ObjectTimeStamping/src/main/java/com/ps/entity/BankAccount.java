package com.ps.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BankAccount implements Serializable {

	private Long acno;
	private String holderName;
	private Double balance;
	private String type;
	private LocalDateTime openingDate;	//to hold account opening date
	//private Timestamp lastUpdate;	//for last update
	private Date lastUpdate;	//for last update

}
