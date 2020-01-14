package cbuc.homestay.controller.MerchantCenter;

import cbuc.homestay.CommonEnum.LevelEnum;
import cbuc.homestay.CommonEnum.OriginEnum;
import cbuc.homestay.CommonEnum.StatusEnum;
import cbuc.homestay.base.Result;
import cbuc.homestay.bean.*;
import cbuc.homestay.evt.UserEvt;
import cbuc.homestay.service.*;
import cbuc.homestay.utils.SendMessageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Explain:
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/11
 */
@Slf4j
@Api(value = "管理员商户控制器", description = "管理员处理商户相关操作")
@Controller
@RequestMapping("/admin")
public class AdminMerchantController {

    @Autowired
    private ApplyService applyService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private AuditLogService auditLogService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private BulletinService bulletinService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private RoomInfoService roomInfoService;

    @ApiOperation("跳转数据统计页面")
    @GetMapping("/dataStatistic")
    public String dataStatisticList() {
        return null;
    }

    @ApiOperation("跳转到商户审核页面")
    @GetMapping("/merchantAudit")
    public String toMerchantAudit() {
        return "admin/merchantAudit";
    }

    @ApiOperation("获取商户审核列表")
    @ResponseBody
    @GetMapping("/merchantApplyPage")
    public Object merchantApplyPage(@RequestParam(value = "current", defaultValue = "1") Integer pn,
                              @RequestParam(value = "size", defaultValue = "10") Integer size,
                              @RequestParam(value = "sort", defaultValue = "id") String sort,
                              @RequestParam(value = "order", defaultValue = "desc") String order,
                              String title) {
        try {
            PageHelper.startPage(pn, size, sort + " " + order);     //pn:页码  10：页大小
            List<Apply> auditList = applyService.queryList(title);
            auditList.stream().forEach(al -> {
                Image image = imageService.queryDetail(al.getId(), OriginEnum.LICENSE.getValue());
                al.setLicenseUrl(image.getUrl());
            });
            PageInfo pageInfo = new PageInfo(auditList, 10);
            return Result.layuiTable(pageInfo.getTotal(), pageInfo.getList());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询结果异常");
            return Result.error("查询结果异常");
        }
    }

    @ApiOperation("弹出审核模态框")
    @GetMapping("/toAudit")
    public String toAudit(String parentId, String type, Model model) {
        model.addAttribute("parentId", parentId);
        model.addAttribute("type", type);
        return "admin/audit";
    }

    @ApiOperation("审核操作")
    @ResponseBody
    @RequestMapping("/doAudit")
    public Object doAudit(AuditLog auditLog) {
        try {
            int res = auditLogService.doAdd(auditLog);
            switch (auditLog.getType()) {
                case "MERCHANT":            //审核商家
                    Apply apply = applyService.queryDetail(auditLog.getParentId());
                    apply.setAuditStatus(auditLog.getAuditStatus());
                    applyService.doEdit(apply);
                    if (StatusEnum.SA.getValue().equals(auditLog.getAuditStatus())) {   //审核通过才建立商户信息
                        Merchant merchant = new Merchant();
                        BeanUtils.copyProperties(apply,merchant);
                        String maccount = SendMessageUtil.getRandomCode(4)+"66";
                        String mpwd = SendMessageUtil.getRandomCode(6);
                        merchant.setAuditId(apply.getId());
                        merchant.setMaccount(maccount);
                        merchant.setMpwd(mpwd);
                        merchant.setMlevel(LevelEnum.NORMAL.getValue());
                        merchant.setCreateTime(new Date());
                        merchantService.doAdd(merchant);
                    }
                    break;
                case "BULLETIN":
                    Bulletin bulletin = Bulletin.builder().id(auditLog.getParentId()).auditStatus(auditLog.getAuditStatus()).build();
                    bulletinService.doEdit(bulletin);
                case "NEWS":
                    News news = News.builder().id(auditLog.getParentId()).auditStatus(auditLog.getAuditStatus()).build();
                    newsService.doEdit(news);
                case "ROOM":
                    RoomInfo roomInfo = RoomInfo.builder().id(auditLog.getParentId()).auditStatus(auditLog.getAuditStatus()).build();
                    roomInfoService.doEdit(roomInfo);
            }
            if (res > 0) {
                return Result.success();
            }else {
                return Result.error("审核失败");
            }
        } catch (BeansException e) {
            e.printStackTrace();
            log.error("审核操作异常");
            return Result.error("审核操作异常");
        }
    }

    @ApiOperation("弹出审核历史模态框")
    @GetMapping("/toAuditHis")
    public String toAuditHis(String parentId, String type, Model model) {
        List<AuditLog> auditLogs = auditLogService.queryList(Long.valueOf(parentId),type);
        model.addAttribute("auditLogs",auditLogs);
        return "admin/auditHistory";
    }

    @ApiOperation("跳转商户管理界面")
    @RequestMapping("/merchantManage")
    public String toMerchantManage() {
        return "admin/merchantManage";
    }

    @ApiOperation("获取商户审核列表")
    @ResponseBody
    @GetMapping("/merchantPage")
    public Object merchantPage(@RequestParam(value = "current", defaultValue = "1") Integer pn,
                              @RequestParam(value = "size", defaultValue = "10") Integer size,
                              @RequestParam(value = "sort", defaultValue = "id") String sort,
                              @RequestParam(value = "order", defaultValue = "desc") String order,
                              String title) {
        try {
            PageHelper.startPage(pn, size, sort + " " + order);     //pn:页码  10：页大小
            List<Merchant> merchantList = merchantService.queryList(title);
            merchantList.stream().forEach(ml->{
                Apply apply = applyService.queryDetail(ml.getAuditId());
                ml.setApply(apply);
            });
            PageInfo pageInfo = new PageInfo(merchantList, 10);
            return Result.layuiTable(pageInfo.getTotal(), pageInfo.getList());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询结果异常");
            return Result.error("查询结果异常");
        }
    }

    @ApiOperation("禁用/启用商户")
    @ResponseBody
    @PostMapping("/opeMerchant")
    public Object opeMerchant(UserEvt evt) {
        try {
            int res = merchantService.doEdit(evt);
            return res>0?Result.success(): Result.error("操作失败");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("操作商户异常");
            return Result.error("操作商户异常");
        }

    }
}
