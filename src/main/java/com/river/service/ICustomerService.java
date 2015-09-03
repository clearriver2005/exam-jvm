package com.river.service;

import java.util.List;

import com.river.entity.Customer;
/**
 * 交易
 * */
public interface ICustomerService {
	/** 创建交易 */
	Customer save(Customer customer);

	/** 按交易状态查询交易列表 */
	List<Customer> query();
	void init();
	void initIfNotExists();
}
