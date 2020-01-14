package cbuc.homestay.controller.commentCenter;

import cbuc.homestay.base.Result;
import cbuc.homestay.bean.Comment;
import cbuc.homestay.bean.RoomInfo;
import cbuc.homestay.bean.User;
import cbuc.homestay.evt.UserEvt;
import cbuc.homestay.service.CommentService;
import cbuc.homestay.service.RoomInfoService;
import cbuc.homestay.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Explain:   管理员之评论控制器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/13
 */
@Slf4j
@Controller
@RequestMapping("/admin")
@Api(value = "管理员之评论控制器", description = "管理评论相关内容")
public class AdminCommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoomInfoService roomInfoService;

    @ApiOperation("跳转评论管理界面")
    @RequestMapping("/commentManage")
    public String bulletinAudit() {
        return "admin/commentManage";
    }

    @ApiOperation("获取评论列表")
    @ResponseBody
    @GetMapping("/commentManagePage")
    public Object commentManagePage(@RequestParam(value = "current", defaultValue = "1") Integer pn,
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
            List<Comment> CommentList = commentService.queryList(comment);
            CommentList.stream().forEach(cl->{
                User user = userService.queryDetail(cl.getCommentor());
                if ("1".equals(cl.getType())) {
                    RoomInfo roomInfo = roomInfoService.queryDetail(cl.getRid());
                    cl.setOrigin(roomInfo.getTitle());
                }else{
                    Comment cominfo = commentService.queryDetail(cl.getRid());
                    cl.setOrigin(cominfo.getContent());
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

    @ApiOperation("禁用/评论公告")
    @ResponseBody
    @PostMapping("/opeComment")
    public Object opeComment(UserEvt evt) {
        try {
            Comment comment = new Comment();
            BeanUtils.copyProperties(evt,comment);
            int res = commentService.doEdit(comment);
            return res>0?Result.success(): Result.error("操作失败");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("操作评论异常");
            return Result.error("操作评论异常");
        }
    }
}
