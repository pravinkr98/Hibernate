package com.ps.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SUPERMARKET_MEMBERSHIP")
public class Membership implements Serializable {

	private Long mid;
	private String name;
	private String addrs;
	private Long rewardsPoint;
	
	@Id
	@Column(name = "MID")
	public Long getMid() {
		return mid;
	}
	public void setMid(Long mid) {
		this.mid = mid;
	}
	
	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "ADDRS")
	public String getAddrs() {
		return addrs;
	}
	public void setAddrs(String addrs) {
		this.addrs = addrs;
	}
	
	@Column(name = "REWARDSPOINT")
	public Long getrewardsPoint() {
		return rewardsPoint;
	}
	public void setrewardsPoint(Long rewardsPoint) {
		this.rewardsPoint = rewardsPoint;
	}
	
	@Override
	public String toString() {
		return "Membership [mid=" + mid + ", name=" + name + ", addrs=" + addrs + ", rewardsPoint=" + rewardsPoint
				+ "]";
	}
	
}
