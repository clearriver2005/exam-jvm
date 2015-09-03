package com.river.controller;
/****
 * @desc 交易信息
 * @author wuqinghe
 * @date 205-09-02
 **/
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.river.entity.Trade;
import com.river.service.ITradeService;
import com.river.util.Tool;
@Controller
@RequestMapping("/s/trade")
public class TradeController {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ITradeService tradeService;
	/****
	 * @desc 交易保存
	 * @author wuqinghe
	 * @date 205-09-02
	 **/
	@RequestMapping(value="/save",method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView save(Trade trade){
		ModelAndView mav=new ModelAndView(new MappingJackson2JsonView());
		if(trade.getAmount()!=null&&trade.getCustomer()!=null){
			tradeService.save(trade);
	        mav.addObject("trade",Tool.mapper(objectMapper, trade));
	        mav.addObject("success",true);
		}else {
			mav.addObject("success",false);
			mav.addObject("msg","参数输入有误!");
		}
        return mav;
    }
	/****
	 * @desc 按交易状态查询
	 * @author wuqinghe
	 * @date 205-09-02
	 **/
	@RequestMapping(value="/queryByStatus",method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView queryByStatus(Integer status){
        ModelAndView mav=new ModelAndView(new MappingJackson2JsonView());
        mav.addObject("tradelist",Tool.mapper(objectMapper,tradeService.query(status)));
		mav.addObject("success",true);
        return mav;
    }
	/****
	 * @desc 查询所有交易状态下的交易
	 * @author wuqinghe
	 * @date 205-09-02
	 **/
	@RequestMapping(value="/query",method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView query(){
        ModelAndView mav=new ModelAndView(new MappingJackson2JsonView());
        mav.addObject("tradelist",Tool.mapper(objectMapper,tradeService.query()));
		mav.addObject("success",true);
        return mav;
    }
	/****
	 * @desc 按交易状态查询
	 * @author wuqinghe
	 * @date 205-09-02
	 **/
	@RequestMapping(value="/query/{status}",method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView query(@PathVariable Integer status){
        ModelAndView mav=new ModelAndView(new MappingJackson2JsonView());
        mav.addObject("tradelist",Tool.mapper(objectMapper,tradeService.query(status)));
		mav.addObject("success",true);
        return mav;
    }
	/****
	 * @desc 按交易ID删除交易信息
	 * @author wuqinghe
	 * @date 205-09-02
	 **/
	@RequestMapping(value="/del",method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView del(Long tid){
		tradeService.del(tid);
        ModelAndView mav=new ModelAndView(new MappingJackson2JsonView());
        mav.addObject("success",true);
        return mav;
    }
}
