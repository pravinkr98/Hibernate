package com.ps.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PrgmrProjId implements Serializable {
	private Integer pid;
	private Integer projId;

}
