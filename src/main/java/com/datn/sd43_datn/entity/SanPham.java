package com.datn.sd43_datn.entity;

import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "san_pham")
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "tenSanPham")
    public String tenSanPham;
    @Column(name = "ngayTao")
    public String ngayTao;
    @Column(name = "ngayCapNhat")
    public String ngayCapNhat;
    @Column(name = "nguoiTao")
    public String nguoiTao;
    @Column(name = "nguoiCapNhat")
    public String nguoiCapNhat;
    @Column(name = "moTa")
    public String moTa;
    @Column(name = "trangThai")
    public String trangThai;
}
