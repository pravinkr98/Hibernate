package com.ps.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Data;

@Data
@Entity
@Table(name = "STUDENTINFO")
public class StudentInfo implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Type(type = "int")
	private int sno;
	@Type(type = "string")
	@Column(length = 10)
	private String sname;
	@Type(type = "float")
	private float avg;
	@Embedded		//To make the property as Component property
	private Address addrs;

}
