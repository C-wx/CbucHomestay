package cbuc.homestay.controller.foreCenter;

import cbuc.homestay.base.Result;
import cbuc.homestay.bean.Comment;
import cbuc.homestay.bean.Order;
import cbuc.homestay.bean.RoomInfo;
import cbuc.homestay.bean.User;
import cbuc.homestay.service.CommentService;
import cbuc.homestay.service.OrderService;
import cbuc.homestay.service.RoomInfoService;
import cbuc.homestay.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @Explain: 小程序端评论控制器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/2/9
 */
@Slf4j
@Controller
@Api(value = "小程序端评论控制器", description = "处理评论相关操作")
public class ForeCommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RoomInfoService roomInfoService;

    @ApiOperation("获取自己的评论列表")
    @ResponseBody
    @RequestMapping("/getCommentList")
    public Object getCommentList(String openId) {
        List<Comment> commentList = null;
        try {
            User user = userService.queryDetail(openId);
            commentList = commentService.getCommentList(user.getId());
            commentList.stream().forEach(comment -> {
                Order order = orderService.queryDetail(comment.getOid());
                RoomInfo roomInfo = roomInfoService.queryDetail(comment.getRid());
                comment.setOrder(order);                    //对应订单
                comment.setRoomInfo(roomInfo);              //对应房源
            });
        } catch (Exception e) {
            e.printStackTrace();
            log.info("获取私人评论异常");
        }
        return Result.success(commentList);
    }

    @ApiOperation("评论")
    @ResponseBody
    @RequestMapping("/doComment")
    public Object doComment(Comment comment, String openId) {
        int res = 0;
        try {
            Order order = Order.builder().status("YR").confirmTime(new Date()).id(comment.getOid()).build();
            orderService.doEdit(order);                                         //更改订单状态
            RoomInfo roomInfo = roomInfoService.queryDetail(comment.getRid());
            roomInfo.setCommentCount(roomInfo.getCommentCount() + 1);           //设置房源评论数+1
            roomInfoService.doEdit(roomInfo);
            User user = userService.queryDetail(openId);
            comment.setCommentor(user.getId());
            res = commentService.doAdd(comment);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("评论异常");
        }
        return res > 0 ? Result.success() : Result.error();
    }
}
