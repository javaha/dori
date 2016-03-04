package com.paintee.common.repository.entity;

import java.util.ArrayList;
import java.util.List;

public class PopularPaintingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PopularPaintingExample() {
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

        public Criteria andSeqIsNull() {
            addCriterion("seq is null");
            return (Criteria) this;
        }

        public Criteria andSeqIsNotNull() {
            addCriterion("seq is not null");
            return (Criteria) this;
        }

        public Criteria andSeqEqualTo(Integer value) {
            addCriterion("seq =", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqNotEqualTo(Integer value) {
            addCriterion("seq <>", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqGreaterThan(Integer value) {
            addCriterion("seq >", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqGreaterThanOrEqualTo(Integer value) {
            addCriterion("seq >=", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqLessThan(Integer value) {
            addCriterion("seq <", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqLessThanOrEqualTo(Integer value) {
            addCriterion("seq <=", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqIn(List<Integer> values) {
            addCriterion("seq in", values, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqNotIn(List<Integer> values) {
            addCriterion("seq not in", values, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqBetween(Integer value1, Integer value2) {
            addCriterion("seq between", value1, value2, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqNotBetween(Integer value1, Integer value2) {
            addCriterion("seq not between", value1, value2, "seq");
            return (Criteria) this;
        }

        public Criteria andPaintingIdIsNull() {
            addCriterion("painting_id is null");
            return (Criteria) this;
        }

        public Criteria andPaintingIdIsNotNull() {
            addCriterion("painting_id is not null");
            return (Criteria) this;
        }

        public Criteria andPaintingIdEqualTo(String value) {
            addCriterion("painting_id =", value, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPaintingIdNotEqualTo(String value) {
            addCriterion("painting_id <>", value, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPaintingIdGreaterThan(String value) {
            addCriterion("painting_id >", value, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPaintingIdGreaterThanOrEqualTo(String value) {
            addCriterion("painting_id >=", value, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPaintingIdLessThan(String value) {
            addCriterion("painting_id <", value, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPaintingIdLessThanOrEqualTo(String value) {
            addCriterion("painting_id <=", value, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPaintingIdLike(String value) {
            addCriterion("painting_id like", value, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPaintingIdNotLike(String value) {
            addCriterion("painting_id not like", value, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPaintingIdIn(List<String> values) {
            addCriterion("painting_id in", values, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPaintingIdNotIn(List<String> values) {
            addCriterion("painting_id not in", values, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPaintingIdBetween(String value1, String value2) {
            addCriterion("painting_id between", value1, value2, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPaintingIdNotBetween(String value1, String value2) {
            addCriterion("painting_id not between", value1, value2, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPurchaseCountIsNull() {
            addCriterion("purchase_count is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseCountIsNotNull() {
            addCriterion("purchase_count is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseCountEqualTo(Integer value) {
            addCriterion("purchase_count =", value, "purchaseCount");
            return (Criteria) this;
        }

        public Criteria andPurchaseCountNotEqualTo(Integer value) {
            addCriterion("purchase_count <>", value, "purchaseCount");
            return (Criteria) this;
        }

        public Criteria andPurchaseCountGreaterThan(Integer value) {
            addCriterion("purchase_count >", value, "purchaseCount");
            return (Criteria) this;
        }

        public Criteria andPurchaseCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("purchase_count >=", value, "purchaseCount");
            return (Criteria) this;
        }

        public Criteria andPurchaseCountLessThan(Integer value) {
            addCriterion("purchase_count <", value, "purchaseCount");
            return (Criteria) this;
        }

        public Criteria andPurchaseCountLessThanOrEqualTo(Integer value) {
            addCriterion("purchase_count <=", value, "purchaseCount");
            return (Criteria) this;
        }

        public Criteria andPurchaseCountIn(List<Integer> values) {
            addCriterion("purchase_count in", values, "purchaseCount");
            return (Criteria) this;
        }

        public Criteria andPurchaseCountNotIn(List<Integer> values) {
            addCriterion("purchase_count not in", values, "purchaseCount");
            return (Criteria) this;
        }

        public Criteria andPurchaseCountBetween(Integer value1, Integer value2) {
            addCriterion("purchase_count between", value1, value2, "purchaseCount");
            return (Criteria) this;
        }

        public Criteria andPurchaseCountNotBetween(Integer value1, Integer value2) {
            addCriterion("purchase_count not between", value1, value2, "purchaseCount");
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