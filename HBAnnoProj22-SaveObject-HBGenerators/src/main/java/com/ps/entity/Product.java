package com.ps.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	@GenericGenerator(name = "gen1",strategy = "increment")
	@GeneratedValue(generator = "gen1")*/
	
	/*@Id
	@GenericGenerator(name = "gen1",strategy = "identity")
	@GeneratedValue(generator = "gen1")*/
	
	/*@Id
	@GenericGenerator(name="gen1",strategy="sequence")
	@GeneratedValue(generator="gen1")*/
	
	/*@Id
	@GenericGenerator(name = "gen1",strategy = "sequence",
					parameters = @Parameter(name="sequence_name",value = "PID_SEQ"))
	@GeneratedValue(generator = "gen1")*/
	
	@Id
	@GenericGenerator(name = "gen1",strategy = "seqhilo",
			parameters = {@Parameter(name="sequence_name",value = "PID_SEQ"),
							@Parameter(name="max_lo",value = "6")})
	@GeneratedValue(generator = "gen1")
	private Integer pid;
	private String pname;
	private Float price;
	private Float qty;
	
}
