package com.ps.entity;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;
import org.hibernate.annotations.Type;

import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@Polymorphism(type = PolymorphismType.EXPLICIT)	// no use
public abstract class Payment implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Type(type = "int")
	private Integer pid;
	@Type(type = "float")
	private Float amount;
}
