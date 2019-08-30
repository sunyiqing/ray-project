package com.ray.dto;

import java.io.Serializable;

/**
 * 主键编号区间范围
 */
public class SequenceRange implements Serializable {
    /**
     * 可用起始序号
     */
    private Long startNo;
    /**
     * 可用截止序号
     */
    private Long endNo;
    /**
     * 当前游标需要，默认等于起始序号
     */
    private Long cursorNo;

    public Long getStartNo() {
        return startNo;
    }

    public void setStartNo(Long startNo) {
        this.startNo = startNo;
    }

    public Long getEndNo() {
        return endNo;
    }

    public void setEndNo(Long endNo) {
        this.endNo = endNo;
    }

    public Long getCursorNo() {
        return cursorNo;
    }

    public void setCursorNo(Long cursorNo) {
        this.cursorNo = cursorNo;
    }
}
