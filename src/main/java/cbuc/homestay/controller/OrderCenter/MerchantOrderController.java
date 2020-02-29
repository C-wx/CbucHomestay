package cbuc.homestay.controller.OrderCenter;

import cbuc.homestay.base.Result;
import cbuc.homestay.bean.*;
import cbuc.homestay.service.ImageService;
import cbuc.homestay.service.OrderService;
import cbuc.homestay.service.RoomInfoService;
import cbuc.homestay.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * @Explain: 商户端订单控制器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/2/28
 */
@Slf4j
@Controller
@Api(value = "商户端订单控制器", description = "操作订单相关业务")
public class MerchantOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoomInfoService roomInfoService;

    @Autowired
    private ImageService imageService;

    @ApiOperation("跳转订单管理界面")
    @RequestMapping("/toOrderCenter")
    public String toOrderCenter() {
        return "merchant/order";
    }


    @ApiOperation("获取订单列表")
    @ResponseBody
    @GetMapping("/orderPage")
    public Object orderPage(@RequestParam(value = "current", defaultValue = "1") Integer pn,
                            @RequestParam(value = "size", defaultValue = "10") Integer size,
                            @RequestParam(value = "sort", defaultValue = "id") String sort,
                            @RequestParam(value = "order", defaultValue = "desc") String order,
                            String orderCode, HttpSession session, String status, Long mid) {
        try {
            Merchant merchant = (Merchant) session.getAttribute("LOGIN_MERCHANT");
            PageHelper.startPage(pn, size, sort + " " + order);     //pn:页码  10：页大小
            Long merchantId;
            if (Objects.isNull(merchant)) {
                merchantId = mid;
            } else {
                merchantId = merchant.getId();
            }
            List<Order> orderList = orderService.queryMerOrderList(status, merchantId);
            orderList.stream().forEach(o -> {
                User user = userService.queryDetail(o.getOpenId());
                RoomInfo roomInfo = roomInfoService.queryDetail(o.getRid());
                List<Image> images = imageService.queryList(Image.builder().parentId(roomInfo.getId()).origin("ROOM").build());
                roomInfo.setImages(images);
                o.setUser(user);
                o.setRoomInfo(roomInfo);

            });
            PageInfo pageInfo = new PageInfo(orderList, 10);
            return Result.layuiTable(pageInfo.getTotal(), pageInfo.getList());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询结果异常");
            return Result.error("查询结果异常");
        }
    }

    @ApiOperation("获取未读的订单信息")
    @ResponseBody
    @RequestMapping("/getUnreadOrderCount")
    public Object getUnreadOrderCount(Long mid) {
        Integer unreadCount = orderService.getUnreadOrderCount(mid);
        return Result.success(unreadCount);
    }

    @ApiOperation("修改订单读取状态")
    @ResponseBody
    @RequestMapping("/doRead")
    public Object doRead(Long mid) {
        int res = orderService.doUpdateReadStatus(mid);
        return res > 0 ? Result.success() : Result.error();
    }
}