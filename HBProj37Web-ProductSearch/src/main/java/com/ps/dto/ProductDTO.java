package com.ps.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProductDTO implements Serializable {

	private Integer pid;
	private String pname;
	private Double price;
	private Integer qty;
	private String category;
}
