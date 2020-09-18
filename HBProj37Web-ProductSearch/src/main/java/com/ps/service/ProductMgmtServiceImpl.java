package com.ps.service;

import com.ps.dao.ProductDAO;
import com.ps.dao.ProductDAOImpl;
import com.ps.dto.ProductDTO;
import com.ps.entity.Product;

public class ProductMgmtServiceImpl implements ProductMgmtService {

	private ProductDAO dao;
	
	public ProductMgmtServiceImpl() {
		dao=new ProductDAOImpl();
	}
	
	@Override
	public ProductDTO fetchProductDetails(int pid) {
		Product prod=null;
		ProductDTO dto=null;
		// use dao
		prod=dao.getProduct(pid);
		if(prod!=null) {
			dto=new ProductDTO();
			dto.setPid(prod.getPid());
			dto.setPname(prod.getPname());
			dto.setPrice(prod.getPrice());
			dto.setQty(prod.getQty());
			if(prod.getPrice()<=20000) {
				dto.setCategory("Economy");
			}
			else {
				dto.setCategory("Premium");
			}		
			return dto;
		}
		return null;
	}//method

}//class
