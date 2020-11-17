package com.ps.dao;

public interface OneToManyDao {
	
	public void saveDataUsingParent();
	public void saveDataUsingChild();	
	public void loadDataUsingParent();
	public void loadDataUsingChild();
	
}
