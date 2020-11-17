package com.ps.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Data;

@Data
public class Employee implements Serializable {

	private Integer eno;
	private String ename;
	private String addrs;
	private List<String> friends;
	private List<String> relativeList;
	private Set<Long> phones;
	private Map<String,Long> accounts;
}
