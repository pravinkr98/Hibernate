package com.ps.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class JobSeeker implements Serializable {

	private Integer jsId;
	private String jsName;
	private String jsAddr;
	private byte[] photo;
	private char[] resume;	
	private boolean active;

}
