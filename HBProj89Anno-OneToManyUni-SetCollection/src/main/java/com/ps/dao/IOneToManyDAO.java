package com.ps.dao;

public interface IOneToManyDAO {

	public void saveDataUsingParent();
	public void loadDataUsingParent();
	public void loadDataUsingParentAndQBC();
	public void deleteOneChildFromCollectionOfChildsBelongingToAParent();
}
