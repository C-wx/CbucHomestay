package cbuc.homestay.controller.MerchantCenter;

import cbuc.homestay.base.Result;
import cbuc.homestay.bean.Apply;
import cbuc.homestay.bean.AuditLog;
import cbuc.homestay.bean.Merchant;
import cbuc.homestay.bean.RoomInfo;
import cbuc.homestay.evt.UserEvt;
import cbuc.homestay.service.ApplyService;
import cbuc.homestay.service.AuditLogService;
import cbuc.homestay.service.MerchantService;
import cbuc.homestay.service.RoomInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
    private AuditLogService auditLogService;

    @Autowired
    private MerchantService merchantService;

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
            auditLogService.doAudit(auditLog);      //审核操作
            if (res > 0) {
                return Result.success();
            } else {
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
        List<AuditLog> auditLogs = auditLogService.queryList(Long.valueOf(parentId), type);
        model.addAttribute("auditLogs", auditLogs);
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
            merchantList.stream().forEach(ml -> {
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
            return res > 0 ? Result.success() : Result.error("操作失败");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("操作商户异常");
            return Result.error("操作商户异常");
        }
    }

    @ApiOperation("/编辑商户#跳转")
    @GetMapping("/editMerchant")
    public String editMerchant(Long id, Model model) {
        AtomicInteger salesCount = new AtomicInteger(0);
        Merchant merchant = merchantService.queryDetail(id);
        Apply apply = applyService.queryDetail(merchant.getAuditId());
        List<RoomInfo> roomInfoList = roomInfoService.queryList(RoomInfo.builder().mid(merchant.getId()).build());
        roomInfoList.stream().forEach(roomInfo -> {
            salesCount.addAndGet(roomInfo.getSales());
        });
        merchant.setSalesCount(salesCount);
        merchant.setApply(apply);
        model.addAttribute("merchant", merchant);
        return "admin/merchantEdit";
    }

    @ApiOperation("/编辑商户#修改")
    @ResponseBody
    @PostMapping("/editMerchant")
    public Object editMerchant(Merchant merchant) {
        try {
            int res = merchantService.doEdit(merchant);
            applyService.doEdit(merchant.getApply());
            return res > 0 ? Result.success() : Result.error("操作失败");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("操作商户异常");
            return Result.error("操作商户异常");
        }
    }
}
