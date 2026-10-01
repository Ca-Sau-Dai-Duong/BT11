package com.huyanh.entity;

/** 8 trạng thái đơn hàng theo yêu cầu đề bài */
public enum OrderStatus_22145150 {
    MOI("Đơn hàng mới"),
    XAC_NHAN("Đã xác nhận"),
    CHUAN_BI("Chuẩn bị hàng"),
    VAN_CHUYEN("Vận chuyển"),
    DANG_GIAO("Đang giao hàng"),
    DA_GIAO("Đã giao"),
    HUY("Đơn hàng hủy"),
    HOAN("Đơn hàng hoàn");

    private final String label;
    OrderStatus_22145150(String label) { this.label = label; }
    public String getLabel() { return label; }
}
