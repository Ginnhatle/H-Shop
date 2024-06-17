package com.datn.sd43_datn.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GioHang {
    private Long idSanPham;
    private String tenSanPham;
    private double giaBan;
    private int soLuong =1;
}
