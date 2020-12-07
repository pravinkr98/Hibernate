package com.ps.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "EMPLOYEE_ANNO_OTO_PK")
@Setter
@Getter
@RequiredArgsConstructor
public class Employee implements Serializable {

	@Id
	@GeneratedValue
	@Type(type = "int")
	private Integer empId;
	
	@NonNull
	@Type(type = "string")
	@Column(length = 15)
	private String empName;

	@NonNull
	@Type(type = "string")
	@Column(length = 15)
	private String empAddrs;
	
	@OneToOne(targetEntity = CanteenCard.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	@PrimaryKeyJoinColumn(name = "canteen_Id", referencedColumnName = "empId")
	private CanteenCard cardDetails;
	
	public Employee() {
		System.out.println("Employee: 0-param constructor");
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empAddrs=" + empAddrs + "]";
	}
		
}
