package com.paintee.common.repository.entity;

import java.util.ArrayList;
import java.util.List;

public class RewardExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RewardExample() {
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

        public Criteria andUserIdIsNull() {
            addCriterion("\"user_id\" is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("\"user_id\" is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("\"user_id\" =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("\"user_id\" <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("\"user_id\" >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("\"user_id\" >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("\"user_id\" <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("\"user_id\" <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("\"user_id\" like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("\"user_id\" not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("\"user_id\" in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("\"user_id\" not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("\"user_id\" between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("\"user_id\" not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andAccountNoIsNull() {
            addCriterion("\"account_no\" is null");
            return (Criteria) this;
        }

        public Criteria andAccountNoIsNotNull() {
            addCriterion("\"account_no\" is not null");
            return (Criteria) this;
        }

        public Criteria andAccountNoEqualTo(String value) {
            addCriterion("\"account_no\" =", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotEqualTo(String value) {
            addCriterion("\"account_no\" <>", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoGreaterThan(String value) {
            addCriterion("\"account_no\" >", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoGreaterThanOrEqualTo(String value) {
            addCriterion("\"account_no\" >=", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoLessThan(String value) {
            addCriterion("\"account_no\" <", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoLessThanOrEqualTo(String value) {
            addCriterion("\"account_no\" <=", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoLike(String value) {
            addCriterion("\"account_no\" like", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotLike(String value) {
            addCriterion("\"account_no\" not like", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoIn(List<String> values) {
            addCriterion("\"account_no\" in", values, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotIn(List<String> values) {
            addCriterion("\"account_no\" not in", values, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoBetween(String value1, String value2) {
            addCriterion("\"account_no\" between", value1, value2, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotBetween(String value1, String value2) {
            addCriterion("\"account_no\" not between", value1, value2, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNameIsNull() {
            addCriterion("\"account_name\" is null");
            return (Criteria) this;
        }

        public Criteria andAccountNameIsNotNull() {
            addCriterion("\"account_name\" is not null");
            return (Criteria) this;
        }

        public Criteria andAccountNameEqualTo(String value) {
            addCriterion("\"account_name\" =", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotEqualTo(String value) {
            addCriterion("\"account_name\" <>", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameGreaterThan(String value) {
            addCriterion("\"account_name\" >", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("\"account_name\" >=", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLessThan(String value) {
            addCriterion("\"account_name\" <", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLessThanOrEqualTo(String value) {
            addCriterion("\"account_name\" <=", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLike(String value) {
            addCriterion("\"account_name\" like", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotLike(String value) {
            addCriterion("\"account_name\" not like", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameIn(List<String> values) {
            addCriterion("\"account_name\" in", values, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotIn(List<String> values) {
            addCriterion("\"account_name\" not in", values, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameBetween(String value1, String value2) {
            addCriterion("\"account_name\" between", value1, value2, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotBetween(String value1, String value2) {
            addCriterion("\"account_name\" not between", value1, value2, "accountName");
            return (Criteria) this;
        }

        public Criteria andEarmRequestedMoneyIsNull() {
            addCriterion("\"earm_requested_money\" is null");
            return (Criteria) this;
        }

        public Criteria andEarmRequestedMoneyIsNotNull() {
            addCriterion("\"earm_requested_money\" is not null");
            return (Criteria) this;
        }

        public Criteria andEarmRequestedMoneyEqualTo(Integer value) {
            addCriterion("\"earm_requested_money\" =", value, "earmRequestedMoney");
            return (Criteria) this;
        }

        public Criteria andEarmRequestedMoneyNotEqualTo(Integer value) {
            addCriterion("\"earm_requested_money\" <>", value, "earmRequestedMoney");
            return (Criteria) this;
        }

        public Criteria andEarmRequestedMoneyGreaterThan(Integer value) {
            addCriterion("\"earm_requested_money\" >", value, "earmRequestedMoney");
            return (Criteria) this;
        }

        public Criteria andEarmRequestedMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("\"earm_requested_money\" >=", value, "earmRequestedMoney");
            return (Criteria) this;
        }

        public Criteria andEarmRequestedMoneyLessThan(Integer value) {
            addCriterion("\"earm_requested_money\" <", value, "earmRequestedMoney");
            return (Criteria) this;
        }

        public Criteria andEarmRequestedMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("\"earm_requested_money\" <=", value, "earmRequestedMoney");
            return (Criteria) this;
        }

        public Criteria andEarmRequestedMoneyIn(List<Integer> values) {
            addCriterion("\"earm_requested_money\" in", values, "earmRequestedMoney");
            return (Criteria) this;
        }

        public Criteria andEarmRequestedMoneyNotIn(List<Integer> values) {
            addCriterion("\"earm_requested_money\" not in", values, "earmRequestedMoney");
            return (Criteria) this;
        }

        public Criteria andEarmRequestedMoneyBetween(Integer value1, Integer value2) {
            addCriterion("\"earm_requested_money\" between", value1, value2, "earmRequestedMoney");
            return (Criteria) this;
        }

        public Criteria andEarmRequestedMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("\"earm_requested_money\" not between", value1, value2, "earmRequestedMoney");
            return (Criteria) this;
        }

        public Criteria andRewardStatusIsNull() {
            addCriterion("\"reward_status\" is null");
            return (Criteria) this;
        }

        public Criteria andRewardStatusIsNotNull() {
            addCriterion("\"reward_status\" is not null");
            return (Criteria) this;
        }

        public Criteria andRewardStatusEqualTo(String value) {
            addCriterion("\"reward_status\" =", value, "rewardStatus");
            return (Criteria) this;
        }

        public Criteria andRewardStatusNotEqualTo(String value) {
            addCriterion("\"reward_status\" <>", value, "rewardStatus");
            return (Criteria) this;
        }

        public Criteria andRewardStatusGreaterThan(String value) {
            addCriterion("\"reward_status\" >", value, "rewardStatus");
            return (Criteria) this;
        }

        public Criteria andRewardStatusGreaterThanOrEqualTo(String value) {
            addCriterion("\"reward_status\" >=", value, "rewardStatus");
            return (Criteria) this;
        }

        public Criteria andRewardStatusLessThan(String value) {
            addCriterion("\"reward_status\" <", value, "rewardStatus");
            return (Criteria) this;
        }

        public Criteria andRewardStatusLessThanOrEqualTo(String value) {
            addCriterion("\"reward_status\" <=", value, "rewardStatus");
            return (Criteria) this;
        }

        public Criteria andRewardStatusLike(String value) {
            addCriterion("\"reward_status\" like", value, "rewardStatus");
            return (Criteria) this;
        }

        public Criteria andRewardStatusNotLike(String value) {
            addCriterion("\"reward_status\" not like", value, "rewardStatus");
            return (Criteria) this;
        }

        public Criteria andRewardStatusIn(List<String> values) {
            addCriterion("\"reward_status\" in", values, "rewardStatus");
            return (Criteria) this;
        }

        public Criteria andRewardStatusNotIn(List<String> values) {
            addCriterion("\"reward_status\" not in", values, "rewardStatus");
            return (Criteria) this;
        }

        public Criteria andRewardStatusBetween(String value1, String value2) {
            addCriterion("\"reward_status\" between", value1, value2, "rewardStatus");
            return (Criteria) this;
        }

        public Criteria andRewardStatusNotBetween(String value1, String value2) {
            addCriterion("\"reward_status\" not between", value1, value2, "rewardStatus");
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