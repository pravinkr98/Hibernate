package com.ps.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@FilterDef(name = "FILTER_BANKACCOUNT_STATUS",
			parameters = {@ParamDef(name="accountType1",type = "string"),
						  @ParamDef(name="accountType2",type = "string")
						 }
			)
@Filter(name = "FILTER_BANKACCOUNT_STATUS",
		condition = "STATUS NOT IN(:accountType1,:accountType2)")
public class BankAccount implements Serializable {
	@Id
	@GeneratedValue
	private Integer acno;
	private String holderName;
	private Float balance;
	private String status;

}
