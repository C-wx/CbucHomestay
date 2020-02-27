package cbuc.homestay.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MerchantExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MerchantExample() {
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

        public Criteria andAuditIdIsNull() {
            addCriterion("AUDIT_ID is null");
            return (Criteria) this;
        }

        public Criteria andAuditIdIsNotNull() {
            addCriterion("AUDIT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAuditIdEqualTo(Long value) {
            addCriterion("AUDIT_ID =", value, "auditId");
            return (Criteria) this;
        }

        public Criteria andAuditIdNotEqualTo(Long value) {
            addCriterion("AUDIT_ID <>", value, "auditId");
            return (Criteria) this;
        }

        public Criteria andAuditIdGreaterThan(Long value) {
            addCriterion("AUDIT_ID >", value, "auditId");
            return (Criteria) this;
        }

        public Criteria andAuditIdGreaterThanOrEqualTo(Long value) {
            addCriterion("AUDIT_ID >=", value, "auditId");
            return (Criteria) this;
        }

        public Criteria andAuditIdLessThan(Long value) {
            addCriterion("AUDIT_ID <", value, "auditId");
            return (Criteria) this;
        }

        public Criteria andAuditIdLessThanOrEqualTo(Long value) {
            addCriterion("AUDIT_ID <=", value, "auditId");
            return (Criteria) this;
        }

        public Criteria andAuditIdIn(List<Long> values) {
            addCriterion("AUDIT_ID in", values, "auditId");
            return (Criteria) this;
        }

        public Criteria andAuditIdNotIn(List<Long> values) {
            addCriterion("AUDIT_ID not in", values, "auditId");
            return (Criteria) this;
        }

        public Criteria andAuditIdBetween(Long value1, Long value2) {
            addCriterion("AUDIT_ID between", value1, value2, "auditId");
            return (Criteria) this;
        }

        public Criteria andAuditIdNotBetween(Long value1, Long value2) {
            addCriterion("AUDIT_ID not between", value1, value2, "auditId");
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

        public Criteria andMaccountIsNull() {
            addCriterion("MACCOUNT is null");
            return (Criteria) this;
        }

        public Criteria andMaccountIsNotNull() {
            addCriterion("MACCOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andMaccountEqualTo(String value) {
            addCriterion("MACCOUNT =", value, "maccount");
            return (Criteria) this;
        }

        public Criteria andMaccountNotEqualTo(String value) {
            addCriterion("MACCOUNT <>", value, "maccount");
            return (Criteria) this;
        }

        public Criteria andMaccountGreaterThan(String value) {
            addCriterion("MACCOUNT >", value, "maccount");
            return (Criteria) this;
        }

        public Criteria andMaccountGreaterThanOrEqualTo(String value) {
            addCriterion("MACCOUNT >=", value, "maccount");
            return (Criteria) this;
        }

        public Criteria andMaccountLessThan(String value) {
            addCriterion("MACCOUNT <", value, "maccount");
            return (Criteria) this;
        }

        public Criteria andMaccountLessThanOrEqualTo(String value) {
            addCriterion("MACCOUNT <=", value, "maccount");
            return (Criteria) this;
        }

        public Criteria andMaccountLike(String value) {
            addCriterion("MACCOUNT like", value, "maccount");
            return (Criteria) this;
        }

        public Criteria andMaccountNotLike(String value) {
            addCriterion("MACCOUNT not like", value, "maccount");
            return (Criteria) this;
        }

        public Criteria andMaccountIn(List<String> values) {
            addCriterion("MACCOUNT in", values, "maccount");
            return (Criteria) this;
        }

        public Criteria andMaccountNotIn(List<String> values) {
            addCriterion("MACCOUNT not in", values, "maccount");
            return (Criteria) this;
        }

        public Criteria andMaccountBetween(String value1, String value2) {
            addCriterion("MACCOUNT between", value1, value2, "maccount");
            return (Criteria) this;
        }

        public Criteria andMaccountNotBetween(String value1, String value2) {
            addCriterion("MACCOUNT not between", value1, value2, "maccount");
            return (Criteria) this;
        }

        public Criteria andMpwdIsNull() {
            addCriterion("MPWD is null");
            return (Criteria) this;
        }

        public Criteria andMpwdIsNotNull() {
            addCriterion("MPWD is not null");
            return (Criteria) this;
        }

        public Criteria andMpwdEqualTo(String value) {
            addCriterion("MPWD =", value, "mpwd");
            return (Criteria) this;
        }

        public Criteria andMpwdNotEqualTo(String value) {
            addCriterion("MPWD <>", value, "mpwd");
            return (Criteria) this;
        }

        public Criteria andMpwdGreaterThan(String value) {
            addCriterion("MPWD >", value, "mpwd");
            return (Criteria) this;
        }

        public Criteria andMpwdGreaterThanOrEqualTo(String value) {
            addCriterion("MPWD >=", value, "mpwd");
            return (Criteria) this;
        }

        public Criteria andMpwdLessThan(String value) {
            addCriterion("MPWD <", value, "mpwd");
            return (Criteria) this;
        }

        public Criteria andMpwdLessThanOrEqualTo(String value) {
            addCriterion("MPWD <=", value, "mpwd");
            return (Criteria) this;
        }

        public Criteria andMpwdLike(String value) {
            addCriterion("MPWD like", value, "mpwd");
            return (Criteria) this;
        }

        public Criteria andMpwdNotLike(String value) {
            addCriterion("MPWD not like", value, "mpwd");
            return (Criteria) this;
        }

        public Criteria andMpwdIn(List<String> values) {
            addCriterion("MPWD in", values, "mpwd");
            return (Criteria) this;
        }

        public Criteria andMpwdNotIn(List<String> values) {
            addCriterion("MPWD not in", values, "mpwd");
            return (Criteria) this;
        }

        public Criteria andMpwdBetween(String value1, String value2) {
            addCriterion("MPWD between", value1, value2, "mpwd");
            return (Criteria) this;
        }

        public Criteria andMpwdNotBetween(String value1, String value2) {
            addCriterion("MPWD not between", value1, value2, "mpwd");
            return (Criteria) this;
        }

        public Criteria andMlevelIsNull() {
            addCriterion("MLEVEL is null");
            return (Criteria) this;
        }

        public Criteria andMlevelIsNotNull() {
            addCriterion("MLEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andMlevelEqualTo(String value) {
            addCriterion("MLEVEL =", value, "mlevel");
            return (Criteria) this;
        }

        public Criteria andMlevelNotEqualTo(String value) {
            addCriterion("MLEVEL <>", value, "mlevel");
            return (Criteria) this;
        }

        public Criteria andMlevelGreaterThan(String value) {
            addCriterion("MLEVEL >", value, "mlevel");
            return (Criteria) this;
        }

        public Criteria andMlevelGreaterThanOrEqualTo(String value) {
            addCriterion("MLEVEL >=", value, "mlevel");
            return (Criteria) this;
        }

        public Criteria andMlevelLessThan(String value) {
            addCriterion("MLEVEL <", value, "mlevel");
            return (Criteria) this;
        }

        public Criteria andMlevelLessThanOrEqualTo(String value) {
            addCriterion("MLEVEL <=", value, "mlevel");
            return (Criteria) this;
        }

        public Criteria andMlevelLike(String value) {
            addCriterion("MLEVEL like", value, "mlevel");
            return (Criteria) this;
        }

        public Criteria andMlevelNotLike(String value) {
            addCriterion("MLEVEL not like", value, "mlevel");
            return (Criteria) this;
        }

        public Criteria andMlevelIn(List<String> values) {
            addCriterion("MLEVEL in", values, "mlevel");
            return (Criteria) this;
        }

        public Criteria andMlevelNotIn(List<String> values) {
            addCriterion("MLEVEL not in", values, "mlevel");
            return (Criteria) this;
        }

        public Criteria andMlevelBetween(String value1, String value2) {
            addCriterion("MLEVEL between", value1, value2, "mlevel");
            return (Criteria) this;
        }

        public Criteria andMlevelNotBetween(String value1, String value2) {
            addCriterion("MLEVEL not between", value1, value2, "mlevel");
            return (Criteria) this;
        }

        public Criteria andMdescIsNull() {
            addCriterion("MDESC is null");
            return (Criteria) this;
        }

        public Criteria andMdescIsNotNull() {
            addCriterion("MDESC is not null");
            return (Criteria) this;
        }

        public Criteria andMdescEqualTo(String value) {
            addCriterion("MDESC =", value, "mdesc");
            return (Criteria) this;
        }

        public Criteria andMdescNotEqualTo(String value) {
            addCriterion("MDESC <>", value, "mdesc");
            return (Criteria) this;
        }

        public Criteria andMdescGreaterThan(String value) {
            addCriterion("MDESC >", value, "mdesc");
            return (Criteria) this;
        }

        public Criteria andMdescGreaterThanOrEqualTo(String value) {
            addCriterion("MDESC >=", value, "mdesc");
            return (Criteria) this;
        }

        public Criteria andMdescLessThan(String value) {
            addCriterion("MDESC <", value, "mdesc");
            return (Criteria) this;
        }

        public Criteria andMdescLessThanOrEqualTo(String value) {
            addCriterion("MDESC <=", value, "mdesc");
            return (Criteria) this;
        }

        public Criteria andMdescLike(String value) {
            addCriterion("MDESC like", value, "mdesc");
            return (Criteria) this;
        }

        public Criteria andMdescNotLike(String value) {
            addCriterion("MDESC not like", value, "mdesc");
            return (Criteria) this;
        }

        public Criteria andMdescIn(List<String> values) {
            addCriterion("MDESC in", values, "mdesc");
            return (Criteria) this;
        }

        public Criteria andMdescNotIn(List<String> values) {
            addCriterion("MDESC not in", values, "mdesc");
            return (Criteria) this;
        }

        public Criteria andMdescBetween(String value1, String value2) {
            addCriterion("MDESC between", value1, value2, "mdesc");
            return (Criteria) this;
        }

        public Criteria andMdescNotBetween(String value1, String value2) {
            addCriterion("MDESC not between", value1, value2, "mdesc");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlIsNull() {
            addCriterion("AVATAR_URL is null");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlIsNotNull() {
            addCriterion("AVATAR_URL is not null");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlEqualTo(String value) {
            addCriterion("AVATAR_URL =", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlNotEqualTo(String value) {
            addCriterion("AVATAR_URL <>", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlGreaterThan(String value) {
            addCriterion("AVATAR_URL >", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlGreaterThanOrEqualTo(String value) {
            addCriterion("AVATAR_URL >=", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlLessThan(String value) {
            addCriterion("AVATAR_URL <", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlLessThanOrEqualTo(String value) {
            addCriterion("AVATAR_URL <=", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlLike(String value) {
            addCriterion("AVATAR_URL like", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlNotLike(String value) {
            addCriterion("AVATAR_URL not like", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlIn(List<String> values) {
            addCriterion("AVATAR_URL in", values, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlNotIn(List<String> values) {
            addCriterion("AVATAR_URL not in", values, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlBetween(String value1, String value2) {
            addCriterion("AVATAR_URL between", value1, value2, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlNotBetween(String value1, String value2) {
            addCriterion("AVATAR_URL not between", value1, value2, "avatarUrl");
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