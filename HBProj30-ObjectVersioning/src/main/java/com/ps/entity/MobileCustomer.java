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
public class MobileCustomer implements Serializable {

	private Integer cno;
	private String cname;
	private Long mobileNo;
	private String callerTune;
	private Integer versionCount;

}
