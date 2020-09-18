package com.ps.entity;

import java.io.Serializable;
//import java.util.Date;
//import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PersonInfo implements Serializable {

	private Integer pid;
	private String pname;
	private String paddrs;
	private LocalDateTime dob;
	private LocalDate dom;
	private LocalTime doj;

}
