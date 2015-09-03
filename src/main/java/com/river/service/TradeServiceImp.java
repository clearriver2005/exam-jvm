package com.river.service;
/****
 * @desc 交易记录业务处理类
 * @author wuqinghe
 * @date 205-09-01
 **/
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.river.dao.ICustomerDao;
import com.river.dao.ITradeDao;
import com.river.entity.Customer;
import com.river.entity.Trade;
@Service
public class TradeServiceImp implements ITradeService{
	@Autowired
	private ITradeDao tradeDao;
	@Autowired
	private ICustomerDao customerDao;
	/****
	 * @desc 保存交易记录
	 * @author wuqinghe
	 * @date 205-09-01
	 **/
	@Override
	@Transactional
	public Trade save(Trade trade){
		if(trade!=null){
			Customer customer=trade.getCustomer();
			if(customer!=null&&customer.getCid()==null){
				customer=customerDao.save(trade.getCustomer());
				trade.setCustomer(customer);
			}
			tradeDao.save(trade);
		}
		return trade;
	}

	@Override
	public List<Trade> query(Integer status) {
		return tradeDao.query(status);
	}
	@Override
	public List<Trade> query() {
		return tradeDao.query();
	}

	@Override
	public void del(Long tid) {
		tradeDao.del(tid);
	}
}
