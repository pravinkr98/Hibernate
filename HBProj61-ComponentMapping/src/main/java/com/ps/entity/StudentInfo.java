package com.ps.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class StudentInfo implements Serializable {
	private int sno;
	private String sname;
	private float avg;
	private Address addrs;

}
