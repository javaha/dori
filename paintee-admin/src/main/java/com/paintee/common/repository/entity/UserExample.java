package com.paintee.common.repository.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
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

        public Criteria andPasswordIsNull() {
            addCriterion("\"password\" is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("\"password\" is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("\"password\" =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("\"password\" <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("\"password\" >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("\"password\" >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("\"password\" <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("\"password\" <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("\"password\" like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("\"password\" not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("\"password\" in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("\"password\" not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("\"password\" between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("\"password\" not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("\"email\" is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("\"email\" is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("\"email\" =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("\"email\" <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("\"email\" >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("\"email\" >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("\"email\" <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("\"email\" <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("\"email\" like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("\"email\" not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("\"email\" in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("\"email\" not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("\"email\" between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("\"email\" not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("\"name\" is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("\"name\" is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("\"name\" =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("\"name\" <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("\"name\" >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("\"name\" >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("\"name\" <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("\"name\" <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("\"name\" like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("\"name\" not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("\"name\" in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("\"name\" not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("\"name\" between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("\"name\" not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andIntroduceIsNull() {
            addCriterion("\"introduce\" is null");
            return (Criteria) this;
        }

        public Criteria andIntroduceIsNotNull() {
            addCriterion("\"introduce\" is not null");
            return (Criteria) this;
        }

        public Criteria andIntroduceEqualTo(String value) {
            addCriterion("\"introduce\" =", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotEqualTo(String value) {
            addCriterion("\"introduce\" <>", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceGreaterThan(String value) {
            addCriterion("\"introduce\" >", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceGreaterThanOrEqualTo(String value) {
            addCriterion("\"introduce\" >=", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLessThan(String value) {
            addCriterion("\"introduce\" <", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLessThanOrEqualTo(String value) {
            addCriterion("\"introduce\" <=", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLike(String value) {
            addCriterion("\"introduce\" like", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotLike(String value) {
            addCriterion("\"introduce\" not like", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceIn(List<String> values) {
            addCriterion("\"introduce\" in", values, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotIn(List<String> values) {
            addCriterion("\"introduce\" not in", values, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceBetween(String value1, String value2) {
            addCriterion("\"introduce\" between", value1, value2, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotBetween(String value1, String value2) {
            addCriterion("\"introduce\" not between", value1, value2, "introduce");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("\"address\" is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("\"address\" is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("\"address\" =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("\"address\" <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("\"address\" >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("\"address\" >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("\"address\" <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("\"address\" <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("\"address\" like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("\"address\" not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("\"address\" in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("\"address\" not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("\"address\" between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("\"address\" not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andLocationIsNull() {
            addCriterion("\"location\" is null");
            return (Criteria) this;
        }

        public Criteria andLocationIsNotNull() {
            addCriterion("\"location\" is not null");
            return (Criteria) this;
        }

        public Criteria andLocationEqualTo(String value) {
            addCriterion("\"location\" =", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotEqualTo(String value) {
            addCriterion("\"location\" <>", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThan(String value) {
            addCriterion("\"location\" >", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThanOrEqualTo(String value) {
            addCriterion("\"location\" >=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThan(String value) {
            addCriterion("\"location\" <", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThanOrEqualTo(String value) {
            addCriterion("\"location\" <=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLike(String value) {
            addCriterion("\"location\" like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotLike(String value) {
            addCriterion("\"location\" not like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationIn(List<String> values) {
            addCriterion("\"location\" in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotIn(List<String> values) {
            addCriterion("\"location\" not in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationBetween(String value1, String value2) {
            addCriterion("\"location\" between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotBetween(String value1, String value2) {
            addCriterion("\"location\" not between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andUploadCntIsNull() {
            addCriterion("\"upload_cnt\" is null");
            return (Criteria) this;
        }

        public Criteria andUploadCntIsNotNull() {
            addCriterion("\"upload_cnt\" is not null");
            return (Criteria) this;
        }

        public Criteria andUploadCntEqualTo(Integer value) {
            addCriterion("\"upload_cnt\" =", value, "uploadCnt");
            return (Criteria) this;
        }

        public Criteria andUploadCntNotEqualTo(Integer value) {
            addCriterion("\"upload_cnt\" <>", value, "uploadCnt");
            return (Criteria) this;
        }

        public Criteria andUploadCntGreaterThan(Integer value) {
            addCriterion("\"upload_cnt\" >", value, "uploadCnt");
            return (Criteria) this;
        }

        public Criteria andUploadCntGreaterThanOrEqualTo(Integer value) {
            addCriterion("\"upload_cnt\" >=", value, "uploadCnt");
            return (Criteria) this;
        }

        public Criteria andUploadCntLessThan(Integer value) {
            addCriterion("\"upload_cnt\" <", value, "uploadCnt");
            return (Criteria) this;
        }

        public Criteria andUploadCntLessThanOrEqualTo(Integer value) {
            addCriterion("\"upload_cnt\" <=", value, "uploadCnt");
            return (Criteria) this;
        }

        public Criteria andUploadCntIn(List<Integer> values) {
            addCriterion("\"upload_cnt\" in", values, "uploadCnt");
            return (Criteria) this;
        }

        public Criteria andUploadCntNotIn(List<Integer> values) {
            addCriterion("\"upload_cnt\" not in", values, "uploadCnt");
            return (Criteria) this;
        }

        public Criteria andUploadCntBetween(Integer value1, Integer value2) {
            addCriterion("\"upload_cnt\" between", value1, value2, "uploadCnt");
            return (Criteria) this;
        }

        public Criteria andUploadCntNotBetween(Integer value1, Integer value2) {
            addCriterion("\"upload_cnt\" not between", value1, value2, "uploadCnt");
            return (Criteria) this;
        }

        public Criteria andPostCntIsNull() {
            addCriterion("\"post_cnt\" is null");
            return (Criteria) this;
        }

        public Criteria andPostCntIsNotNull() {
            addCriterion("\"post_cnt\" is not null");
            return (Criteria) this;
        }

        public Criteria andPostCntEqualTo(Integer value) {
            addCriterion("\"post_cnt\" =", value, "postCnt");
            return (Criteria) this;
        }

        public Criteria andPostCntNotEqualTo(Integer value) {
            addCriterion("\"post_cnt\" <>", value, "postCnt");
            return (Criteria) this;
        }

        public Criteria andPostCntGreaterThan(Integer value) {
            addCriterion("\"post_cnt\" >", value, "postCnt");
            return (Criteria) this;
        }

        public Criteria andPostCntGreaterThanOrEqualTo(Integer value) {
            addCriterion("\"post_cnt\" >=", value, "postCnt");
            return (Criteria) this;
        }

        public Criteria andPostCntLessThan(Integer value) {
            addCriterion("\"post_cnt\" <", value, "postCnt");
            return (Criteria) this;
        }

        public Criteria andPostCntLessThanOrEqualTo(Integer value) {
            addCriterion("\"post_cnt\" <=", value, "postCnt");
            return (Criteria) this;
        }

        public Criteria andPostCntIn(List<Integer> values) {
            addCriterion("\"post_cnt\" in", values, "postCnt");
            return (Criteria) this;
        }

        public Criteria andPostCntNotIn(List<Integer> values) {
            addCriterion("\"post_cnt\" not in", values, "postCnt");
            return (Criteria) this;
        }

        public Criteria andPostCntBetween(Integer value1, Integer value2) {
            addCriterion("\"post_cnt\" between", value1, value2, "postCnt");
            return (Criteria) this;
        }

        public Criteria andPostCntNotBetween(Integer value1, Integer value2) {
            addCriterion("\"post_cnt\" not between", value1, value2, "postCnt");
            return (Criteria) this;
        }

        public Criteria andEarnTotalMoneyIsNull() {
            addCriterion("\"earn_total_money\" is null");
            return (Criteria) this;
        }

        public Criteria andEarnTotalMoneyIsNotNull() {
            addCriterion("\"earn_total_money\" is not null");
            return (Criteria) this;
        }

        public Criteria andEarnTotalMoneyEqualTo(Integer value) {
            addCriterion("\"earn_total_money\" =", value, "earnTotalMoney");
            return (Criteria) this;
        }

        public Criteria andEarnTotalMoneyNotEqualTo(Integer value) {
            addCriterion("\"earn_total_money\" <>", value, "earnTotalMoney");
            return (Criteria) this;
        }

        public Criteria andEarnTotalMoneyGreaterThan(Integer value) {
            addCriterion("\"earn_total_money\" >", value, "earnTotalMoney");
            return (Criteria) this;
        }

        public Criteria andEarnTotalMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("\"earn_total_money\" >=", value, "earnTotalMoney");
            return (Criteria) this;
        }

        public Criteria andEarnTotalMoneyLessThan(Integer value) {
            addCriterion("\"earn_total_money\" <", value, "earnTotalMoney");
            return (Criteria) this;
        }

        public Criteria andEarnTotalMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("\"earn_total_money\" <=", value, "earnTotalMoney");
            return (Criteria) this;
        }

        public Criteria andEarnTotalMoneyIn(List<Integer> values) {
            addCriterion("\"earn_total_money\" in", values, "earnTotalMoney");
            return (Criteria) this;
        }

        public Criteria andEarnTotalMoneyNotIn(List<Integer> values) {
            addCriterion("\"earn_total_money\" not in", values, "earnTotalMoney");
            return (Criteria) this;
        }

        public Criteria andEarnTotalMoneyBetween(Integer value1, Integer value2) {
            addCriterion("\"earn_total_money\" between", value1, value2, "earnTotalMoney");
            return (Criteria) this;
        }

        public Criteria andEarnTotalMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("\"earn_total_money\" not between", value1, value2, "earnTotalMoney");
            return (Criteria) this;
        }

        public Criteria andEarnRewordMoneyIsNull() {
            addCriterion("\"earn_reword_money\" is null");
            return (Criteria) this;
        }

        public Criteria andEarnRewordMoneyIsNotNull() {
            addCriterion("\"earn_reword_money\" is not null");
            return (Criteria) this;
        }

        public Criteria andEarnRewordMoneyEqualTo(Integer value) {
            addCriterion("\"earn_reword_money\" =", value, "earnRewordMoney");
            return (Criteria) this;
        }

        public Criteria andEarnRewordMoneyNotEqualTo(Integer value) {
            addCriterion("\"earn_reword_money\" <>", value, "earnRewordMoney");
            return (Criteria) this;
        }

        public Criteria andEarnRewordMoneyGreaterThan(Integer value) {
            addCriterion("\"earn_reword_money\" >", value, "earnRewordMoney");
            return (Criteria) this;
        }

        public Criteria andEarnRewordMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("\"earn_reword_money\" >=", value, "earnRewordMoney");
            return (Criteria) this;
        }

        public Criteria andEarnRewordMoneyLessThan(Integer value) {
            addCriterion("\"earn_reword_money\" <", value, "earnRewordMoney");
            return (Criteria) this;
        }

        public Criteria andEarnRewordMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("\"earn_reword_money\" <=", value, "earnRewordMoney");
            return (Criteria) this;
        }

        public Criteria andEarnRewordMoneyIn(List<Integer> values) {
            addCriterion("\"earn_reword_money\" in", values, "earnRewordMoney");
            return (Criteria) this;
        }

        public Criteria andEarnRewordMoneyNotIn(List<Integer> values) {
            addCriterion("\"earn_reword_money\" not in", values, "earnRewordMoney");
            return (Criteria) this;
        }

        public Criteria andEarnRewordMoneyBetween(Integer value1, Integer value2) {
            addCriterion("\"earn_reword_money\" between", value1, value2, "earnRewordMoney");
            return (Criteria) this;
        }

        public Criteria andEarnRewordMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("\"earn_reword_money\" not between", value1, value2, "earnRewordMoney");
            return (Criteria) this;
        }

        public Criteria andResentSendAddrIsNull() {
            addCriterion("\"resent_send_addr\" is null");
            return (Criteria) this;
        }

        public Criteria andResentSendAddrIsNotNull() {
            addCriterion("\"resent_send_addr\" is not null");
            return (Criteria) this;
        }

        public Criteria andResentSendAddrEqualTo(String value) {
            addCriterion("\"resent_send_addr\" =", value, "resentSendAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendAddrNotEqualTo(String value) {
            addCriterion("\"resent_send_addr\" <>", value, "resentSendAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendAddrGreaterThan(String value) {
            addCriterion("\"resent_send_addr\" >", value, "resentSendAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendAddrGreaterThanOrEqualTo(String value) {
            addCriterion("\"resent_send_addr\" >=", value, "resentSendAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendAddrLessThan(String value) {
            addCriterion("\"resent_send_addr\" <", value, "resentSendAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendAddrLessThanOrEqualTo(String value) {
            addCriterion("\"resent_send_addr\" <=", value, "resentSendAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendAddrLike(String value) {
            addCriterion("\"resent_send_addr\" like", value, "resentSendAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendAddrNotLike(String value) {
            addCriterion("\"resent_send_addr\" not like", value, "resentSendAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendAddrIn(List<String> values) {
            addCriterion("\"resent_send_addr\" in", values, "resentSendAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendAddrNotIn(List<String> values) {
            addCriterion("\"resent_send_addr\" not in", values, "resentSendAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendAddrBetween(String value1, String value2) {
            addCriterion("\"resent_send_addr\" between", value1, value2, "resentSendAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendAddrNotBetween(String value1, String value2) {
            addCriterion("\"resent_send_addr\" not between", value1, value2, "resentSendAddr");
            return (Criteria) this;
        }

        public Criteria andResentSendNameIsNull() {
            addCriterion("\"resent_send_name\" is null");
            return (Criteria) this;
        }

        public Criteria andResentSendNameIsNotNull() {
            addCriterion("\"resent_send_name\" is not null");
            return (Criteria) this;
        }

        public Criteria andResentSendNameEqualTo(String value) {
            addCriterion("\"resent_send_name\" =", value, "resentSendName");
            return (Criteria) this;
        }

        public Criteria andResentSendNameNotEqualTo(String value) {
            addCriterion("\"resent_send_name\" <>", value, "resentSendName");
            return (Criteria) this;
        }

        public Criteria andResentSendNameGreaterThan(String value) {
            addCriterion("\"resent_send_name\" >", value, "resentSendName");
            return (Criteria) this;
        }

        public Criteria andResentSendNameGreaterThanOrEqualTo(String value) {
            addCriterion("\"resent_send_name\" >=", value, "resentSendName");
            return (Criteria) this;
        }

        public Criteria andResentSendNameLessThan(String value) {
            addCriterion("\"resent_send_name\" <", value, "resentSendName");
            return (Criteria) this;
        }

        public Criteria andResentSendNameLessThanOrEqualTo(String value) {
            addCriterion("\"resent_send_name\" <=", value, "resentSendName");
            return (Criteria) this;
        }

        public Criteria andResentSendNameLike(String value) {
            addCriterion("\"resent_send_name\" like", value, "resentSendName");
            return (Criteria) this;
        }

        public Criteria andResentSendNameNotLike(String value) {
            addCriterion("\"resent_send_name\" not like", value, "resentSendName");
            return (Criteria) this;
        }

        public Criteria andResentSendNameIn(List<String> values) {
            addCriterion("\"resent_send_name\" in", values, "resentSendName");
            return (Criteria) this;
        }

        public Criteria andResentSendNameNotIn(List<String> values) {
            addCriterion("\"resent_send_name\" not in", values, "resentSendName");
            return (Criteria) this;
        }

        public Criteria andResentSendNameBetween(String value1, String value2) {
            addCriterion("\"resent_send_name\" between", value1, value2, "resentSendName");
            return (Criteria) this;
        }

        public Criteria andResentSendNameNotBetween(String value1, String value2) {
            addCriterion("\"resent_send_name\" not between", value1, value2, "resentSendName");
            return (Criteria) this;
        }

        public Criteria andPointIsNull() {
            addCriterion("\"point\" is null");
            return (Criteria) this;
        }

        public Criteria andPointIsNotNull() {
            addCriterion("\"point\" is not null");
            return (Criteria) this;
        }

        public Criteria andPointEqualTo(Integer value) {
            addCriterion("\"point\" =", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointNotEqualTo(Integer value) {
            addCriterion("\"point\" <>", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointGreaterThan(Integer value) {
            addCriterion("\"point\" >", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointGreaterThanOrEqualTo(Integer value) {
            addCriterion("\"point\" >=", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointLessThan(Integer value) {
            addCriterion("\"point\" <", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointLessThanOrEqualTo(Integer value) {
            addCriterion("\"point\" <=", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointIn(List<Integer> values) {
            addCriterion("\"point\" in", values, "point");
            return (Criteria) this;
        }

        public Criteria andPointNotIn(List<Integer> values) {
            addCriterion("\"point\" not in", values, "point");
            return (Criteria) this;
        }

        public Criteria andPointBetween(Integer value1, Integer value2) {
            addCriterion("\"point\" between", value1, value2, "point");
            return (Criteria) this;
        }

        public Criteria andPointNotBetween(Integer value1, Integer value2) {
            addCriterion("\"point\" not between", value1, value2, "point");
            return (Criteria) this;
        }

        public Criteria andUserStatusIsNull() {
            addCriterion("\"user_status\" is null");
            return (Criteria) this;
        }

        public Criteria andUserStatusIsNotNull() {
            addCriterion("\"user_status\" is not null");
            return (Criteria) this;
        }

        public Criteria andUserStatusEqualTo(String value) {
            addCriterion("\"user_status\" =", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusNotEqualTo(String value) {
            addCriterion("\"user_status\" <>", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusGreaterThan(String value) {
            addCriterion("\"user_status\" >", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusGreaterThanOrEqualTo(String value) {
            addCriterion("\"user_status\" >=", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusLessThan(String value) {
            addCriterion("\"user_status\" <", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusLessThanOrEqualTo(String value) {
            addCriterion("\"user_status\" <=", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusLike(String value) {
            addCriterion("\"user_status\" like", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusNotLike(String value) {
            addCriterion("\"user_status\" not like", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusIn(List<String> values) {
            addCriterion("\"user_status\" in", values, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusNotIn(List<String> values) {
            addCriterion("\"user_status\" not in", values, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusBetween(String value1, String value2) {
            addCriterion("\"user_status\" between", value1, value2, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusNotBetween(String value1, String value2) {
            addCriterion("\"user_status\" not between", value1, value2, "userStatus");
            return (Criteria) this;
        }

        public Criteria andCreatedDateIsNull() {
            addCriterion("\"created_date\" is null");
            return (Criteria) this;
        }

        public Criteria andCreatedDateIsNotNull() {
            addCriterion("\"created_date\" is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedDateEqualTo(Date value) {
            addCriterion("\"created_date\" =", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateNotEqualTo(Date value) {
            addCriterion("\"created_date\" <>", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateGreaterThan(Date value) {
            addCriterion("\"created_date\" >", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateGreaterThanOrEqualTo(Date value) {
            addCriterion("\"created_date\" >=", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateLessThan(Date value) {
            addCriterion("\"created_date\" <", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateLessThanOrEqualTo(Date value) {
            addCriterion("\"created_date\" <=", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateIn(List<Date> values) {
            addCriterion("\"created_date\" in", values, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateNotIn(List<Date> values) {
            addCriterion("\"created_date\" not in", values, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateBetween(Date value1, Date value2) {
            addCriterion("\"created_date\" between", value1, value2, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateNotBetween(Date value1, Date value2) {
            addCriterion("\"created_date\" not between", value1, value2, "createdDate");
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