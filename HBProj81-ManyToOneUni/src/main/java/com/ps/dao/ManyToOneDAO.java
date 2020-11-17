package com.ps.dao;

public interface ManyToOneDAO {

	public void saveDataUsingChild();
	public void loadDataUsingChild();
	public void deleteAllChildsAndItsParent();
	public void deleteASpecificChild();
	public void deleteOneChildFromParent();
}
