package cbuc.homestay.controller.RoomCenter;

import cbuc.homestay.base.Result;
import cbuc.homestay.bean.Image;
import cbuc.homestay.bean.Merchant;
import cbuc.homestay.bean.RoomInfo;
import cbuc.homestay.evt.RoomInfoEvt;
import cbuc.homestay.mapper.PropertyMapper;
import cbuc.homestay.service.ImageService;
import cbuc.homestay.service.RoomInfoService;
import cbuc.homestay.utils.QiniuCloudUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

/**
 * @Explain: 商户端房源控制器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/2/13
 */
@Slf4j
@Controller
@RequestMapping("merchant")
@Api(value = "商户端房源控制器", description = "用来管理房源相关业务")
public class MerchantRoomController {

    @Autowired
    private RoomInfoService roomInfoService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private PropertyMapper propertyMapper;

    @ApiOperation("跳转房源管理界面")
    @GetMapping("/toRoom")
    public String toRoom() {
        return "merchant/room";
    }

    @ApiOperation("获取房源列表")
    @ResponseBody
    @GetMapping("/roomPage")
    public Object roomPage(@RequestParam(value = "current", defaultValue = "1") Integer pn,
                           @RequestParam(value = "size", defaultValue = "10") Integer size,
                           @RequestParam(value = "sort", defaultValue = "id") String sort,
                           @RequestParam(value = "order", defaultValue = "desc") String order,
                           String content, HttpSession session) {
        try {
            Merchant merchant = (Merchant) session.getAttribute("LOGIN_MERCHANT");
            PageHelper.startPage(pn, size, sort + " " + order);     //pn:页码  10：页大小
            RoomInfo room = RoomInfo.builder().mid(merchant.getId()).build();
            if (StringUtils.isNotBlank(content)) {
                room.setTitle(content);
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

    @RequestMapping("/toOpeRoom")
    public String toOpeRoom(String type,
                            @RequestParam(value = "rid", required = false) Long rid,
                            Model model) {
        if (null != rid) {
            RoomInfo roomInfo = roomInfoService.queryDetail(rid);
            RoomInfoEvt roomInfoEvt = new RoomInfoEvt();
            BeanUtils.copyProperties(roomInfo, roomInfoEvt);
            model.addAttribute("ri", roomInfoEvt);
        }
        return "merchant/opeRoom";
    }

    @ResponseBody
    @RequestMapping("/doSaveRoom")
    public Object doSaveRoom(@RequestParam("myFiles") MultipartFile[] myFiles,
                             @RequestParam("roomInfo") String roomInfo,
                             HttpSession session) {
        Long rid;
        try {
            Merchant merchant = (Merchant) session.getAttribute("LOGIN_MERCHANT");
            RoomInfoEvt roomInfoEvt = JSON.parseObject(roomInfo, RoomInfoEvt.class);
            rid = roomInfoService.doSaveRoomInfo(roomInfoEvt, merchant.getId(), roomInfoEvt.getId());
            for (MultipartFile myFile : myFiles) {
                byte[] bytes = myFile.getBytes();
                String imageName = UUID.randomUUID().toString();
                try {
                    String url = QiniuCloudUtil.put64image(bytes, imageName);
                    Image image = Image.builder().parentId(rid).url(url).origin("ROOM").build();
                    imageService.doAdd(image);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            rid = null;
            return Result.error("房源发布失败,请重新尝试");
        }
        return rid == null ? Result.error("房源发布失败,请重新尝试") : Result.success();
    }

    @ApiOperation("获取房源图片")
    @ResponseBody
    @RequestMapping("/getRoomImage")
    public Object getRoomImage(Long rid) {
        if (rid != null) {
            List<Image> imageList = imageService.queryList(Image.builder().parentId(rid).origin("ROOM").status("E").build());
            return Result.success(imageList);
        }else{
            return Result.success(null);
        }

    }

    @ApiOperation("删除房源图片")
    @ResponseBody
    @RequestMapping("/delImage")
    public Object delImage(String url) {
        String target = url.split("/")[3];
        QiniuCloudUtil.delete(target);
        int res = imageService.doDel(url);
        return res > 0 ? Result.success() : Result.error();
    }
}