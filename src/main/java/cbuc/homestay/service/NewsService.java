package cbuc.homestay.service;

import cbuc.homestay.bean.News;
import cbuc.homestay.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Explain:   资讯处理器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/13
 */
@Service
public class NewsService {

    @Autowired
    private NewsMapper newsMapper;

    public List<News> queryList(News news) {
        return newsMapper.selectByExampleWithBLOBs(null);
    }

    public int doAdd(News news) {
        return newsMapper.insertSelective(news);
    }

    public int doEdit(News news) {
        return newsMapper.updateByPrimaryKeySelective(news);
    }
}
