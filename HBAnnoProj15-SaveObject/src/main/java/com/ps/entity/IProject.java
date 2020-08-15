package com.ps.entity;

public interface IProject {
	
	public Long getProjId();
	public void setProjId(Long projId);
	public String getProjName();
	public void setProjName(String projName);
	public Integer getTeamSize();
	public void setTeamSize(Integer teamSize);
	public String getCompany();
	public void setCompany(String company);	
}
