package com.river.dao;

import java.util.List;

import com.river.entity.Trade;
/**
 * 交易
 * */
public interface ITradeDao{
	/** 创建交易 */
	Trade save(Trade trade);
	/** 按交易状态查询交易列表 */
	List<Trade> query(Integer status);
	/**查询所有交易记录*/
	List<Trade> query();
	/**按ID删除交易记录*/
	void del(Long tid);
}
