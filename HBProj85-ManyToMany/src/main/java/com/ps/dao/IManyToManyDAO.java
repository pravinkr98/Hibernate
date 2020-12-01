package com.ps.dao;

public interface IManyToManyDAO {

	public void saveDataUsingParent();
	public void saveDataUsingChild();
	public void loadDataUsingParent();
	public void deleteAllChildsOfAParent();
	public void deleteSpecificChildFromSpecificAParent();
	
}
