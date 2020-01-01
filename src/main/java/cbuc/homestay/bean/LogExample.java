package cbuc.homestay.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LogExample() {
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

        public Criteria andAccessUnameIsNull() {
            addCriterion("ACCESS_UNAME is null");
            return (Criteria) this;
        }

        public Criteria andAccessUnameIsNotNull() {
            addCriterion("ACCESS_UNAME is not null");
            return (Criteria) this;
        }

        public Criteria andAccessUnameEqualTo(String value) {
            addCriterion("ACCESS_UNAME =", value, "accessUname");
            return (Criteria) this;
        }

        public Criteria andAccessUnameNotEqualTo(String value) {
            addCriterion("ACCESS_UNAME <>", value, "accessUname");
            return (Criteria) this;
        }

        public Criteria andAccessUnameGreaterThan(String value) {
            addCriterion("ACCESS_UNAME >", value, "accessUname");
            return (Criteria) this;
        }

        public Criteria andAccessUnameGreaterThanOrEqualTo(String value) {
            addCriterion("ACCESS_UNAME >=", value, "accessUname");
            return (Criteria) this;
        }

        public Criteria andAccessUnameLessThan(String value) {
            addCriterion("ACCESS_UNAME <", value, "accessUname");
            return (Criteria) this;
        }

        public Criteria andAccessUnameLessThanOrEqualTo(String value) {
            addCriterion("ACCESS_UNAME <=", value, "accessUname");
            return (Criteria) this;
        }

        public Criteria andAccessUnameLike(String value) {
            addCriterion("ACCESS_UNAME like", value, "accessUname");
            return (Criteria) this;
        }

        public Criteria andAccessUnameNotLike(String value) {
            addCriterion("ACCESS_UNAME not like", value, "accessUname");
            return (Criteria) this;
        }

        public Criteria andAccessUnameIn(List<String> values) {
            addCriterion("ACCESS_UNAME in", values, "accessUname");
            return (Criteria) this;
        }

        public Criteria andAccessUnameNotIn(List<String> values) {
            addCriterion("ACCESS_UNAME not in", values, "accessUname");
            return (Criteria) this;
        }

        public Criteria andAccessUnameBetween(String value1, String value2) {
            addCriterion("ACCESS_UNAME between", value1, value2, "accessUname");
            return (Criteria) this;
        }

        public Criteria andAccessUnameNotBetween(String value1, String value2) {
            addCriterion("ACCESS_UNAME not between", value1, value2, "accessUname");
            return (Criteria) this;
        }

        public Criteria andAccessIpIsNull() {
            addCriterion("ACCESS_IP is null");
            return (Criteria) this;
        }

        public Criteria andAccessIpIsNotNull() {
            addCriterion("ACCESS_IP is not null");
            return (Criteria) this;
        }

        public Criteria andAccessIpEqualTo(String value) {
            addCriterion("ACCESS_IP =", value, "accessIp");
            return (Criteria) this;
        }

        public Criteria andAccessIpNotEqualTo(String value) {
            addCriterion("ACCESS_IP <>", value, "accessIp");
            return (Criteria) this;
        }

        public Criteria andAccessIpGreaterThan(String value) {
            addCriterion("ACCESS_IP >", value, "accessIp");
            return (Criteria) this;
        }

        public Criteria andAccessIpGreaterThanOrEqualTo(String value) {
            addCriterion("ACCESS_IP >=", value, "accessIp");
            return (Criteria) this;
        }

        public Criteria andAccessIpLessThan(String value) {
            addCriterion("ACCESS_IP <", value, "accessIp");
            return (Criteria) this;
        }

        public Criteria andAccessIpLessThanOrEqualTo(String value) {
            addCriterion("ACCESS_IP <=", value, "accessIp");
            return (Criteria) this;
        }

        public Criteria andAccessIpLike(String value) {
            addCriterion("ACCESS_IP like", value, "accessIp");
            return (Criteria) this;
        }

        public Criteria andAccessIpNotLike(String value) {
            addCriterion("ACCESS_IP not like", value, "accessIp");
            return (Criteria) this;
        }

        public Criteria andAccessIpIn(List<String> values) {
            addCriterion("ACCESS_IP in", values, "accessIp");
            return (Criteria) this;
        }

        public Criteria andAccessIpNotIn(List<String> values) {
            addCriterion("ACCESS_IP not in", values, "accessIp");
            return (Criteria) this;
        }

        public Criteria andAccessIpBetween(String value1, String value2) {
            addCriterion("ACCESS_IP between", value1, value2, "accessIp");
            return (Criteria) this;
        }

        public Criteria andAccessIpNotBetween(String value1, String value2) {
            addCriterion("ACCESS_IP not between", value1, value2, "accessIp");
            return (Criteria) this;
        }

        public Criteria andAccessCodeIsNull() {
            addCriterion("ACCESS_CODE is null");
            return (Criteria) this;
        }

        public Criteria andAccessCodeIsNotNull() {
            addCriterion("ACCESS_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andAccessCodeEqualTo(String value) {
            addCriterion("ACCESS_CODE =", value, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCodeNotEqualTo(String value) {
            addCriterion("ACCESS_CODE <>", value, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCodeGreaterThan(String value) {
            addCriterion("ACCESS_CODE >", value, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ACCESS_CODE >=", value, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCodeLessThan(String value) {
            addCriterion("ACCESS_CODE <", value, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCodeLessThanOrEqualTo(String value) {
            addCriterion("ACCESS_CODE <=", value, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCodeLike(String value) {
            addCriterion("ACCESS_CODE like", value, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCodeNotLike(String value) {
            addCriterion("ACCESS_CODE not like", value, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCodeIn(List<String> values) {
            addCriterion("ACCESS_CODE in", values, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCodeNotIn(List<String> values) {
            addCriterion("ACCESS_CODE not in", values, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCodeBetween(String value1, String value2) {
            addCriterion("ACCESS_CODE between", value1, value2, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCodeNotBetween(String value1, String value2) {
            addCriterion("ACCESS_CODE not between", value1, value2, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCnameIsNull() {
            addCriterion("ACCESS_CNAME is null");
            return (Criteria) this;
        }

        public Criteria andAccessCnameIsNotNull() {
            addCriterion("ACCESS_CNAME is not null");
            return (Criteria) this;
        }

        public Criteria andAccessCnameEqualTo(String value) {
            addCriterion("ACCESS_CNAME =", value, "accessCname");
            return (Criteria) this;
        }

        public Criteria andAccessCnameNotEqualTo(String value) {
            addCriterion("ACCESS_CNAME <>", value, "accessCname");
            return (Criteria) this;
        }

        public Criteria andAccessCnameGreaterThan(String value) {
            addCriterion("ACCESS_CNAME >", value, "accessCname");
            return (Criteria) this;
        }

        public Criteria andAccessCnameGreaterThanOrEqualTo(String value) {
            addCriterion("ACCESS_CNAME >=", value, "accessCname");
            return (Criteria) this;
        }

        public Criteria andAccessCnameLessThan(String value) {
            addCriterion("ACCESS_CNAME <", value, "accessCname");
            return (Criteria) this;
        }

        public Criteria andAccessCnameLessThanOrEqualTo(String value) {
            addCriterion("ACCESS_CNAME <=", value, "accessCname");
            return (Criteria) this;
        }

        public Criteria andAccessCnameLike(String value) {
            addCriterion("ACCESS_CNAME like", value, "accessCname");
            return (Criteria) this;
        }

        public Criteria andAccessCnameNotLike(String value) {
            addCriterion("ACCESS_CNAME not like", value, "accessCname");
            return (Criteria) this;
        }

        public Criteria andAccessCnameIn(List<String> values) {
            addCriterion("ACCESS_CNAME in", values, "accessCname");
            return (Criteria) this;
        }

        public Criteria andAccessCnameNotIn(List<String> values) {
            addCriterion("ACCESS_CNAME not in", values, "accessCname");
            return (Criteria) this;
        }

        public Criteria andAccessCnameBetween(String value1, String value2) {
            addCriterion("ACCESS_CNAME between", value1, value2, "accessCname");
            return (Criteria) this;
        }

        public Criteria andAccessCnameNotBetween(String value1, String value2) {
            addCriterion("ACCESS_CNAME not between", value1, value2, "accessCname");
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