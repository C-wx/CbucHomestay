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
        if (message.getIfMerchant()) {
            criteria.andSendTypeNotEqualTo("USER");
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

    public Message queryLast(Long mid,String type) {
        return messageMapper.queryLast(mid,type);
    }

    public List<Message> getList(Message message) {
        return messageMapper.getList(message);
    }

    public List<Message> queryChatList(Long id, Long mId) {
        MessageExample example = new MessageExample();
        MessageExample.Criteria criteria = example.createCriteria();
        MessageExample.Criteria criteria1 = example.createCriteria();
        criteria.andSendIdEqualTo(id);
        criteria.andSendTypeEqualTo("USER");
        criteria.andReceiveIdEqualTo(mId);
        criteria.andReceiveTypeEqualTo("MERCHANT");
        criteria1.andSendIdEqualTo(mId);
        criteria1.andSendTypeEqualTo("MERCHANT");
        criteria1.andReceiveIdEqualTo(id);
        criteria1.andReceiveTypeEqualTo("USER");
        example.or(criteria1);
        return messageMapper.selectByExample(example);
    }

    public List<Message> getKefuList(Long id) {
        return messageMapper.getKefuList(id);
    }
}
