package cbuc.homestay.service;

import cbuc.homestay.CommonEnum.StatusEnum;
import cbuc.homestay.bean.Order;
import cbuc.homestay.bean.OrderExample;
import cbuc.homestay.bean.RoomInfo;
import cbuc.homestay.bean.RoomInfoExample;
import cbuc.homestay.mapper.OrderMapper;
import cbuc.homestay.mapper.RoomInfoMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Explain: 订单处理器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/14
 */
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RoomInfoMapper roomInfoMapper;

    public List<Order> queryList() {
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andStatusNotEqualTo(StatusEnum.D.getValue());
        return orderMapper.selectByExample(orderExample);
    }

    public List<Map<String, Object>> querySalesData(Long mid,String beginTime, String endTime) {
        return orderMapper.querySalesData(mid,beginTime, endTime);
    }

    public Order queryLast(Long mid) {
        return orderMapper.queryLast(mid);
    }

    public int doAdd(Order order) {
        return orderMapper.insertSelective(order);
    }

    public int doEdit(Order o) {
        return orderMapper.updateByPrimaryKeySelective(o);
    }

    public List<Order> queryList(Order order) {
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        if (StringUtils.isNotBlank(order.getStatus()) && !"ALL".equals(order.getStatus())) {
            criteria.andStatusEqualTo(order.getStatus());
        }
        if (StringUtils.isNotBlank(order.getOpenId())) {
            criteria.andOpenIdEqualTo(order.getOpenId());
        }
        if (order.getMid() != null) {
            ArrayList<Long> values = new ArrayList<>();
            RoomInfoExample example = new RoomInfoExample();
            example.createCriteria().andMidEqualTo(order.getMid());
            List<RoomInfo> roomInfoList = roomInfoMapper.selectByExample(example);
            roomInfoList.stream().forEach(roomInfo -> {
                values.add(roomInfo.getId());
            });
            criteria.andRidIn(values);
        }

        return orderMapper.selectByExample(orderExample);
    }

    public Order queryDetail(Long oid) {
        return orderMapper.selectByPrimaryKey(oid);
    }
}
