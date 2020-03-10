package cbuc.homestay.controller.NewsCenter;

import cbuc.homestay.base.Result;
import cbuc.homestay.bean.Merchant;
import cbuc.homestay.bean.News;
import cbuc.homestay.service.NewsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Explain: 商户端资讯控制器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/2/11
 */
@Slf4j
@Controller
@RequestMapping("merchant")
@Api(value = "商户端资讯控制器", description = "商户端资讯相关业务")
public class MerchantNewsController {

    @Autowired
    private NewsService newsService;

    @ApiOperation("跳转资讯管理界面")
    @GetMapping("/toNews")
    public String toNews() {
        return "merchant/news";
    }

    @ApiOperation("获取资讯列表")
    @ResponseBody
    @GetMapping("/newsPage")
    public Object newsPage(@RequestParam(value = "current", defaultValue = "1") Integer pn,
                           @RequestParam(value = "size", defaultValue = "10") Integer size,
                           @RequestParam(value = "sort", defaultValue = "id") String sort,
                           @RequestParam(value = "order", defaultValue = "desc") String order,
                           String title, HttpSession session) {
        try {
            Merchant merchant = (Merchant) session.getAttribute("LOGIN_MERCHANT");
            PageHelper.startPage(pn, size, sort + " " + order);     //pn:页码  10：页大小
            News news = new News();
            if (StringUtils.isNotBlank(title)) {
                news.setTitle(title);
            }
            news.setStatus("E");
            news.setPublishId(merchant.getId());
            List<News> newsList = newsService.queryList(news);
            newsList.stream().forEach(news1 -> {
                if (news1.getPublishId() == merchant.getId()) {
                    news1.setSelf(true);
                } else {
                    news1.setSelf(false);
                }
            });
            PageInfo pageInfo = new PageInfo(newsList, 10);
            return Result.layuiTable(pageInfo.getTotal(), pageInfo.getList());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询结果异常");
            return Result.error("查询结果异常");
        }
    }

}
