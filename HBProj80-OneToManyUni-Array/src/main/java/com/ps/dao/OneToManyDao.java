package com.ps.dao;

public interface OneToManyDao {
	
	public void saveDataUsingParent();
	public void loadDataUsingParent();
	public void deleteOneChildFromCollectionOfChildsOfAParent();

}
