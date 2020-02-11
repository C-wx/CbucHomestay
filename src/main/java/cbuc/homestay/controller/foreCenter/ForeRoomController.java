package cbuc.homestay.controller.foreCenter;

import cbuc.homestay.base.Result;
import cbuc.homestay.bean.*;
import cbuc.homestay.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Explain: 小程序端房间控制器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/2/4
 */
@Slf4j
@Controller
@Api(value = "小程序端房间控制器", description = "房源相关业务")
public class ForeRoomController {

    @Autowired
    private RoomInfoService roomInfoService;

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private MerchantService merchantService;

    @ApiOperation("获取房源列表")
    @ResponseBody
    @RequestMapping("/getAllRoom")
    public Object getAllRoom(@RequestParam(value = "current", defaultValue = "1") Integer pn,
                             @RequestParam(value = "size", defaultValue = "4") Integer size,
                             @RequestParam(value = "sort", defaultValue = "id") String sort,
                             @RequestParam(value = "order", defaultValue = "desc") String order,
                             String title, String type, String keyWord,
                             Long beginTime, Long endTime) {
        try {
            PageHelper.startPage(pn, size, sort + " " + order);     //pn:页码  10：页大小
            RoomInfo room = new RoomInfo().builder().auditStatus("SA").build();
            if (StringUtils.isNotBlank(type) && !"undefined".equals(type)) {
                room.setType(type);
            }
            if (StringUtils.isNotBlank(title)) {
                room.setTitle(title);
            }
            if (beginTime != null) {
                Date bt = new Date(beginTime);
                room.setBeginTime(bt);
            }
            if (endTime != null) {
                Date et = new Date(endTime);
                room.setEndTime(et);
            }
            List<RoomInfo> roomInfoList = roomInfoService.queryList(room);
            PageInfo pageInfo = new PageInfo(roomInfoList, 10);
            return Result.layuiTable(pageInfo.getTotal(), pageInfo.getList());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询结果异常");
            return Result.error("查询结果异常");
        }
    }

    @ApiOperation("获取活动房间列表")
    @ResponseBody
    @RequestMapping("/getActiveRoom")
    public Object getActiveRoom() {
        List<RoomInfo> roomInfoList = roomInfoService.queryActiveRoom();
        return Result.success(roomInfoList);
    }

    @ApiOperation("获取排行前5的房间列表")
    @ResponseBody
    @RequestMapping("/getTopRoom")
    public Object getTopRoom() {
        List<RoomInfo> roomInfoList = roomInfoService.queryTopRoom();
        return Result.success(roomInfoList);
    }

    @ApiOperation("获取房源详情")
    @ResponseBody
    @RequestMapping("/getRoomInfo")
    public Object getRoomInfo(Long id, String openId) {
        Favorite fe = favoriteService.queryDetail(id, openId);
        RoomInfo roomInfo = roomInfoService.queryDetail(id);
        List<Comment> commentList = commentService.queryList(Comment.builder().rid(roomInfo.getId()).type("1").build());    //当前房间的评论列表
        List<Comment> allComment = commentService.getSelfComment(roomInfo.getMid());    //获取当前房东下的所有评论列表
        commentList.stream().forEach(comment -> {
            User user = userService.queryDetail(comment.getCommentor());
            comment.setPublishName(user.getUname());
        });
        Merchant merchant = merchantService.queryDetail(roomInfo.getMid());
        roomInfo.setMerchant(merchant);
        roomInfo.setCommentList(commentList);
        roomInfo.setAllComment(allComment);
        if (Objects.nonNull(fe)) {
            if ("E".equals(fe.getStatus())) {
                roomInfo.setIsFavorite("true");
            } else {
                roomInfo.setIsFavorite("false");
            }
        } else {
            roomInfo.setIsFavorite("false");
        }
        return Result.success(roomInfo);
    }

    @ApiOperation(("/点赞房源"))
    @ResponseBody
    @RequestMapping("/doLikeRoom")
    public Object doLikeRoom(RoomInfo roomInfo, String openId) {
        Favorite fe = favoriteService.queryDetail(roomInfo.getId(), openId);
        Favorite favorite = Favorite.builder().rid(roomInfo.getId()).openId(openId).build();
        if (roomInfo.getLikeCount() > 0) {
            if (Objects.isNull(fe)) {
                favoriteService.doAdd(favorite);
            } else {
                favorite.setStatus("E");
                favoriteService.doEdit(favorite);
            }
        } else {
            if (Objects.nonNull(fe)) {
                favorite.setStatus("D");
                favoriteService.doEdit(favorite);
            }
        }
        RoomInfo ri = roomInfoService.queryDetail(roomInfo.getId());
        ri.setLikeCount(ri.getLikeCount() + roomInfo.getLikeCount());
        int res = roomInfoService.doEdit(ri);
        return res > 0 ? Result.success() : Result.error();
    }

    @ApiOperation("获取收藏列表")
    @ResponseBody
    @RequestMapping("getFavoriteList")
    public Object getFavoriteList(String openId) {
        List<Favorite> favorites = favoriteService.queryList(openId);
        favorites.stream().forEach(favorite -> {
            RoomInfo roomInfo = roomInfoService.queryDetail(favorite.getRid());
            favorite.setRoomInfo(roomInfo);
        });
        return Result.success(favorites);
    }
}
