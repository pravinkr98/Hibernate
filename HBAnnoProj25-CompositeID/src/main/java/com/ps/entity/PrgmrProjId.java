package com.ps.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Embeddable
public class PrgmrProjId implements Serializable {
	
	@Type(type = "int")
	private Integer pid;
	
	@Type(type = "int")
	private Integer projId;

}
