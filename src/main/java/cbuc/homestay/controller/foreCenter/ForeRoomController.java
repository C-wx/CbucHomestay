package cbuc.homestay.controller.foreCenter;

import cbuc.homestay.base.Result;
import cbuc.homestay.bean.*;
import cbuc.homestay.evt.RoomInfoEvt;
import cbuc.homestay.mapper.PropertyMapper;
import cbuc.homestay.service.*;
import cbuc.homestay.utils.QiniuCloudUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private ImageService imageService;

    @Autowired
    private PropertyMapper propertyMapper;

    @ApiOperation("获取房源列表")
    @ResponseBody
    @RequestMapping("/getAllRoom")
    public Object getAllRoom(@RequestParam(value = "current", defaultValue = "1") Integer pn,
                             @RequestParam(value = "size", defaultValue = "4") Integer size,
                             @RequestParam(value = "sort", defaultValue = "id") String sort,
                             @RequestParam(value = "order", defaultValue = "desc") String order,
                             String title, String type, String keyWord,
                             Long beginTime, Long endTime,
                             @RequestParam(value = "merchantId", required = false) Long merchantId,
                             @RequestParam(value = "status", required = false) String status) {
        try {
            PageHelper.startPage(pn, size, sort + " " + order);     //pn:页码  10：页大小
            RoomInfo room = new RoomInfo().builder().auditStatus("SA").build();
            if (StringUtils.isNotBlank(type) && !"undefined".equals(type)) {
                room.setType(type);         //根据房源类型查询
            }
            if (StringUtils.isNotBlank(title)) {
                room.setTitle(title);       //根据房源标题查询
            }
            /**查询入住时间*/
            if (beginTime != null) {
                Date bt = new Date(beginTime);
                room.setBeginTime(bt);
            }
            if (endTime != null) {
                Date et = new Date(endTime);
                room.setEndTime(et);
            }
            /**---end---**/
            if (null != merchantId) {
                room.setMid(merchantId);        //查询对应商家的店铺
            }
            if (StringUtils.isNotBlank(status)) {           //根据房源状态查询
                room.setStatus(status);
            }
            List<RoomInfo> roomInfoList = roomInfoService.queryList(room);
            roomInfoList.stream().forEach(roomInfo -> {
                List<Image> images = imageService.queryList(Image.builder().parentId(roomInfo.getId()).origin("ROOM").status("E").build());
                roomInfo.setImages(images);         //获取房源的图片信息
            });
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
        roomInfoList.stream().forEach(roomInfo -> {
            List<Image> images = imageService.queryList(Image.builder().parentId(roomInfo.getId()).origin("ROOM").status("E").build());
            roomInfo.setImages(images);             //获取房源的图片信息
        });
        return Result.success(roomInfoList);
    }

    @ApiOperation("获取排行前5的房间列表")
    @ResponseBody
    @RequestMapping("/getTopRoom")
    public Object getTopRoom() {
        List<RoomInfo> roomInfoList = roomInfoService.queryTopRoom();
        roomInfoList.stream().forEach(roomInfo -> {
            List<Image> images = imageService.queryList(Image.builder().parentId(roomInfo.getId()).origin("ROOM").status("E").build());
            roomInfo.setImages(images);             //获取房源的图片信息
        });
        return Result.success(roomInfoList);
    }

    @ApiOperation("获取房源详情")
    @ResponseBody
    @RequestMapping("/getRoomInfo")
    public Object getRoomInfo(Long id, String openId) {
        RoomInfo roomInfo = roomInfoService.queryDetail(id);
        List<Comment> commentList = commentService.queryList(Comment.builder().rid(roomInfo.getId()).type("1").build());    //当前房间的评论列表
        List<Comment> allComment = commentService.getSelfComment(roomInfo.getMid());    //获取当前房东下的所有评论列表
        commentList.forEach(comment -> {
            List<Comment> comments = commentService.queryList(Comment.builder().rid(comment.getId()).type("2").build());
            Comment replyComment = comments.size() > 0 ? comments.get(0) : null;
            if (!Objects.isNull(replyComment)) {
                Merchant merchant = merchantService.queryDetail(replyComment.getRid());
                replyComment.setPublishName(merchant.getMname());
                comment.setChildComment(replyComment);
            }
        });
        List<Image> images = imageService.queryList(Image.builder().parentId(roomInfo.getId()).origin("ROOM").status("E").build());
        PropertyExample propertyExample = new PropertyExample();
        propertyExample.createCriteria().andRidEqualTo(roomInfo.getId()).andStatusEqualTo("E");
        List<Property> properties = propertyMapper.selectByExample(propertyExample);
        commentList.stream().forEach(comment -> {
            User user = userService.queryDetail(comment.getCommentor());
            comment.setPublishName(user.getUname());
        });
        Merchant merchant = merchantService.queryDetail(roomInfo.getMid());
        roomInfo.setImages(images);
        roomInfo.setMerchant(merchant);
        roomInfo.setCommentList(commentList);
        roomInfo.setAllComment(allComment);
        roomInfo.setPropertyList(properties);
        if (StringUtils.isNotBlank(openId)) {
            Favorite fe = favoriteService.queryDetail(id, openId);
            if (Objects.nonNull(fe)) {
                if ("E".equals(fe.getStatus())) {
                    roomInfo.setIsFavorite("true");
                } else {
                    roomInfo.setIsFavorite("false");
                }
            } else {
                roomInfo.setIsFavorite("false");
            }
        }
        return Result.success(roomInfo);
    }

    @ApiOperation(("/点赞房源"))
    @ResponseBody
    @RequestMapping("/doLikeRoom")
    public Object doLikeRoom(RoomInfo roomInfo, String openId) {
        int res = 0;
        try {
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
            res = roomInfoService.doEdit(ri);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("点赞房源异常");
        }
        return res > 0 ? Result.success() : Result.error();
    }

    @ApiOperation("获取我的收藏列表")
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

    @ApiOperation("获取热门收藏列表")
    @ResponseBody
    @RequestMapping("getHotFavoriteList")
    public Object getHotFavoriteList() {
        List<Favorite> favoriteList = roomInfoService.queryHotRoom();
        return Result.success(favoriteList);
    }

    @ApiOperation("获取房源信息")
    @ResponseBody
    @RequestMapping("/getRoomDetail")
    public Object getRoomDetail(@RequestParam(value = "rid", required = false) Long rid) {
        RoomInfo roomInfo = roomInfoService.queryDetail(rid);
        List<Image> images = imageService.queryList(Image.builder().parentId(rid).origin("ROOM").status("E").build());
        roomInfo.setImages(images);
        RoomInfoEvt roomInfoEvt = new RoomInfoEvt();
        BeanUtils.copyProperties(roomInfo, roomInfoEvt);
        return Result.success(roomInfoEvt);
    }

    @ApiOperation("保存房源图片")
    @ResponseBody
    @RequestMapping("doSaveRoomImages")
    public Object doSaveRoomImages(@RequestParam(value = "file", required = false) MultipartFile file,
                                   @RequestParam(value = "rid", required = false) Long rid) {
        Image image = Image.builder().parentId(rid).origin("ROOM").build();
        if (file != null) {
            if (StringUtils.isNotBlank(file.getOriginalFilename())) {
                String url = QiniuCloudUtil.uploadFile(file);
                image.setUrl(url);
            }
        }
        int res = imageService.doAdd(image);
        return res > 0 ? Result.success("上传成功") : Result.error("图片上传异常");
    }

    @ApiOperation("操作房源信息")
    @ResponseBody
    @RequestMapping("/doOpeRoom")
    public Object doOpeRoom(RoomInfo roomInfo) {
        int res;
        try {
            if ("DELETE".equals(roomInfo.getStatus())) {
                res = roomInfoService.doDel(roomInfo);
            } else {
                res = roomInfoService.doEdit(roomInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            res = 0;
            log.info("操作房源异常");
        }
        return res > 0 ? Result.success() : Result.error("操作异常");
    }

}