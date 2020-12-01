package com.ps.dao;

public interface IOneToOnePKDAO {

	public void saveDataUsingParent();
	public void saveDataUsingChild();
	public void loadDataUsingParent();
}
