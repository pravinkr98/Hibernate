package com.ps.dao;

import java.util.List;

import com.ps.entity.InsurancePolicy;

public interface IInsurancePolicyDAO {

	public List<InsurancePolicy> getPoliciesByTenure(int start, int end);
}
