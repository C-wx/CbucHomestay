package cbuc.homestay.service;

import cbuc.homestay.bean.News;
import cbuc.homestay.bean.NewsExample;
import cbuc.homestay.mapper.NewsMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Explain: 资讯处理器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/13
 */
@Service
public class NewsService {

    @Autowired
    private NewsMapper newsMapper;

    public List<News> queryList(News news) {
        NewsExample newsExample = new NewsExample();
        NewsExample.Criteria criteria = newsExample.createCriteria();
        if (news.isValid()) {       //是否在有效期间
            criteria.andBeginTimeLessThanOrEqualTo(new Date());
            criteria.andEndTimeGreaterThanOrEqualTo(new Date());
        }
        if (StringUtils.isNotBlank(news.getTitle())) {
            criteria.andTitleLike("%" + news.getTitle() + "%");
        }
        if (news.getPublishId() != null) {
            criteria.andPublishIdEqualTo(news.getPublishId());
        }
        if (StringUtils.isNotBlank(news.getStatus())) {
            criteria.andStatusEqualTo(news.getStatus());
        }
        if (StringUtils.isNotBlank(news.getAuditStatus())) {
            criteria.andAuditStatusEqualTo(news.getAuditStatus());
        }
        criteria.andStatusNotEqualTo("D");
        newsExample.setOrderByClause("id desc");
        return newsMapper.selectByExampleWithBLOBs(newsExample);
    }

    public int doAdd(News news) {
        return newsMapper.insertSelective(news);
    }

    public int doEdit(News news) {
        return newsMapper.updateByPrimaryKeySelective(news);
    }

    public News queryDetail(Long id) {
        return newsMapper.selectByPrimaryKey(id);
    }
}
