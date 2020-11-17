package com.ps.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.ListIndexBase;
import org.hibernate.annotations.Type;

import lombok.Data;

@Data
@Entity
@Table(name = "EMPLOYEE_COLLECTION")
public class Employee implements Serializable {

	@Id
	@GeneratedValue
	@Type(type = "int")
	private Integer eno;
	
	@Type(type = "string")
	@Column(length = 15)
	private String ename;
	
	@Type(type = "string")
	@Column(length = 15)
	private String addrs;
	
	@ElementCollection
	@Column(name = "FRIEND_NAME", length = 15)
	@CollectionTable(name = "EMP_FRIENDS", 
					 joinColumns = @JoinColumn(name = "EMP_ID", referencedColumnName = "eno"))
	@OrderColumn(name = "FRIEND_NO")
	@ListIndexBase(value = 1)
	private List<String> friends;
	
	@ElementCollection
	@Column(name = "RELATIVE_NAME", length = 15)
	@CollectionTable(name = "EMP_RELATIVE", 
					 joinColumns = @JoinColumn(name = "EMP_ID", referencedColumnName = "eno"))
	private List<String> relativeList;
	
	@ElementCollection
	@Column(name = "MOBILE_NO")
	@CollectionTable(name = "EMP_PHONES",
					 joinColumns = @JoinColumn(name = "EMP_ID", referencedColumnName = "eno"))
	private Set<Long> phones;
	
	@ElementCollection
	@Column(name = "ACCOUNT_NO")
	@CollectionTable(name = "EMP_ACCOUNTS",
					 joinColumns = @JoinColumn(name = "EMP_ID", referencedColumnName = "eno"))
	@MapKeyColumn(name = "BANK_NAME", length = 10)
	private Map<String,Long> accounts;
}
