package cbuc.homestay.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MessageExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSendIdIsNull() {
            addCriterion("SEND_ID is null");
            return (Criteria) this;
        }

        public Criteria andSendIdIsNotNull() {
            addCriterion("SEND_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSendIdEqualTo(Long value) {
            addCriterion("SEND_ID =", value, "sendId");
            return (Criteria) this;
        }

        public Criteria andSendIdNotEqualTo(Long value) {
            addCriterion("SEND_ID <>", value, "sendId");
            return (Criteria) this;
        }

        public Criteria andSendIdGreaterThan(Long value) {
            addCriterion("SEND_ID >", value, "sendId");
            return (Criteria) this;
        }

        public Criteria andSendIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SEND_ID >=", value, "sendId");
            return (Criteria) this;
        }

        public Criteria andSendIdLessThan(Long value) {
            addCriterion("SEND_ID <", value, "sendId");
            return (Criteria) this;
        }

        public Criteria andSendIdLessThanOrEqualTo(Long value) {
            addCriterion("SEND_ID <=", value, "sendId");
            return (Criteria) this;
        }

        public Criteria andSendIdIn(List<Long> values) {
            addCriterion("SEND_ID in", values, "sendId");
            return (Criteria) this;
        }

        public Criteria andSendIdNotIn(List<Long> values) {
            addCriterion("SEND_ID not in", values, "sendId");
            return (Criteria) this;
        }

        public Criteria andSendIdBetween(Long value1, Long value2) {
            addCriterion("SEND_ID between", value1, value2, "sendId");
            return (Criteria) this;
        }

        public Criteria andSendIdNotBetween(Long value1, Long value2) {
            addCriterion("SEND_ID not between", value1, value2, "sendId");
            return (Criteria) this;
        }

        public Criteria andSendTypeIsNull() {
            addCriterion("SEND_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSendTypeIsNotNull() {
            addCriterion("SEND_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSendTypeEqualTo(String value) {
            addCriterion("SEND_TYPE =", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeNotEqualTo(String value) {
            addCriterion("SEND_TYPE <>", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeGreaterThan(String value) {
            addCriterion("SEND_TYPE >", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_TYPE >=", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeLessThan(String value) {
            addCriterion("SEND_TYPE <", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeLessThanOrEqualTo(String value) {
            addCriterion("SEND_TYPE <=", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeLike(String value) {
            addCriterion("SEND_TYPE like", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeNotLike(String value) {
            addCriterion("SEND_TYPE not like", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeIn(List<String> values) {
            addCriterion("SEND_TYPE in", values, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeNotIn(List<String> values) {
            addCriterion("SEND_TYPE not in", values, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeBetween(String value1, String value2) {
            addCriterion("SEND_TYPE between", value1, value2, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeNotBetween(String value1, String value2) {
            addCriterion("SEND_TYPE not between", value1, value2, "sendType");
            return (Criteria) this;
        }

        public Criteria andReceiveIdIsNull() {
            addCriterion("RECEIVE_ID is null");
            return (Criteria) this;
        }

        public Criteria andReceiveIdIsNotNull() {
            addCriterion("RECEIVE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveIdEqualTo(Long value) {
            addCriterion("RECEIVE_ID =", value, "receiveId");
            return (Criteria) this;
        }

        public Criteria andReceiveIdNotEqualTo(Long value) {
            addCriterion("RECEIVE_ID <>", value, "receiveId");
            return (Criteria) this;
        }

        public Criteria andReceiveIdGreaterThan(Long value) {
            addCriterion("RECEIVE_ID >", value, "receiveId");
            return (Criteria) this;
        }

        public Criteria andReceiveIdGreaterThanOrEqualTo(Long value) {
            addCriterion("RECEIVE_ID >=", value, "receiveId");
            return (Criteria) this;
        }

        public Criteria andReceiveIdLessThan(Long value) {
            addCriterion("RECEIVE_ID <", value, "receiveId");
            return (Criteria) this;
        }

        public Criteria andReceiveIdLessThanOrEqualTo(Long value) {
            addCriterion("RECEIVE_ID <=", value, "receiveId");
            return (Criteria) this;
        }

        public Criteria andReceiveIdIn(List<Long> values) {
            addCriterion("RECEIVE_ID in", values, "receiveId");
            return (Criteria) this;
        }

        public Criteria andReceiveIdNotIn(List<Long> values) {
            addCriterion("RECEIVE_ID not in", values, "receiveId");
            return (Criteria) this;
        }

        public Criteria andReceiveIdBetween(Long value1, Long value2) {
            addCriterion("RECEIVE_ID between", value1, value2, "receiveId");
            return (Criteria) this;
        }

        public Criteria andReceiveIdNotBetween(Long value1, Long value2) {
            addCriterion("RECEIVE_ID not between", value1, value2, "receiveId");
            return (Criteria) this;
        }

        public Criteria andReceiveTypeIsNull() {
            addCriterion("RECEIVE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andReceiveTypeIsNotNull() {
            addCriterion("RECEIVE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveTypeEqualTo(String value) {
            addCriterion("RECEIVE_TYPE =", value, "receiveType");
            return (Criteria) this;
        }

        public Criteria andReceiveTypeNotEqualTo(String value) {
            addCriterion("RECEIVE_TYPE <>", value, "receiveType");
            return (Criteria) this;
        }

        public Criteria andReceiveTypeGreaterThan(String value) {
            addCriterion("RECEIVE_TYPE >", value, "receiveType");
            return (Criteria) this;
        }

        public Criteria andReceiveTypeGreaterThanOrEqualTo(String value) {
            addCriterion("RECEIVE_TYPE >=", value, "receiveType");
            return (Criteria) this;
        }

        public Criteria andReceiveTypeLessThan(String value) {
            addCriterion("RECEIVE_TYPE <", value, "receiveType");
            return (Criteria) this;
        }

        public Criteria andReceiveTypeLessThanOrEqualTo(String value) {
            addCriterion("RECEIVE_TYPE <=", value, "receiveType");
            return (Criteria) this;
        }

        public Criteria andReceiveTypeLike(String value) {
            addCriterion("RECEIVE_TYPE like", value, "receiveType");
            return (Criteria) this;
        }

        public Criteria andReceiveTypeNotLike(String value) {
            addCriterion("RECEIVE_TYPE not like", value, "receiveType");
            return (Criteria) this;
        }

        public Criteria andReceiveTypeIn(List<String> values) {
            addCriterion("RECEIVE_TYPE in", values, "receiveType");
            return (Criteria) this;
        }

        public Criteria andReceiveTypeNotIn(List<String> values) {
            addCriterion("RECEIVE_TYPE not in", values, "receiveType");
            return (Criteria) this;
        }

        public Criteria andReceiveTypeBetween(String value1, String value2) {
            addCriterion("RECEIVE_TYPE between", value1, value2, "receiveType");
            return (Criteria) this;
        }

        public Criteria andReceiveTypeNotBetween(String value1, String value2) {
            addCriterion("RECEIVE_TYPE not between", value1, value2, "receiveType");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("CONTENT =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("CONTENT <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("CONTENT >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("CONTENT >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("CONTENT <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("CONTENT <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("CONTENT like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("CONTENT not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("CONTENT in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("CONTENT not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("CONTENT between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("CONTENT not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andReadStatusIsNull() {
            addCriterion("READ_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andReadStatusIsNotNull() {
            addCriterion("READ_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andReadStatusEqualTo(String value) {
            addCriterion("READ_STATUS =", value, "readStatus");
            return (Criteria) this;
        }

        public Criteria andReadStatusNotEqualTo(String value) {
            addCriterion("READ_STATUS <>", value, "readStatus");
            return (Criteria) this;
        }

        public Criteria andReadStatusGreaterThan(String value) {
            addCriterion("READ_STATUS >", value, "readStatus");
            return (Criteria) this;
        }

        public Criteria andReadStatusGreaterThanOrEqualTo(String value) {
            addCriterion("READ_STATUS >=", value, "readStatus");
            return (Criteria) this;
        }

        public Criteria andReadStatusLessThan(String value) {
            addCriterion("READ_STATUS <", value, "readStatus");
            return (Criteria) this;
        }

        public Criteria andReadStatusLessThanOrEqualTo(String value) {
            addCriterion("READ_STATUS <=", value, "readStatus");
            return (Criteria) this;
        }

        public Criteria andReadStatusLike(String value) {
            addCriterion("READ_STATUS like", value, "readStatus");
            return (Criteria) this;
        }

        public Criteria andReadStatusNotLike(String value) {
            addCriterion("READ_STATUS not like", value, "readStatus");
            return (Criteria) this;
        }

        public Criteria andReadStatusIn(List<String> values) {
            addCriterion("READ_STATUS in", values, "readStatus");
            return (Criteria) this;
        }

        public Criteria andReadStatusNotIn(List<String> values) {
            addCriterion("READ_STATUS not in", values, "readStatus");
            return (Criteria) this;
        }

        public Criteria andReadStatusBetween(String value1, String value2) {
            addCriterion("READ_STATUS between", value1, value2, "readStatus");
            return (Criteria) this;
        }

        public Criteria andReadStatusNotBetween(String value1, String value2) {
            addCriterion("READ_STATUS not between", value1, value2, "readStatus");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}