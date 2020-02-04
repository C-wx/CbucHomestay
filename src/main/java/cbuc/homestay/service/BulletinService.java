package cbuc.homestay.service;

import cbuc.homestay.bean.Bulletin;
import cbuc.homestay.bean.BulletinExample;
import cbuc.homestay.mapper.BulletinMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Explain:   公告处理器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/13
 */
@Service
public class BulletinService {

    @Autowired
    private BulletinMapper bulletinMapper;

    public List<Bulletin> queryList(Bulletin bulletin) {
        BulletinExample bulletinExample = new BulletinExample();
        BulletinExample.Criteria criteria = bulletinExample.createCriteria();;
        if (StringUtils.isNotBlank(bulletin.getContent())) {
            criteria.andContentLike("%" + bulletin.getContent() + "%");
        }
        if (bulletin.getPublishId() != null) {
            criteria.andPublishIdEqualTo(bulletin.getPublishId());
        }
        bulletinExample.setOrderByClause("ID DESC");
        return bulletinMapper.selectByExample(bulletinExample);
    }

    public int doEdit(Bulletin bulletin) {
        return bulletinMapper.updateByPrimaryKeySelective(bulletin);
    }

    public int doAdd(Bulletin bulletin) {
        return bulletinMapper.insertSelective(bulletin);
    }

    public Bulletin queryDetail(Long id) {
        return bulletinMapper.selectByPrimaryKey(id);
    }

    public Bulletin getLastBulletin() {
        return bulletinMapper.getLastBulletin();
    }
}
