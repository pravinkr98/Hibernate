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

import org.hibernate.annotations.Type;

import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "PAYMENT_TPSC_DISC")
@DiscriminatorColumn(name = "PAYMENT_MODE", discriminatorType = DiscriminatorType.STRING, length = 10)
public abstract class Payment implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Type(type = "int")
	private Integer pid;
	@Type(type = "float")
	private Float amount;
}
