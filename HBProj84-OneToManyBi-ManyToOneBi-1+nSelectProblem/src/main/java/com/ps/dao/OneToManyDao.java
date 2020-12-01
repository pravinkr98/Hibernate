package com.ps.dao;

public interface OneToManyDao {
		
	public void loadDataUsingParent();
	public void loadDataUsingParentThroughQBC();
	public void loadDataUsingParentThroughJPQBC();
	public void loadDataUsingParentThroughHQLJoin();
	public void loadDataUsingParentThroughHQLJoinScaler();
	
}
