package com.paintee.common.repository.entity;

import com.paintee.common.object.BaseEntity;
import java.util.Date;

public class User extends BaseEntity {
    private String userId;

    private String password;

    private String email;

    private String name;

    private String introduce;

    private String address;

    private String location;

    private Integer uploadCnt;

    private Integer postCnt;

    private Integer earnTotalMoney;

    private Integer earnRewordMoney;

    private String resentSendAddr;

    private String resentSendName;

    private Integer point;

    private String userStatus;

    private Date createdDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getUploadCnt() {
        return uploadCnt;
    }

    public void setUploadCnt(Integer uploadCnt) {
        this.uploadCnt = uploadCnt;
    }

    public Integer getPostCnt() {
        return postCnt;
    }

    public void setPostCnt(Integer postCnt) {
        this.postCnt = postCnt;
    }

    public Integer getEarnTotalMoney() {
        return earnTotalMoney;
    }

    public void setEarnTotalMoney(Integer earnTotalMoney) {
        this.earnTotalMoney = earnTotalMoney;
    }

    public Integer getEarnRewordMoney() {
        return earnRewordMoney;
    }

    public void setEarnRewordMoney(Integer earnRewordMoney) {
        this.earnRewordMoney = earnRewordMoney;
    }

    public String getResentSendAddr() {
        return resentSendAddr;
    }

    public void setResentSendAddr(String resentSendAddr) {
        this.resentSendAddr = resentSendAddr;
    }

    public String getResentSendName() {
        return resentSendName;
    }

    public void setResentSendName(String resentSendName) {
        this.resentSendName = resentSendName;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}