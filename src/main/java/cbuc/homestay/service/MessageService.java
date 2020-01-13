package cbuc.homestay.service;

import cbuc.homestay.CommonEnum.StatusEnum;
import cbuc.homestay.bean.Message;
import cbuc.homestay.bean.MessageExample;
import cbuc.homestay.mapper.MessageMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Explain:   消息处理器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/12
 */
@Service
public class MessageService {

    @Autowired
    private MessageMapper messageMapper;


    public int doAdd(Message message) {
        return messageMapper.insertSelective(message);
    }

    public List<Message> queryList(Message message) {
        MessageExample messageExample = new MessageExample();
        MessageExample.Criteria criteria = messageExample.createCriteria();
        if (message.getReceiveId() != null) {
            criteria.andReceiveIdEqualTo(message.getReceiveId());
        }
        if (message.getSendId() != null) {
            criteria.andSendIdEqualTo(message.getSendId());
        }
        if (StringUtils.isNotBlank(message.getSendType())) {
            criteria.andSendTypeEqualTo(message.getSendType());
        }
        if (StringUtils.isNotBlank(message.getReceiveType())) {
            criteria.andReceiveTypeEqualTo(message.getReceiveType());
        }
        if (StringUtils.isNotBlank(message.getReadStatus())) {
            criteria.andReadStatusEqualTo(message.getReadStatus());
        }
        if (StringUtils.isNotBlank(message.getContent())) {
            criteria.andContentLike("%"+message.getContent()+"%");
        }
        criteria.andStatusNotEqualTo(StatusEnum.D.getValue());
        messageExample.setOrderByClause("id desc");
        return messageMapper.selectByExample(messageExample);
    }

    public int doEdit(Message message) {
        return messageMapper.updateByPrimaryKeySelective(message);
    }

    public Message queryDetail(Long id) {
        return messageMapper.selectByPrimaryKey(id);
    }
}
