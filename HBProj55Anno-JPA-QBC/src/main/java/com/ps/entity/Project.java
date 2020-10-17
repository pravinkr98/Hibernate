package com.ps.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
public class Project implements Serializable {
	
	public Project() {
		System.out.println("Project::0-param constructor"+this.getClass());
	}
	
	@Id
	private Integer projId;
	private String projName;
	private String company;
	private String location;
	private Float cost;
	private Integer teamSize;

}
