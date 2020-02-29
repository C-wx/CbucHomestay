package cbuc.homestay.service;

import cbuc.homestay.bean.*;
import cbuc.homestay.evt.RoomInfoEvt;
import cbuc.homestay.mapper.ImageMapper;
import cbuc.homestay.mapper.PropertyMapper;
import cbuc.homestay.mapper.RoomInfoMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private ImageMapper imageMapper;

    @Autowired
    private PropertyMapper propertyMapper;

    public RoomInfo queryDetail(Long rid) {
        RoomInfo roomInfo = roomInfoMapper.selectByPrimaryKey(rid);
        PropertyExample example = new PropertyExample();
        example.createCriteria().andRidEqualTo(rid).andStatusEqualTo("E");
        List<Property> propertyList = propertyMapper.selectByExample(example);
        Map<String, Object> info = new HashMap();
        if (!CollectionUtils.isEmpty(propertyList)) {
            propertyList.stream().forEach(property -> {
                if (property.getProKey().contains("卧室")) {
                    info.put("bedRoomCount", property.getProValue());
                } else if (property.getProKey().contains("床")) {
                    info.put("bedCount", property.getProValue());
                } else if (property.getProKey().contains("卫生间")) {
                    info.put("bathRoomCount", property.getProValue());
                } else if (property.getProKey().contains("人")) {
                    info.put("peopleCount", property.getProValue());
                }
            });
            roomInfo.setPropertyInfo(info);
        }
        return roomInfo;
    }

    public List<RoomInfo> queryList(RoomInfo roomInfo) {
        RoomInfoExample roomInfoExample = new RoomInfoExample();
        RoomInfoExample.Criteria criteria = roomInfoExample.createCriteria();
        if (roomInfo.getBeginTime() != null && roomInfo.getEndTime() != null) {
            RoomInfoExample.Criteria criteria1 = roomInfoExample.createCriteria();
            criteria1.andBeginTimeGreaterThan(roomInfo.getEndTime());
            this.getCriteria(criteria1, roomInfo);
            RoomInfoExample.Criteria criteria2 = roomInfoExample.createCriteria();
            criteria2.andBeginTimeIsNull();
            criteria2.andEndTimeIsNull();
            this.getCriteria(criteria2, roomInfo);
            RoomInfoExample.Criteria criteria3 = roomInfoExample.createCriteria();
            criteria3.andEndTimeLessThan(roomInfo.getBeginTime());
            this.getCriteria(criteria3, roomInfo);
            roomInfoExample.or(criteria1);
            roomInfoExample.or(criteria2);
            roomInfoExample.or(criteria3);
        } else {
            this.getCriteria(criteria, roomInfo);
        }
        roomInfoExample.setOrderByClause("ID DESC");
        return roomInfoMapper.selectByExample(roomInfoExample);
    }

    public int doAdd(RoomInfo roomInfo) {
        return roomInfoMapper.insertSelective(roomInfo);
    }

    public int doEdit(RoomInfo roomInfo) {
        return roomInfoMapper.updateByPrimaryKeySelective(roomInfo);
    }

    public List<RoomInfo> queryActiveRoom() {
        RoomInfoExample roomInfoExample = new RoomInfoExample();
        roomInfoExample.createCriteria().andIsActiveEqualTo("Y").andAuditStatusEqualTo("SA").andStatusNotEqualTo("D");
        return roomInfoMapper.selectByExample(roomInfoExample);
    }

    public List<RoomInfo> queryTopRoom() {
        return roomInfoMapper.queryTopRoom();
    }

    public RoomInfoExample.Criteria getCriteria(RoomInfoExample.Criteria criteria, RoomInfo roomInfo) {
        if (StringUtils.isNotBlank(roomInfo.getTitle())) {
            criteria.andTitleLike("%" + roomInfo.getTitle() + "%");
        }
        if (StringUtils.isNotBlank(roomInfo.getAuditStatus())) {
            criteria.andAuditStatusEqualTo(roomInfo.getAuditStatus());
        }
        if (StringUtils.isNotBlank(roomInfo.getType())) {
            criteria.andTypeEqualTo(roomInfo.getType());
        }
        if (roomInfo.getMid() != null) {
            criteria.andMidEqualTo(roomInfo.getMid());
        }
        if (StringUtils.isNotBlank(roomInfo.getStatus())) {
            if ("D".equals(roomInfo.getStatus())) {
                criteria.andStatusEqualTo(roomInfo.getStatus());
            } else {
                criteria.andStatusNotEqualTo("D");
            }
        }
        return criteria;
    }

    public Long doSaveRoomInfo(RoomInfoEvt roomInfoEvt, Long mid, Long id) {
        RoomInfo roomInfo = new RoomInfo();
        BeanUtils.copyProperties(roomInfoEvt, roomInfo);
        if ("on".equals(roomInfoEvt.getIsActive())) {
            roomInfo.setIsActive("N");
        } else {
            roomInfo.setIsActive("Y");
        }
        roomInfo.setMid(mid);
        if (id != null) {
            roomInfo.setId(id);
            PropertyExample propertyExample = new PropertyExample();
            propertyExample.createCriteria().andRidEqualTo(id);
            propertyMapper.updateByExampleSelective(Property.builder().status("D").build(), propertyExample);
            this.doEdit(roomInfo);
        } else {
            this.doAdd(roomInfo);
        }
        this.doSaveProperty(roomInfoEvt, roomInfo.getId());
        return roomInfo.getId();
    }

    public void doSaveProperty(RoomInfoEvt roomInfoEvt, Long rid) {
        if (StringUtils.isNotBlank(roomInfoEvt.getBathRoomCount())) {
            Property pi = Property.builder().rid(rid).proKey("间卧室").proValue(roomInfoEvt.getBathRoomCount()).build();
            propertyMapper.insertSelective(pi);
        }
        if (StringUtils.isNotBlank(roomInfoEvt.getBedCount())) {
            Property pi = Property.builder().rid(rid).proKey("张床").proValue(roomInfoEvt.getBedCount()).build();
            propertyMapper.insertSelective(pi);
        }
        if (StringUtils.isNotBlank(roomInfoEvt.getBathRoomCount())) {
            Property pi = Property.builder().rid(rid).proKey("间卫生间").proValue(roomInfoEvt.getBathRoomCount()).build();
            propertyMapper.insertSelective(pi);
        }
        if (StringUtils.isNotBlank(roomInfoEvt.getPeopleCount())) {
            Property pi = Property.builder().rid(rid).proKey("人可住").proValue(roomInfoEvt.getPeopleCount()).build();
            propertyMapper.insertSelective(pi);
        }
    }

    public List<Favorite> queryHotRoom() {
        List<RoomInfo> hotRoom = roomInfoMapper.getHotRoom();
        List<Favorite> list = new ArrayList();
        hotRoom.stream().forEach(roomInfo -> {
            Favorite favorite = Favorite.builder().roomInfo(roomInfo).build();
            list.add(favorite);
        });
        return list;
    }

    public int doDel(RoomInfo roomInfo) {
        this.doDelImage(roomInfo);
        this.doDelProperty(roomInfo);
        return roomInfoMapper.deleteByPrimaryKey(roomInfo.getId());
    }

    private void doDelImage(RoomInfo roomInfo) {
        ImageExample imageExample = new ImageExample();
        imageExample.createCriteria().andParentIdEqualTo(roomInfo.getId()).andOriginEqualTo("ROOM");
        imageMapper.deleteByExample(imageExample);
    }

    private void doDelProperty(RoomInfo roomInfo) {
        PropertyExample propertyExample = new PropertyExample();
        propertyExample.createCriteria().andRidEqualTo(roomInfo.getId());
        propertyMapper.deleteByExample(propertyExample);
    }
}
