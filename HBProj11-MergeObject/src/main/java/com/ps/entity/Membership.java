package com.ps.entity;

public class Membership {

	private Long mid;
	private String name;
	private String addrs;
	private Long rewardsPoint;
	
	public Membership() {
		System.out.println("Membership :: 0-param constructor");
	}
	
	public Long getMid() {
		return mid;
	}
	public void setMid(Long mid) {
		this.mid = mid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddrs() {
		return addrs;
	}
	public void setAddrs(String addrs) {
		this.addrs = addrs;
	}
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
