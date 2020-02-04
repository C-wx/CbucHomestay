package cbuc.homestay.service;

import cbuc.homestay.bean.RoomInfo;
import cbuc.homestay.bean.RoomInfoExample;
import cbuc.homestay.mapper.RoomInfoMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Explain: 房间信息处理器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/13
 */
@Service
public class RoomInfoService {

    @Autowired
    private RoomInfoMapper roomInfoMapper;

    public RoomInfo queryDetail(Long rid) {
        return roomInfoMapper.selectByPrimaryKey(rid);
    }

    public List<RoomInfo> queryList(RoomInfo roomInfo) {
        RoomInfoExample roomInfoExample = new RoomInfoExample();
        RoomInfoExample.Criteria criteria = roomInfoExample.createCriteria();
        if (StringUtils.isNotBlank(roomInfo.getTitle())) {
            criteria.andTitleLike("%" + roomInfo.getTitle() + "%");
        }
        if (StringUtils.isNotBlank(roomInfo.getAuditStatus())) {
            criteria.andAuditStatusEqualTo(roomInfo.getAuditStatus());
        }
        if (StringUtils.isNotBlank(roomInfo.getType())) {
            criteria.andTypeEqualTo(roomInfo.getType());
        }
        roomInfoExample.setOrderByClause("ID DESC");
        return roomInfoMapper.selectByExample(roomInfoExample);
    }

    public int doEdit(RoomInfo roomInfo) {
        return roomInfoMapper.updateByPrimaryKeySelective(roomInfo);
    }

    public List<RoomInfo> queryActiveRoom() {
        RoomInfoExample roomInfoExample = new RoomInfoExample();
        roomInfoExample.createCriteria().andIsActiveEqualTo("Y");
        return roomInfoMapper.selectByExample(roomInfoExample);
    }

    public List<RoomInfo> queryTopRoom() {
        return roomInfoMapper.queryTopRoom();
    }
}
