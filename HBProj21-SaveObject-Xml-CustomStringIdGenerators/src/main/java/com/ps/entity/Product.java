package com.ps.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Product {

	private String pid;
	private String pname;
	private Float price;
	private Float qty;
	
}
