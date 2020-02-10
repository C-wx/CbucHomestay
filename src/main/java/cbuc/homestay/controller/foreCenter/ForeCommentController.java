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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Explain: 小程序端评论控制器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/2/9
 */
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

    @ResponseBody
    @RequestMapping("/getCommentList")
    public Object getCommentList(String openId) {
        User user = userService.queryDetail(openId);
        List<Comment> commentList = commentService.getCommentList(user.getId());
        commentList.stream().forEach(comment -> {
            Order order = orderService.queryDetail(comment.getOid());
            RoomInfo roomInfo = roomInfoService.queryDetail(comment.getRid());
            comment.setOrder(order);
            comment.setRoomInfo(roomInfo);
        });
        return Result.success(commentList);
    }

    @ResponseBody
    @RequestMapping("/doComment")
    public Object doComment(Comment comment, String openId) {
        Order order = Order.builder().status("YR").id(comment.getOid()).build();
        orderService.doEdit(order);
        User user = userService.queryDetail(openId);
        comment.setCommentor(user.getId());
        int res = commentService.doAdd(comment);
        return res > 0 ? Result.success() : Result.error();
    }
}
