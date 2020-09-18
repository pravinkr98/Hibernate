package com.ps.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProgrammerProjectInfo implements Serializable {
	private PrgmrProjId id;
	private String pname;
	private String projName;
	private Integer deptNo;

}
