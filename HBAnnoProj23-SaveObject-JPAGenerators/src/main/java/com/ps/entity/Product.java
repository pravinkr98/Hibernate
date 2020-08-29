package com.ps.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "Product_Anno")
public class Product {

	/*@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)*/
	
	/*@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)*/
	
	@Id
	@SequenceGenerator(name = "gen2",
						sequenceName = "JPA_PID_SEQ",
						allocationSize = 7,
						initialValue = 100)
	@GeneratedValue(generator = "gen2",strategy = GenerationType.SEQUENCE)
	private Integer pid;
	private String pname;
	private Float price;
	private Float qty;
	
}
