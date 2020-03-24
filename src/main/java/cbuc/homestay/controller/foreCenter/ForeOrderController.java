package cbuc.homestay.controller.foreCenter;

import cbuc.homestay.base.Result;
import cbuc.homestay.bean.Image;
import cbuc.homestay.bean.Order;
import cbuc.homestay.bean.RoomInfo;
import cbuc.homestay.service.ImageService;
import cbuc.homestay.service.OrderService;
import cbuc.homestay.service.RoomInfoService;
import cbuc.homestay.utils.SendMessageUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Explain: 小程序端订单控制器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/2/7
 */
@Slf4j
@RestController
@Api(value = "小程序端订单控制器", description = "用于操作订单")
public class ForeOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RoomInfoService roomInfoService;

    @Autowired
    private ImageService imageService;

    @ApiOperation("生成订单")
    @RequestMapping("/doSaveOrder")
    public Object doSaveOrder(@RequestParam("order") String order) {
        try {
            Order o = JSONObject.parseObject(order, Order.class);
            int res;
            if (o.getId() != null) {
                o.setPayTime(new Date());
                res = orderService.doEdit(o);
                RoomInfo roomInfo = roomInfoService.queryDetail(o.getRid());
                roomInfo.setBeginTime(o.getBeginTime());
                roomInfo.setEndTime(o.getEndTime());
                roomInfo.setSales(roomInfo.getSales() + 1);
                roomInfoService.doEdit(roomInfo);
            } else {
                String s = SendMessageUtil.getRandomCode(6) + UUID.randomUUID().toString().replace("-", "").substring(0, 6);
                o.setOrderCode(s);
                res = orderService.doAdd(o);
            }
            return res > 0 ? Result.success(o.getId()) : Result.error();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @ApiOperation("获取订单列表")
    @RequestMapping("/getOrderList")
    public Object getOrderList(String openId, String status) {
        try {
            Order order = Order.builder().openId(openId).status(status).build();
            List<Order> orderList = orderService.queryList(order);
            orderList.stream().forEach((o) -> {
                RoomInfo roomInfo = roomInfoService.queryDetail(o.getRid());
                List<Image> images = imageService.queryList(Image.builder().parentId(roomInfo.getId()).origin("ROOM").status("E").build());
                roomInfo.setImages(images);
                o.setRoomInfo(roomInfo);
            });
            return Result.success(orderList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @ApiOperation("操作订单")
    @RequestMapping("/doOpeOrder")
    public Object doOpeOrder(String status, Long id) {
        try {
            Order order = Order.builder().id(id).status(status).build();
            int res = orderService.doEdit(order);
            return res > 0 ? Result.success() : Result.error();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @ApiOperation("发起售后")
    @RequestMapping("/doService")
    public Object doService(Order order) {
        try {
            int res = orderService.doService(order);
            return res > 0 ? Result.success() : Result.error();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }
}
