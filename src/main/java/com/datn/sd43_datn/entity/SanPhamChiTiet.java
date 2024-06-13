package com.datn.sd43_datn.entity;

import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "san_pham_chi_tiet")
public class SanPhamChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @ManyToOne
    @JoinColumn(name = "idSanPham")
    public SanPham idSanPham;
    @Column(name = "giaNhap")
    public Double giaNhap;
    @Column(name = "giaBan")
    public Double giaBan;
    @Column(name = "soLuong")
    public Double soLuong;
    @Column(name = "moTa")
    public String moTa;
    @Column(name = "trangThai")
    public String trangThai;
}
