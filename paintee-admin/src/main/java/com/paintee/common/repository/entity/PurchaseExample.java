package com.paintee.common.repository.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PurchaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PurchaseExample() {
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

        public Criteria andPaintingIdIsNull() {
            addCriterion("\"painting_id\" is null");
            return (Criteria) this;
        }

        public Criteria andPaintingIdIsNotNull() {
            addCriterion("\"painting_id\" is not null");
            return (Criteria) this;
        }

        public Criteria andPaintingIdEqualTo(String value) {
            addCriterion("\"painting_id\" =", value, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPaintingIdNotEqualTo(String value) {
            addCriterion("\"painting_id\" <>", value, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPaintingIdGreaterThan(String value) {
            addCriterion("\"painting_id\" >", value, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPaintingIdGreaterThanOrEqualTo(String value) {
            addCriterion("\"painting_id\" >=", value, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPaintingIdLessThan(String value) {
            addCriterion("\"painting_id\" <", value, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPaintingIdLessThanOrEqualTo(String value) {
            addCriterion("\"painting_id\" <=", value, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPaintingIdLike(String value) {
            addCriterion("\"painting_id\" like", value, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPaintingIdNotLike(String value) {
            addCriterion("\"painting_id\" not like", value, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPaintingIdIn(List<String> values) {
            addCriterion("\"painting_id\" in", values, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPaintingIdNotIn(List<String> values) {
            addCriterion("\"painting_id\" not in", values, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPaintingIdBetween(String value1, String value2) {
            addCriterion("\"painting_id\" between", value1, value2, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPaintingIdNotBetween(String value1, String value2) {
            addCriterion("\"painting_id\" not between", value1, value2, "paintingId");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateIsNull() {
            addCriterion("\"purchase_date\" is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateIsNotNull() {
            addCriterion("\"purchase_date\" is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateEqualTo(Date value) {
            addCriterion("\"purchase_date\" =", value, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateNotEqualTo(Date value) {
            addCriterion("\"purchase_date\" <>", value, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateGreaterThan(Date value) {
            addCriterion("\"purchase_date\" >", value, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateGreaterThanOrEqualTo(Date value) {
            addCriterion("\"purchase_date\" >=", value, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateLessThan(Date value) {
            addCriterion("\"purchase_date\" <", value, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateLessThanOrEqualTo(Date value) {
            addCriterion("\"purchase_date\" <=", value, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateIn(List<Date> values) {
            addCriterion("\"purchase_date\" in", values, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateNotIn(List<Date> values) {
            addCriterion("\"purchase_date\" not in", values, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateBetween(Date value1, Date value2) {
            addCriterion("\"purchase_date\" between", value1, value2, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andPurchaseDateNotBetween(Date value1, Date value2) {
            addCriterion("\"purchase_date\" not between", value1, value2, "purchaseDate");
            return (Criteria) this;
        }

        public Criteria andSentenceIsNull() {
            addCriterion("\"sentence\" is null");
            return (Criteria) this;
        }

        public Criteria andSentenceIsNotNull() {
            addCriterion("\"sentence\" is not null");
            return (Criteria) this;
        }

        public Criteria andSentenceEqualTo(String value) {
            addCriterion("\"sentence\" =", value, "sentence");
            return (Criteria) this;
        }

        public Criteria andSentenceNotEqualTo(String value) {
            addCriterion("\"sentence\" <>", value, "sentence");
            return (Criteria) this;
        }

        public Criteria andSentenceGreaterThan(String value) {
            addCriterion("\"sentence\" >", value, "sentence");
            return (Criteria) this;
        }

        public Criteria andSentenceGreaterThanOrEqualTo(String value) {
            addCriterion("\"sentence\" >=", value, "sentence");
            return (Criteria) this;
        }

        public Criteria andSentenceLessThan(String value) {
            addCriterion("\"sentence\" <", value, "sentence");
            return (Criteria) this;
        }

        public Criteria andSentenceLessThanOrEqualTo(String value) {
            addCriterion("\"sentence\" <=", value, "sentence");
            return (Criteria) this;
        }

        public Criteria andSentenceLike(String value) {
            addCriterion("\"sentence\" like", value, "sentence");
            return (Criteria) this;
        }

        public Criteria andSentenceNotLike(String value) {
            addCriterion("\"sentence\" not like", value, "sentence");
            return (Criteria) this;
        }

        public Criteria andSentenceIn(List<String> values) {
            addCriterion("\"sentence\" in", values, "sentence");
            return (Criteria) this;
        }

        public Criteria andSentenceNotIn(List<String> values) {
            addCriterion("\"sentence\" not in", values, "sentence");
            return (Criteria) this;
        }

        public Criteria andSentenceBetween(String value1, String value2) {
            addCriterion("\"sentence\" between", value1, value2, "sentence");
            return (Criteria) this;
        }

        public Criteria andSentenceNotBetween(String value1, String value2) {
            addCriterion("\"sentence\" not between", value1, value2, "sentence");
            return (Criteria) this;
        }

        public Criteria andPrivateAtIsNull() {
            addCriterion("\"private_at\" is null");
            return (Criteria) this;
        }

        public Criteria andPrivateAtIsNotNull() {
            addCriterion("\"private_at\" is not null");
            return (Criteria) this;
        }

        public Criteria andPrivateAtEqualTo(String value) {
            addCriterion("\"private_at\" =", value, "privateAt");
            return (Criteria) this;
        }

        public Criteria andPrivateAtNotEqualTo(String value) {
            addCriterion("\"private_at\" <>", value, "privateAt");
            return (Criteria) this;
        }

        public Criteria andPrivateAtGreaterThan(String value) {
            addCriterion("\"private_at\" >", value, "privateAt");
            return (Criteria) this;
        }

        public Criteria andPrivateAtGreaterThanOrEqualTo(String value) {
            addCriterion("\"private_at\" >=", value, "privateAt");
            return (Criteria) this;
        }

        public Criteria andPrivateAtLessThan(String value) {
            addCriterion("\"private_at\" <", value, "privateAt");
            return (Criteria) this;
        }

        public Criteria andPrivateAtLessThanOrEqualTo(String value) {
            addCriterion("\"private_at\" <=", value, "privateAt");
            return (Criteria) this;
        }

        public Criteria andPrivateAtLike(String value) {
            addCriterion("\"private_at\" like", value, "privateAt");
            return (Criteria) this;
        }

        public Criteria andPrivateAtNotLike(String value) {
            addCriterion("\"private_at\" not like", value, "privateAt");
            return (Criteria) this;
        }

        public Criteria andPrivateAtIn(List<String> values) {
            addCriterion("\"private_at\" in", values, "privateAt");
            return (Criteria) this;
        }

        public Criteria andPrivateAtNotIn(List<String> values) {
            addCriterion("\"private_at\" not in", values, "privateAt");
            return (Criteria) this;
        }

        public Criteria andPrivateAtBetween(String value1, String value2) {
            addCriterion("\"private_at\" between", value1, value2, "privateAt");
            return (Criteria) this;
        }

        public Criteria andPrivateAtNotBetween(String value1, String value2) {
            addCriterion("\"private_at\" not between", value1, value2, "privateAt");
            return (Criteria) this;
        }

        public Criteria andReceiverAddrIsNull() {
            addCriterion("\"receiver_addr\" is null");
            return (Criteria) this;
        }

        public Criteria andReceiverAddrIsNotNull() {
            addCriterion("\"receiver_addr\" is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverAddrEqualTo(String value) {
            addCriterion("\"receiver_addr\" =", value, "receiverAddr");
            return (Criteria) this;
        }

        public Criteria andReceiverAddrNotEqualTo(String value) {
            addCriterion("\"receiver_addr\" <>", value, "receiverAddr");
            return (Criteria) this;
        }

        public Criteria andReceiverAddrGreaterThan(String value) {
            addCriterion("\"receiver_addr\" >", value, "receiverAddr");
            return (Criteria) this;
        }

        public Criteria andReceiverAddrGreaterThanOrEqualTo(String value) {
            addCriterion("\"receiver_addr\" >=", value, "receiverAddr");
            return (Criteria) this;
        }

        public Criteria andReceiverAddrLessThan(String value) {
            addCriterion("\"receiver_addr\" <", value, "receiverAddr");
            return (Criteria) this;
        }

        public Criteria andReceiverAddrLessThanOrEqualTo(String value) {
            addCriterion("\"receiver_addr\" <=", value, "receiverAddr");
            return (Criteria) this;
        }

        public Criteria andReceiverAddrLike(String value) {
            addCriterion("\"receiver_addr\" like", value, "receiverAddr");
            return (Criteria) this;
        }

        public Criteria andReceiverAddrNotLike(String value) {
            addCriterion("\"receiver_addr\" not like", value, "receiverAddr");
            return (Criteria) this;
        }

        public Criteria andReceiverAddrIn(List<String> values) {
            addCriterion("\"receiver_addr\" in", values, "receiverAddr");
            return (Criteria) this;
        }

        public Criteria andReceiverAddrNotIn(List<String> values) {
            addCriterion("\"receiver_addr\" not in", values, "receiverAddr");
            return (Criteria) this;
        }

        public Criteria andReceiverAddrBetween(String value1, String value2) {
            addCriterion("\"receiver_addr\" between", value1, value2, "receiverAddr");
            return (Criteria) this;
        }

        public Criteria andReceiverAddrNotBetween(String value1, String value2) {
            addCriterion("\"receiver_addr\" not between", value1, value2, "receiverAddr");
            return (Criteria) this;
        }

        public Criteria andSenderAddrIsNull() {
            addCriterion("\"sender_addr\" is null");
            return (Criteria) this;
        }

        public Criteria andSenderAddrIsNotNull() {
            addCriterion("\"sender_addr\" is not null");
            return (Criteria) this;
        }

        public Criteria andSenderAddrEqualTo(String value) {
            addCriterion("\"sender_addr\" =", value, "senderAddr");
            return (Criteria) this;
        }

        public Criteria andSenderAddrNotEqualTo(String value) {
            addCriterion("\"sender_addr\" <>", value, "senderAddr");
            return (Criteria) this;
        }

        public Criteria andSenderAddrGreaterThan(String value) {
            addCriterion("\"sender_addr\" >", value, "senderAddr");
            return (Criteria) this;
        }

        public Criteria andSenderAddrGreaterThanOrEqualTo(String value) {
            addCriterion("\"sender_addr\" >=", value, "senderAddr");
            return (Criteria) this;
        }

        public Criteria andSenderAddrLessThan(String value) {
            addCriterion("\"sender_addr\" <", value, "senderAddr");
            return (Criteria) this;
        }

        public Criteria andSenderAddrLessThanOrEqualTo(String value) {
            addCriterion("\"sender_addr\" <=", value, "senderAddr");
            return (Criteria) this;
        }

        public Criteria andSenderAddrLike(String value) {
            addCriterion("\"sender_addr\" like", value, "senderAddr");
            return (Criteria) this;
        }

        public Criteria andSenderAddrNotLike(String value) {
            addCriterion("\"sender_addr\" not like", value, "senderAddr");
            return (Criteria) this;
        }

        public Criteria andSenderAddrIn(List<String> values) {
            addCriterion("\"sender_addr\" in", values, "senderAddr");
            return (Criteria) this;
        }

        public Criteria andSenderAddrNotIn(List<String> values) {
            addCriterion("\"sender_addr\" not in", values, "senderAddr");
            return (Criteria) this;
        }

        public Criteria andSenderAddrBetween(String value1, String value2) {
            addCriterion("\"sender_addr\" between", value1, value2, "senderAddr");
            return (Criteria) this;
        }

        public Criteria andSenderAddrNotBetween(String value1, String value2) {
            addCriterion("\"sender_addr\" not between", value1, value2, "senderAddr");
            return (Criteria) this;
        }

        public Criteria andReceiverNameIsNull() {
            addCriterion("\"receiver_name\" is null");
            return (Criteria) this;
        }

        public Criteria andReceiverNameIsNotNull() {
            addCriterion("\"receiver_name\" is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverNameEqualTo(String value) {
            addCriterion("\"receiver_name\" =", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameNotEqualTo(String value) {
            addCriterion("\"receiver_name\" <>", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameGreaterThan(String value) {
            addCriterion("\"receiver_name\" >", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameGreaterThanOrEqualTo(String value) {
            addCriterion("\"receiver_name\" >=", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameLessThan(String value) {
            addCriterion("\"receiver_name\" <", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameLessThanOrEqualTo(String value) {
            addCriterion("\"receiver_name\" <=", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameLike(String value) {
            addCriterion("\"receiver_name\" like", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameNotLike(String value) {
            addCriterion("\"receiver_name\" not like", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameIn(List<String> values) {
            addCriterion("\"receiver_name\" in", values, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameNotIn(List<String> values) {
            addCriterion("\"receiver_name\" not in", values, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameBetween(String value1, String value2) {
            addCriterion("\"receiver_name\" between", value1, value2, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameNotBetween(String value1, String value2) {
            addCriterion("\"receiver_name\" not between", value1, value2, "receiverName");
            return (Criteria) this;
        }

        public Criteria andSenderNameIsNull() {
            addCriterion("\"sender_name\" is null");
            return (Criteria) this;
        }

        public Criteria andSenderNameIsNotNull() {
            addCriterion("\"sender_name\" is not null");
            return (Criteria) this;
        }

        public Criteria andSenderNameEqualTo(String value) {
            addCriterion("\"sender_name\" =", value, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderNameNotEqualTo(String value) {
            addCriterion("\"sender_name\" <>", value, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderNameGreaterThan(String value) {
            addCriterion("\"sender_name\" >", value, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderNameGreaterThanOrEqualTo(String value) {
            addCriterion("\"sender_name\" >=", value, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderNameLessThan(String value) {
            addCriterion("\"sender_name\" <", value, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderNameLessThanOrEqualTo(String value) {
            addCriterion("\"sender_name\" <=", value, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderNameLike(String value) {
            addCriterion("\"sender_name\" like", value, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderNameNotLike(String value) {
            addCriterion("\"sender_name\" not like", value, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderNameIn(List<String> values) {
            addCriterion("\"sender_name\" in", values, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderNameNotIn(List<String> values) {
            addCriterion("\"sender_name\" not in", values, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderNameBetween(String value1, String value2) {
            addCriterion("\"sender_name\" between", value1, value2, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderNameNotBetween(String value1, String value2) {
            addCriterion("\"sender_name\" not between", value1, value2, "senderName");
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

        public Criteria andPurchaseStatusIsNull() {
            addCriterion("\"purchase_status\" is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusIsNotNull() {
            addCriterion("\"purchase_status\" is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusEqualTo(String value) {
            addCriterion("\"purchase_status\" =", value, "purchaseStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusNotEqualTo(String value) {
            addCriterion("\"purchase_status\" <>", value, "purchaseStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusGreaterThan(String value) {
            addCriterion("\"purchase_status\" >", value, "purchaseStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusGreaterThanOrEqualTo(String value) {
            addCriterion("\"purchase_status\" >=", value, "purchaseStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusLessThan(String value) {
            addCriterion("\"purchase_status\" <", value, "purchaseStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusLessThanOrEqualTo(String value) {
            addCriterion("\"purchase_status\" <=", value, "purchaseStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusLike(String value) {
            addCriterion("\"purchase_status\" like", value, "purchaseStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusNotLike(String value) {
            addCriterion("\"purchase_status\" not like", value, "purchaseStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusIn(List<String> values) {
            addCriterion("\"purchase_status\" in", values, "purchaseStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusNotIn(List<String> values) {
            addCriterion("\"purchase_status\" not in", values, "purchaseStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusBetween(String value1, String value2) {
            addCriterion("\"purchase_status\" between", value1, value2, "purchaseStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusNotBetween(String value1, String value2) {
            addCriterion("\"purchase_status\" not between", value1, value2, "purchaseStatus");
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