package cbuc.homestay.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApplyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ApplyExample() {
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

        public Criteria andUidIsNull() {
            addCriterion("UID is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("UID is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Long value) {
            addCriterion("UID =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Long value) {
            addCriterion("UID <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Long value) {
            addCriterion("UID >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Long value) {
            addCriterion("UID >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Long value) {
            addCriterion("UID <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Long value) {
            addCriterion("UID <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Long> values) {
            addCriterion("UID in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Long> values) {
            addCriterion("UID not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Long value1, Long value2) {
            addCriterion("UID between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Long value1, Long value2) {
            addCriterion("UID not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andMnameIsNull() {
            addCriterion("MNAME is null");
            return (Criteria) this;
        }

        public Criteria andMnameIsNotNull() {
            addCriterion("MNAME is not null");
            return (Criteria) this;
        }

        public Criteria andMnameEqualTo(String value) {
            addCriterion("MNAME =", value, "mname");
            return (Criteria) this;
        }

        public Criteria andMnameNotEqualTo(String value) {
            addCriterion("MNAME <>", value, "mname");
            return (Criteria) this;
        }

        public Criteria andMnameGreaterThan(String value) {
            addCriterion("MNAME >", value, "mname");
            return (Criteria) this;
        }

        public Criteria andMnameGreaterThanOrEqualTo(String value) {
            addCriterion("MNAME >=", value, "mname");
            return (Criteria) this;
        }

        public Criteria andMnameLessThan(String value) {
            addCriterion("MNAME <", value, "mname");
            return (Criteria) this;
        }

        public Criteria andMnameLessThanOrEqualTo(String value) {
            addCriterion("MNAME <=", value, "mname");
            return (Criteria) this;
        }

        public Criteria andMnameLike(String value) {
            addCriterion("MNAME like", value, "mname");
            return (Criteria) this;
        }

        public Criteria andMnameNotLike(String value) {
            addCriterion("MNAME not like", value, "mname");
            return (Criteria) this;
        }

        public Criteria andMnameIn(List<String> values) {
            addCriterion("MNAME in", values, "mname");
            return (Criteria) this;
        }

        public Criteria andMnameNotIn(List<String> values) {
            addCriterion("MNAME not in", values, "mname");
            return (Criteria) this;
        }

        public Criteria andMnameBetween(String value1, String value2) {
            addCriterion("MNAME between", value1, value2, "mname");
            return (Criteria) this;
        }

        public Criteria andMnameNotBetween(String value1, String value2) {
            addCriterion("MNAME not between", value1, value2, "mname");
            return (Criteria) this;
        }

        public Criteria andMphoneIsNull() {
            addCriterion("MPHONE is null");
            return (Criteria) this;
        }

        public Criteria andMphoneIsNotNull() {
            addCriterion("MPHONE is not null");
            return (Criteria) this;
        }

        public Criteria andMphoneEqualTo(String value) {
            addCriterion("MPHONE =", value, "mphone");
            return (Criteria) this;
        }

        public Criteria andMphoneNotEqualTo(String value) {
            addCriterion("MPHONE <>", value, "mphone");
            return (Criteria) this;
        }

        public Criteria andMphoneGreaterThan(String value) {
            addCriterion("MPHONE >", value, "mphone");
            return (Criteria) this;
        }

        public Criteria andMphoneGreaterThanOrEqualTo(String value) {
            addCriterion("MPHONE >=", value, "mphone");
            return (Criteria) this;
        }

        public Criteria andMphoneLessThan(String value) {
            addCriterion("MPHONE <", value, "mphone");
            return (Criteria) this;
        }

        public Criteria andMphoneLessThanOrEqualTo(String value) {
            addCriterion("MPHONE <=", value, "mphone");
            return (Criteria) this;
        }

        public Criteria andMphoneLike(String value) {
            addCriterion("MPHONE like", value, "mphone");
            return (Criteria) this;
        }

        public Criteria andMphoneNotLike(String value) {
            addCriterion("MPHONE not like", value, "mphone");
            return (Criteria) this;
        }

        public Criteria andMphoneIn(List<String> values) {
            addCriterion("MPHONE in", values, "mphone");
            return (Criteria) this;
        }

        public Criteria andMphoneNotIn(List<String> values) {
            addCriterion("MPHONE not in", values, "mphone");
            return (Criteria) this;
        }

        public Criteria andMphoneBetween(String value1, String value2) {
            addCriterion("MPHONE between", value1, value2, "mphone");
            return (Criteria) this;
        }

        public Criteria andMphoneNotBetween(String value1, String value2) {
            addCriterion("MPHONE not between", value1, value2, "mphone");
            return (Criteria) this;
        }

        public Criteria andMcardnoIsNull() {
            addCriterion("MCARDNO is null");
            return (Criteria) this;
        }

        public Criteria andMcardnoIsNotNull() {
            addCriterion("MCARDNO is not null");
            return (Criteria) this;
        }

        public Criteria andMcardnoEqualTo(String value) {
            addCriterion("MCARDNO =", value, "mcardno");
            return (Criteria) this;
        }

        public Criteria andMcardnoNotEqualTo(String value) {
            addCriterion("MCARDNO <>", value, "mcardno");
            return (Criteria) this;
        }

        public Criteria andMcardnoGreaterThan(String value) {
            addCriterion("MCARDNO >", value, "mcardno");
            return (Criteria) this;
        }

        public Criteria andMcardnoGreaterThanOrEqualTo(String value) {
            addCriterion("MCARDNO >=", value, "mcardno");
            return (Criteria) this;
        }

        public Criteria andMcardnoLessThan(String value) {
            addCriterion("MCARDNO <", value, "mcardno");
            return (Criteria) this;
        }

        public Criteria andMcardnoLessThanOrEqualTo(String value) {
            addCriterion("MCARDNO <=", value, "mcardno");
            return (Criteria) this;
        }

        public Criteria andMcardnoLike(String value) {
            addCriterion("MCARDNO like", value, "mcardno");
            return (Criteria) this;
        }

        public Criteria andMcardnoNotLike(String value) {
            addCriterion("MCARDNO not like", value, "mcardno");
            return (Criteria) this;
        }

        public Criteria andMcardnoIn(List<String> values) {
            addCriterion("MCARDNO in", values, "mcardno");
            return (Criteria) this;
        }

        public Criteria andMcardnoNotIn(List<String> values) {
            addCriterion("MCARDNO not in", values, "mcardno");
            return (Criteria) this;
        }

        public Criteria andMcardnoBetween(String value1, String value2) {
            addCriterion("MCARDNO between", value1, value2, "mcardno");
            return (Criteria) this;
        }

        public Criteria andMcardnoNotBetween(String value1, String value2) {
            addCriterion("MCARDNO not between", value1, value2, "mcardno");
            return (Criteria) this;
        }

        public Criteria andMaddrIsNull() {
            addCriterion("MADDR is null");
            return (Criteria) this;
        }

        public Criteria andMaddrIsNotNull() {
            addCriterion("MADDR is not null");
            return (Criteria) this;
        }

        public Criteria andMaddrEqualTo(String value) {
            addCriterion("MADDR =", value, "maddr");
            return (Criteria) this;
        }

        public Criteria andMaddrNotEqualTo(String value) {
            addCriterion("MADDR <>", value, "maddr");
            return (Criteria) this;
        }

        public Criteria andMaddrGreaterThan(String value) {
            addCriterion("MADDR >", value, "maddr");
            return (Criteria) this;
        }

        public Criteria andMaddrGreaterThanOrEqualTo(String value) {
            addCriterion("MADDR >=", value, "maddr");
            return (Criteria) this;
        }

        public Criteria andMaddrLessThan(String value) {
            addCriterion("MADDR <", value, "maddr");
            return (Criteria) this;
        }

        public Criteria andMaddrLessThanOrEqualTo(String value) {
            addCriterion("MADDR <=", value, "maddr");
            return (Criteria) this;
        }

        public Criteria andMaddrLike(String value) {
            addCriterion("MADDR like", value, "maddr");
            return (Criteria) this;
        }

        public Criteria andMaddrNotLike(String value) {
            addCriterion("MADDR not like", value, "maddr");
            return (Criteria) this;
        }

        public Criteria andMaddrIn(List<String> values) {
            addCriterion("MADDR in", values, "maddr");
            return (Criteria) this;
        }

        public Criteria andMaddrNotIn(List<String> values) {
            addCriterion("MADDR not in", values, "maddr");
            return (Criteria) this;
        }

        public Criteria andMaddrBetween(String value1, String value2) {
            addCriterion("MADDR between", value1, value2, "maddr");
            return (Criteria) this;
        }

        public Criteria andMaddrNotBetween(String value1, String value2) {
            addCriterion("MADDR not between", value1, value2, "maddr");
            return (Criteria) this;
        }

        public Criteria andMlicenseIsNull() {
            addCriterion("MLICENSE is null");
            return (Criteria) this;
        }

        public Criteria andMlicenseIsNotNull() {
            addCriterion("MLICENSE is not null");
            return (Criteria) this;
        }

        public Criteria andMlicenseEqualTo(String value) {
            addCriterion("MLICENSE =", value, "mlicense");
            return (Criteria) this;
        }

        public Criteria andMlicenseNotEqualTo(String value) {
            addCriterion("MLICENSE <>", value, "mlicense");
            return (Criteria) this;
        }

        public Criteria andMlicenseGreaterThan(String value) {
            addCriterion("MLICENSE >", value, "mlicense");
            return (Criteria) this;
        }

        public Criteria andMlicenseGreaterThanOrEqualTo(String value) {
            addCriterion("MLICENSE >=", value, "mlicense");
            return (Criteria) this;
        }

        public Criteria andMlicenseLessThan(String value) {
            addCriterion("MLICENSE <", value, "mlicense");
            return (Criteria) this;
        }

        public Criteria andMlicenseLessThanOrEqualTo(String value) {
            addCriterion("MLICENSE <=", value, "mlicense");
            return (Criteria) this;
        }

        public Criteria andMlicenseLike(String value) {
            addCriterion("MLICENSE like", value, "mlicense");
            return (Criteria) this;
        }

        public Criteria andMlicenseNotLike(String value) {
            addCriterion("MLICENSE not like", value, "mlicense");
            return (Criteria) this;
        }

        public Criteria andMlicenseIn(List<String> values) {
            addCriterion("MLICENSE in", values, "mlicense");
            return (Criteria) this;
        }

        public Criteria andMlicenseNotIn(List<String> values) {
            addCriterion("MLICENSE not in", values, "mlicense");
            return (Criteria) this;
        }

        public Criteria andMlicenseBetween(String value1, String value2) {
            addCriterion("MLICENSE between", value1, value2, "mlicense");
            return (Criteria) this;
        }

        public Criteria andMlicenseNotBetween(String value1, String value2) {
            addCriterion("MLICENSE not between", value1, value2, "mlicense");
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

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIsNull() {
            addCriterion("AUDIT_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIsNotNull() {
            addCriterion("AUDIT_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusEqualTo(String value) {
            addCriterion("AUDIT_STATUS =", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotEqualTo(String value) {
            addCriterion("AUDIT_STATUS <>", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusGreaterThan(String value) {
            addCriterion("AUDIT_STATUS >", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS >=", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLessThan(String value) {
            addCriterion("AUDIT_STATUS <", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS <=", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLike(String value) {
            addCriterion("AUDIT_STATUS like", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotLike(String value) {
            addCriterion("AUDIT_STATUS not like", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIn(List<String> values) {
            addCriterion("AUDIT_STATUS in", values, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotIn(List<String> values) {
            addCriterion("AUDIT_STATUS not in", values, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS between", value1, value2, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS not between", value1, value2, "auditStatus");
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