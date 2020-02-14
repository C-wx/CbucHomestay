package cbuc.homestay.controller.foreCenter;

import cbuc.homestay.base.Result;
import cbuc.homestay.bean.News;
import cbuc.homestay.service.NewsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Explain: 小程序端资讯控制器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/2/12
 */
@Controller
@Api(value = "小程序端资讯控制器", description = "资讯相关业务")
public class ForeNewsController {

    @Autowired
    private NewsService newsService;

    @ResponseBody
    @RequestMapping("/getNewsList")
    public Object getNewsList() {
        News news = News.builder().valid(true).auditStatus("SA").status("E").build();
        List<News> newsList = newsService.queryList(news);
        return Result.success(newsList);
    }

    @ResponseBody
    @RequestMapping("/getNewsDetail")
    public Object getNewsDetail(Long id) {
        News news = newsService.queryDetail(id);
        return Result.success(news);
    }

}
