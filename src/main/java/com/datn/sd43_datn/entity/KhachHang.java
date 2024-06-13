package com.datn.sd43_datn.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "khach_hang")
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "tenKhachHang")
    public String tenKhachHang;
    @Column(name = "sdt")
    public String sdt;
    @Column(name = "gioiTinh")
    public Boolean gioiTinh;
    @Column(name = "email")
    public String email;
    @Column(name = "matKhau")
    public String matKhau;
    @Column(name = "diaChi")
    public String diaChi;
    @Column(name = "ngayTao")
    public String ngayTao;
    @Column(name = "anh")
    public String anh;
}
