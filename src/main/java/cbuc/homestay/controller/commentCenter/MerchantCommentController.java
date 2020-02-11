package cbuc.homestay.controller.commentCenter;

import cbuc.homestay.base.Result;
import cbuc.homestay.bean.Comment;
import cbuc.homestay.bean.Merchant;
import cbuc.homestay.bean.RoomInfo;
import cbuc.homestay.bean.User;
import cbuc.homestay.service.CommentService;
import cbuc.homestay.service.RoomInfoService;
import cbuc.homestay.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Explain: 商家端评论控制器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/2/10
 */
@Slf4j
@Controller
@RequestMapping("/merchant")
@Api(value = "商家端评论控制器", description = "商家端评论相关业务")
public class MerchantCommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoomInfoService roomInfoService;

    @ApiOperation("跳转评论管理界面")
    @RequestMapping("/toCommentManage")
    public String bulletinAudit() {
        return "merchant/comment";
    }


    @ApiOperation("获取评论列表")
    @ResponseBody
    @GetMapping("/commentPage")
    public Object commentPage(@RequestParam(value = "current", defaultValue = "1") Integer pn,
                              @RequestParam(value = "size", defaultValue = "10") Integer size,
                              @RequestParam(value = "sort", defaultValue = "id") String sort,
                              @RequestParam(value = "order", defaultValue = "desc") String order,
                              String content) {
        try {
            PageHelper.startPage(pn, size, sort + " " + order);     //pn:页码  10：页大小
            Comment comment = new Comment();
            if (StringUtils.isNotBlank(content)) {
                comment.setContent(content);
            }
            comment.setType("1");
            List<Comment> CommentList = commentService.queryList(comment);
            CommentList.stream().forEach(cl -> {
                User user = userService.queryDetail(cl.getCommentor());
                RoomInfo roomInfo = roomInfoService.queryDetail(cl.getRid());
                cl.setOrigin(roomInfo.getTitle());
                if (!CollectionUtils.isEmpty(commentService.queryList(Comment.builder().rid(cl.getId()).type("2").status("E").build()))) {
                    Comment childComment = commentService.queryList(Comment.builder().rid(cl.getId()).type("2").status("E").build()).get(0);
                    cl.setChildComment(childComment);
                }
                cl.setPublishName(user.getUname());
            });
            PageInfo pageInfo = new PageInfo(CommentList, 10);
            return Result.layuiTable(pageInfo.getTotal(), pageInfo.getList());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询结果异常");
            return Result.error("查询结果异常");
        }
    }

    @RequestMapping("/toReply")
    public String toReply(HttpSession session, Model model, Long oid, Long rid) {
        Merchant merchant = (Merchant) session.getAttribute("LOGIN_MERCHANT");
        List<Comment> commentList = commentService.queryList(Comment.builder().commentor(merchant.getId()).rid(rid).type("2").oid(oid).build());
        if (!CollectionUtils.isEmpty(commentList)) {
            model.addAttribute("comment", commentList.get(0));
        }
        model.addAttribute("commentor", merchant.getId());
        model.addAttribute("oid", oid);
        model.addAttribute("rid", rid);
        return "merchant/reply";
    }

    @ApiOperation("回复评论操作")
    @ResponseBody
    @RequestMapping("/doReply")
    public Object doReply(Comment comment) {
        Comment c = commentService.queryDetail(comment.getRid());
        c.setCommentCount(c.getCommentCount() + 1);
        commentService.doEdit(c);
        int res = commentService.doAdd(comment);
        return res > 0 ? Result.success() : Result.error();
    }
}
