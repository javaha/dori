package com.paintee.common.repository.entity;

import com.paintee.common.object.BaseEntity;

public class PopularPainting extends BaseEntity {
    private Integer seq;

    private String paintingId;

    private Integer purchaseCount;

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getPaintingId() {
        return paintingId;
    }

    public void setPaintingId(String paintingId) {
        this.paintingId = paintingId;
    }

    public Integer getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(Integer purchaseCount) {
        this.purchaseCount = purchaseCount;
    }
}