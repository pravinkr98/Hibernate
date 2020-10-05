package com.ps.service;

import java.util.List;

import com.ps.dto.InsurancePolicyDTO;

public interface IInsurancePolicyMgmtService {
	
	public long fetchPagesCount(int pageSize);
	public List<InsurancePolicyDTO> fetchPageData(int pageSize, int pageNo);
}
