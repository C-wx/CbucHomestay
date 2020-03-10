package cbuc.homestay.controller.NewsCenter;

import cbuc.homestay.base.Result;
import cbuc.homestay.bean.Merchant;
import cbuc.homestay.bean.News;
import cbuc.homestay.evt.UserEvt;
import cbuc.homestay.service.MerchantService;
import cbuc.homestay.service.NewsService;
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
 * @Explain: 管理员之资讯控制器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/13
 */
@Slf4j
@Controller
@RequestMapping("/admin")
@Api(value = "管理员之资讯控制器", description = "管理资讯相关内容")
public class AdminNewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private MerchantService merchantService;

    @ApiOperation("跳转资讯管理界面")
    @GetMapping("/newsManage")
    public String newsManage() {
        return "admin/newsManage";
    }

    @ApiOperation("获取资讯列表")
    @ResponseBody
    @GetMapping("/newsManagePage")
    public Object newsManagePage(@RequestParam(value = "current", defaultValue = "1") Integer pn,
                                 @RequestParam(value = "size", defaultValue = "10") Integer size,
                                 @RequestParam(value = "sort", defaultValue = "id") String sort,
                                 @RequestParam(value = "order", defaultValue = "desc") String order,
                                 String content, HttpSession session) {
        try {
            Merchant login_merchant = (Merchant) session.getAttribute("LOGIN_MERCHANT");
            PageHelper.startPage(pn, size, sort + " " + order);     //pn:页码  10：页大小
            News news = new News();
            if (StringUtils.isNotBlank(content)) {
                news.setContent(content);
            }
            List<News> newsList = newsService.queryList(news);
            newsList.stream().forEach(nl -> {
                if (nl.getPublishId() == login_merchant.getId()) {
                    nl.setSelf(true);
                } else {
                    nl.setSelf(false);
                }
                Merchant merchant = merchantService.queryDetail(nl.getPublishId());
                nl.setPublishName(merchant.getMname());
            });
            PageInfo pageInfo = new PageInfo(newsList, 10);
            return Result.layuiTable(pageInfo.getTotal(), pageInfo.getList());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询结果异常");
            return Result.error("查询结果异常");
        }
    }

    @ApiOperation("跳转资讯发布界面")
    @GetMapping("/newPublish")
    public String toNewPublish(Long id, Model model) {
        if (id != null) {
            News news = newsService.queryDetail(id);
            model.addAttribute("news", news);
        } else {
            model.addAttribute("news", new News());
        }
        return "admin/newPublish";
    }

    @ApiOperation("发布资讯")
    @ResponseBody
    @PutMapping("/pubNews")
    public Object doPubNews(News news, HttpSession session) {
        try {
            int res;
            if (news.getId() != null) {
                res = newsService.doEdit(news);
            } else {
                Merchant merchant = (Merchant) session.getAttribute("LOGIN_MERCHANT");
                news.setPublishId(merchant.getId());
                res = newsService.doAdd(news);
            }
            return res > 0 ? Result.success() : Result.error("发布资讯失败");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("发布资讯异常");
            return Result.error("发布资讯异常！");
        }
    }

    @ApiOperation("禁用/启用资讯")
    @ResponseBody
    @PostMapping("/opeNews")
    public Object opeNews(UserEvt evt) {
        try {
            News news = new News();
            BeanUtils.copyProperties(evt, news);
            int res = newsService.doEdit(news);
            return res > 0 ? Result.success() : Result.error("操作失败");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("操作公告异常");
            return Result.error("操作公告异常");
        }
    }
}
