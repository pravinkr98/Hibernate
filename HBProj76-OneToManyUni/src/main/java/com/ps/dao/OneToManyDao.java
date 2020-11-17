package com.ps.dao;

public interface OneToManyDao {
	
	public void saveDataUsingParent();
	public void loadDataUsingParent();
	public void addNewChildToExistingParent();
	public void deletingAParentAndItsChild();
	public void deleteAllParentAndItsChild();
	public void deleteAllParentAndItsChild1();
	public void deleteAllChildsOfAParent();
	public void deleteOneChildFromCollectionOfChildsOfAParent();
	public void transferOneChildOfOneParentToAnotherParent();

}
