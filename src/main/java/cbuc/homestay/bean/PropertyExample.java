package cbuc.homestay.bean;

import java.util.ArrayList;
import java.util.List;

public class PropertyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PropertyExample() {
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

        public Criteria andRidIsNull() {
            addCriterion("RID is null");
            return (Criteria) this;
        }

        public Criteria andRidIsNotNull() {
            addCriterion("RID is not null");
            return (Criteria) this;
        }

        public Criteria andRidEqualTo(Long value) {
            addCriterion("RID =", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidNotEqualTo(Long value) {
            addCriterion("RID <>", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidGreaterThan(Long value) {
            addCriterion("RID >", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidGreaterThanOrEqualTo(Long value) {
            addCriterion("RID >=", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidLessThan(Long value) {
            addCriterion("RID <", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidLessThanOrEqualTo(Long value) {
            addCriterion("RID <=", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidIn(List<Long> values) {
            addCriterion("RID in", values, "rid");
            return (Criteria) this;
        }

        public Criteria andRidNotIn(List<Long> values) {
            addCriterion("RID not in", values, "rid");
            return (Criteria) this;
        }

        public Criteria andRidBetween(Long value1, Long value2) {
            addCriterion("RID between", value1, value2, "rid");
            return (Criteria) this;
        }

        public Criteria andRidNotBetween(Long value1, Long value2) {
            addCriterion("RID not between", value1, value2, "rid");
            return (Criteria) this;
        }

        public Criteria andProKeyIsNull() {
            addCriterion("PRO_KEY is null");
            return (Criteria) this;
        }

        public Criteria andProKeyIsNotNull() {
            addCriterion("PRO_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andProKeyEqualTo(String value) {
            addCriterion("PRO_KEY =", value, "proKey");
            return (Criteria) this;
        }

        public Criteria andProKeyNotEqualTo(String value) {
            addCriterion("PRO_KEY <>", value, "proKey");
            return (Criteria) this;
        }

        public Criteria andProKeyGreaterThan(String value) {
            addCriterion("PRO_KEY >", value, "proKey");
            return (Criteria) this;
        }

        public Criteria andProKeyGreaterThanOrEqualTo(String value) {
            addCriterion("PRO_KEY >=", value, "proKey");
            return (Criteria) this;
        }

        public Criteria andProKeyLessThan(String value) {
            addCriterion("PRO_KEY <", value, "proKey");
            return (Criteria) this;
        }

        public Criteria andProKeyLessThanOrEqualTo(String value) {
            addCriterion("PRO_KEY <=", value, "proKey");
            return (Criteria) this;
        }

        public Criteria andProKeyLike(String value) {
            addCriterion("PRO_KEY like", value, "proKey");
            return (Criteria) this;
        }

        public Criteria andProKeyNotLike(String value) {
            addCriterion("PRO_KEY not like", value, "proKey");
            return (Criteria) this;
        }

        public Criteria andProKeyIn(List<String> values) {
            addCriterion("PRO_KEY in", values, "proKey");
            return (Criteria) this;
        }

        public Criteria andProKeyNotIn(List<String> values) {
            addCriterion("PRO_KEY not in", values, "proKey");
            return (Criteria) this;
        }

        public Criteria andProKeyBetween(String value1, String value2) {
            addCriterion("PRO_KEY between", value1, value2, "proKey");
            return (Criteria) this;
        }

        public Criteria andProKeyNotBetween(String value1, String value2) {
            addCriterion("PRO_KEY not between", value1, value2, "proKey");
            return (Criteria) this;
        }

        public Criteria andProValueIsNull() {
            addCriterion("PRO_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andProValueIsNotNull() {
            addCriterion("PRO_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andProValueEqualTo(String value) {
            addCriterion("PRO_VALUE =", value, "proValue");
            return (Criteria) this;
        }

        public Criteria andProValueNotEqualTo(String value) {
            addCriterion("PRO_VALUE <>", value, "proValue");
            return (Criteria) this;
        }

        public Criteria andProValueGreaterThan(String value) {
            addCriterion("PRO_VALUE >", value, "proValue");
            return (Criteria) this;
        }

        public Criteria andProValueGreaterThanOrEqualTo(String value) {
            addCriterion("PRO_VALUE >=", value, "proValue");
            return (Criteria) this;
        }

        public Criteria andProValueLessThan(String value) {
            addCriterion("PRO_VALUE <", value, "proValue");
            return (Criteria) this;
        }

        public Criteria andProValueLessThanOrEqualTo(String value) {
            addCriterion("PRO_VALUE <=", value, "proValue");
            return (Criteria) this;
        }

        public Criteria andProValueLike(String value) {
            addCriterion("PRO_VALUE like", value, "proValue");
            return (Criteria) this;
        }

        public Criteria andProValueNotLike(String value) {
            addCriterion("PRO_VALUE not like", value, "proValue");
            return (Criteria) this;
        }

        public Criteria andProValueIn(List<String> values) {
            addCriterion("PRO_VALUE in", values, "proValue");
            return (Criteria) this;
        }

        public Criteria andProValueNotIn(List<String> values) {
            addCriterion("PRO_VALUE not in", values, "proValue");
            return (Criteria) this;
        }

        public Criteria andProValueBetween(String value1, String value2) {
            addCriterion("PRO_VALUE between", value1, value2, "proValue");
            return (Criteria) this;
        }

        public Criteria andProValueNotBetween(String value1, String value2) {
            addCriterion("PRO_VALUE not between", value1, value2, "proValue");
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