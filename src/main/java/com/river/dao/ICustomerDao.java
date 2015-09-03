package com.river.dao;

import java.util.List;

import com.river.entity.Customer;

/**
 * 客户
 * */
public interface ICustomerDao{
	/** 保存用户 */
	Customer save(Customer customer);
	/**查询所有用户*/
	List<Customer> query();
	/****
	 * @desc 初始化数据表结构
	 * @author wuqinghe
	 * @date 205-09-02
	 **/
	void init();
	/****
	 * @desc 初始化数据表结构
	 * @author wuqinghe
	 * @date 205-09-02
	 **/
	void initIfNotExists();
}
