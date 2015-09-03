package com.river.service;
/****
 * @desc 客户业务类
 * @author wuqinghe
 * @date 205-09-01
 **/
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.river.dao.ICustomerDao;
import com.river.entity.Customer;
@Service
public class CustomerServiceImp implements ICustomerService {
	@Autowired
	private ICustomerDao customerDao;
	@Override
	@Transactional
	public Customer save(Customer customer) {
		return customerDao.save(customer);
	}
	@Override
	public List<Customer> query() {
		return customerDao.query();
	}
	@Override
	public void init() {
		customerDao.init();
	}
	@Override
	public void initIfNotExists() {
		customerDao.initIfNotExists();
	}

}
