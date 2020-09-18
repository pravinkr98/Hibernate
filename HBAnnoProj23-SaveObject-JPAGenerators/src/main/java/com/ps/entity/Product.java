package com.ps.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

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
	
	/*@Id
	@SequenceGenerator(name = "gen2",
						sequenceName = "JPA_PID_SEQ",
						allocationSize = 7,
						initialValue = 100)
	@GeneratedValue(generator = "gen2",strategy = GenerationType.SEQUENCE)*/
	
	/*@Id
	@TableGenerator(name = "gen1",
					table = "ID_TAB",
					pkColumnName = "ID_COL",
					valueColumnName = "ID_VAL",
					pkColumnValue = "PID",
					initialValue = 10,
					allocationSize = 2)
	@GeneratedValue(generator = "gen1",strategy = GenerationType.TABLE)*/
	
	/*@Id
	@GeneratedValue(strategy = GenerationType.TABLE)*/
	
	/*@Id
	@GeneratedValue(strategy = GenerationType.AUTO)*/
	
	@Id
	@SequenceGenerator(name = "gen1",
						sequenceName = "PROD_ID_SEQ",
						initialValue = 21,
						allocationSize = 3)
	@GeneratedValue(generator = "gen1",strategy = GenerationType.AUTO)
	private Integer pid;
	private String pname;
	private Float price;
	private Float qty;
	
}
