package cbuc.homestay.service;

import cbuc.homestay.CommonEnum.StatusEnum;
import cbuc.homestay.bean.Order;
import cbuc.homestay.bean.OrderExample;
import cbuc.homestay.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Explain:   订单处理器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/14
 */
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public List<Order> queryList() {
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andStatusNotEqualTo(StatusEnum.D.getValue());
        return orderMapper.selectByExample(orderExample);
    }

    public List<Map<String, Object>> querySalesData(String beginTime, String endTime) {
        return orderMapper.querySalesData(beginTime,endTime);
    }

    public Order queryLast() {
        return orderMapper.queryLast();
    }

    public int doAdd(Order order) {
        return orderMapper.insertSelective(order);
    }

    public int doEdit(Order o) {
        return orderMapper.updateByPrimaryKeySelective(o);
    }
}
