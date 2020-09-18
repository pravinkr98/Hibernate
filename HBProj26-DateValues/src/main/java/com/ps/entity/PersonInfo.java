package com.ps.entity;

import java.io.Serializable;
import java.util.Date;
//import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
	private Date dob;
	private Date dom;
	private Date doj;

	/*public Timestamp getDob() {
	    return Timestamp.valueOf(this.dob);
	}
	 public LocalDateTime getDobLocal() {
	    return this.dob;
	} 
	public void setDob(Timestamp dob) {
	    this.dob=dob.toLocalDateTime();
	}
	public void setDobLocal(LocalDateTime dob) {
	    this.dob=dob;
	}
	
	public Date getDom() {
	    return Date.valueOf(this.dom);
	}
	 public LocalDate getDomLocal() {
	    return this.dom;
	} 
	public void setDom(Date dom) {
	    this.dom=dom.toLocalDate();
	}
	public void setDomLocal(LocalDate dom) {
	    this.dom=dom;
	}
	
	public Timestamp getDoj() {
	    return Timestamp.valueOf(this.doj);
	}
	 public LocalDateTime getDojLocal() {
	    return this.doj;
	} 
	public void setDoj(Timestamp doj) {
	    this.doj=doj.toLocalDateTime();
	}
	public void setDojLocal(LocalDateTime doj) {
	    this.doj=doj;
	}
	*/

}
