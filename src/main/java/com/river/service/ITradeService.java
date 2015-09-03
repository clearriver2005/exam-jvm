package com.river.service;

import java.util.List;

import com.river.entity.Trade;
/**
 * 交易
 * */
public interface ITradeService {
	/** 创建交易 */
	Trade save(Trade trade);

	/** 按交易状态查询交易列表 */
	List<Trade> query(Integer status);
	List<Trade> query();
	void del(Long tid);
}
