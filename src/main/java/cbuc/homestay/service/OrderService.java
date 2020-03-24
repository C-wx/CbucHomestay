package cbuc.homestay.service;

import cbuc.homestay.CommonEnum.StatusEnum;
import cbuc.homestay.bean.*;
import cbuc.homestay.mapper.OrderMapper;
import cbuc.homestay.mapper.RoomInfoMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @Autowired
    private UserService userService;

    public List<Order> queryList() {
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andStatusNotEqualTo(StatusEnum.D.getValue());
        return orderMapper.selectByExample(orderExample);
    }

    public List<Map<String, Object>> querySalesData(Long mid, String beginTime, String endTime) {
        return orderMapper.querySalesData(mid, beginTime, endTime);
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
        criteria.andStatusNotEqualTo(StatusEnum.D.getValue());
        orderExample.setOrderByClause("id desc");
        return orderMapper.selectByExample(orderExample);
    }

    public Order queryDetail(Long oid) {
        return orderMapper.selectByPrimaryKey(oid);
    }

    public List<Order> queryMerOrderList(String status, Long id) {
        if ("ALL".equals(status)) {
            status = "%";
        }
        if ("DD".equals(status)) {
            status = "_" + status;
        }
        return orderMapper.queryMerOrderList(status, id);
    }

    public List<Map<String, Object>> getCustomerList(Long mid) {
        List<Map<String, Object>> customerMap = orderMapper.getCustomerList(mid);
        for (Map<String, Object> map : customerMap) {
            Set<Map.Entry<String, Object>> entries = map.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                if ("openId".equals(entry.getKey())) {
                    User user = userService.queryDetail((String) entry.getValue());
                    map.put("userInfo", user);
                    break;
                }
            }
        }
        return customerMap;
    }

    public Map<String, Object> getTodayData(Long mid, int i) {
        return orderMapper.getSalesData(mid, i);
    }

    public Map<String, Object> getTotalCountAndPrice(Long mid) {
        return orderMapper.getTotalCountAndPrice(mid);
    }

    public Integer getUserCount(Boolean currentMonth, Long mid) {
        return orderMapper.getUserCount(currentMonth, mid);
    }

    public Integer getUnreadOrderCount(Long mid) {
        return orderMapper.getUnreadOrderCount(mid);
    }

    public int doUpdateReadStatus(Long mid) {
        return orderMapper.doUpdateReadStatus(mid);
    }

    public int doService(Order order) {
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andOrderCodeEqualTo(order.getOrderCode());
        return orderMapper.updateByExampleSelective(order, orderExample);
    }
}
