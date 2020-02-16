package cbuc.homestay.controller.RoomCenter;

import cbuc.homestay.base.Result;
import cbuc.homestay.bean.Image;
import cbuc.homestay.bean.Merchant;
import cbuc.homestay.bean.RoomInfo;
import cbuc.homestay.evt.UserEvt;
import cbuc.homestay.service.ImageService;
import cbuc.homestay.service.MerchantService;
import cbuc.homestay.service.RoomInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Explain: 管理员之房源管理控制器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/13
 */
@Slf4j
@Controller
@RequestMapping("/admin")
@Api(value = "管理员之房源管理控制器", description = "管理房源相关内容")
public class AdminRoomController {

    @Autowired
    private RoomInfoService roomInfoService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private ImageService imageService;

    @ApiOperation("跳转房源管理界面")
    @GetMapping("/roomManage")
    public String roomManage() {
        return "admin/roomManage";
    }

    @ApiOperation("获取房源列表")
    @ResponseBody
    @GetMapping("/roomManagePage")
    public Object roomManagePage(@RequestParam(value = "current", defaultValue = "1") Integer pn,
                                 @RequestParam(value = "size", defaultValue = "10") Integer size,
                                 @RequestParam(value = "sort", defaultValue = "id") String sort,
                                 @RequestParam(value = "order", defaultValue = "desc") String order,
                                 String content) {
        try {
            PageHelper.startPage(pn, size, sort + " " + order);     //pn:页码  10：页大小
            List<RoomInfo> roomInfoList = roomInfoService.queryList(new RoomInfo());
            roomInfoList.stream().forEach(rl -> {
                Merchant merchant = merchantService.queryDetail(rl.getMid());
                rl.setPublishName(merchant.getMname());
            });
            PageInfo pageInfo = new PageInfo(roomInfoList, 10);
            return Result.layuiTable(pageInfo.getTotal(), pageInfo.getList());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询结果异常");
            return Result.error("查询结果异常");
        }
    }

    @ApiOperation("禁用/启用房源")
    @ResponseBody
    @PostMapping("/opeRoom")
    public Object opeRoom(UserEvt evt) {
        try {
            RoomInfo roomInfo = new RoomInfo();
            BeanUtils.copyProperties(evt, roomInfo);
            int res = roomInfoService.doEdit(roomInfo);
            return res > 0 ? Result.success() : Result.error("操作失败");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("操作房源异常");
            return Result.error("操作房源异常");
        }
    }

    @ApiOperation("跳转房间详情页")
    @RequestMapping("/roomDetail")
    public String roomDetail(Long rid, Model model) {
        RoomInfo roomInfo = roomInfoService.queryDetail(rid);
        Merchant merchant = merchantService.queryDetail(roomInfo.getMid());
        roomInfo.setPublishName(merchant.getMname());
        Image image = Image.builder().parentId(roomInfo.getId()).origin("ROOM").status("E").build();
        List<Image> images = imageService.queryList(image);
        roomInfo.setImages(images);
        model.addAttribute("roomInfo", roomInfo);
        return "roomDetail";
    }
}