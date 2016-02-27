package com.paintee.common.repository.entity;

import com.paintee.common.object.BaseEntity;

public class Reward extends BaseEntity {
    private String userId;

    private String accountNo;

    private String accountName;

    private Integer earmRequestedMoney;

    private String rewardStatus;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Integer getEarmRequestedMoney() {
        return earmRequestedMoney;
    }

    public void setEarmRequestedMoney(Integer earmRequestedMoney) {
        this.earmRequestedMoney = earmRequestedMoney;
    }

    public String getRewardStatus() {
        return rewardStatus;
    }

    public void setRewardStatus(String rewardStatus) {
        this.rewardStatus = rewardStatus;
    }
}