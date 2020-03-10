package cbuc.homestay.controller.BulletinCenter;

import cbuc.homestay.base.Result;
import cbuc.homestay.bean.Bulletin;
import cbuc.homestay.bean.Merchant;
import cbuc.homestay.evt.UserEvt;
import cbuc.homestay.service.BulletinService;
import cbuc.homestay.service.MerchantService;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Explain: 管理员之公告控制器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/13
 */
@Slf4j
@Controller
@RequestMapping("/admin")
@Api(value = "管理员之公告控制器", description = "管理公告相关内容")
public class AdminBulletinController {

    @Autowired
    private BulletinService bulletinService;

    @Autowired
    private MerchantService merchantService;

    @ApiOperation("跳转公告审核界面")
    @RequestMapping("/bulletinAudit")
    public String bulletinAudit() {
        return "admin/bulletinAudit";
    }

    @ApiOperation("获取公告审核列表")
    @ResponseBody
    @GetMapping("/bulletinAuditPage")
    public Object bulletinAuditPage(@RequestParam(value = "current", defaultValue = "1") Integer pn,
                                    @RequestParam(value = "size", defaultValue = "10") Integer size,
                                    @RequestParam(value = "sort", defaultValue = "id") String sort,
                                    @RequestParam(value = "order", defaultValue = "desc") String order,
                                    String content) {
        try {
            PageHelper.startPage(pn, size, sort + " " + order);     //pn:页码  10：页大小
            Bulletin bulletin = new Bulletin();
            if (StringUtils.isNotBlank(content)) {
                bulletin.setContent(content);
            }
            List<Bulletin> bulletinList = bulletinService.queryList(bulletin);
            bulletinList.stream().forEach(bl -> {
                Merchant merchant = merchantService.queryDetail(bl.getPublishId());
                bl.setPublishName(merchant.getMname());
            });
            PageInfo pageInfo = new PageInfo(bulletinList, 10);
            return Result.layuiTable(pageInfo.getTotal(), pageInfo.getList());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询结果异常");
            return Result.error("查询结果异常");
        }
    }

    @ApiOperation("禁用/启用公告")
    @ResponseBody
    @PostMapping("/opeBulletin")
    public Object opeBulletin(UserEvt evt) {
        try {
            Bulletin bulletin = new Bulletin();
            BeanUtils.copyProperties(evt, bulletin);
            int res = bulletinService.doEdit(bulletin);
            return res > 0 ? Result.success() : Result.error("操作失败");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("操作公告异常");
            return Result.error("操作公告异常");
        }
    }

    @ApiOperation("跳转发布公告界面")
    @GetMapping("/bulletinPublish")
    public String bulletinPublish() {
        return "admin/bulletinPublish";
    }

    @ApiOperation("发布公告")
    @ResponseBody
    @PutMapping("/pubBulletin")
    public Object pubBulletin(Bulletin bulletin, HttpSession session) {
        try {
            Merchant merchant = (Merchant) session.getAttribute("LOGIN_MERCHANT");
            bulletin.setPublishId(merchant.getId());
            int res = bulletinService.doAdd(bulletin);
            return res > 0 ? Result.success(bulletin) : Result.error("发布公告失败");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("发布公告异常");
            return Result.error("发布公告异常");
        }
    }


    @ApiOperation("获取历史公告")
    @ResponseBody
    @RequestMapping("getHisBulletin")
    public Object getHisBulletin(HttpSession session) {
        try {
            Merchant merchant = (Merchant) session.getAttribute("LOGIN_MERCHANT");
            Bulletin bulletin = Bulletin.builder().publishId(merchant.getId()).build();
            List<Bulletin> bulletinList = bulletinService.queryList(bulletin);
            return Result.success(bulletinList);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取历史公告异常");
            return Result.error("获取历史公告异常");
        }
    }

    @ApiOperation("获取公告详情")
    @GetMapping("toBulletinDetail")
    public String toBulletinDetail(Long id, Model model) {
        try {
            Bulletin bulletin = bulletinService.queryDetail(id);
            model.addAttribute("bulletin", bulletin);
            return "bulletinDetail";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取公告详情异常");
            return "bulletinDetail";
        }
    }
}
