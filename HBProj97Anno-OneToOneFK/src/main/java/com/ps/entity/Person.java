package com.ps.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PERSON_ANNO_OTO_FK")
@Setter
@Getter
@RequiredArgsConstructor
public class Person implements Serializable {

	@Id
	@GeneratedValue
	@Type(type = "int")
	private Integer personId;
	
	@NonNull
	@Type(type = "string")
	@Column(length = 15)
	private String personName;

	@NonNull
	@Type(type = "string")
	@Column(length = 15)
	private String addrs;
		
	public Person() {
		System.out.println("Person: 0-param constructor");
	}

	@Override
	public String toString() {
		return "Person [personId=" + personId + ", personName=" + personName + ", addrs=" + addrs + "]";
	}
		
}
