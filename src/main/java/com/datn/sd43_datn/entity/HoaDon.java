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
@Table(name = "hoa_don")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @ManyToOne
    @JoinColumn(name = "idKhachHang")
    public KhachHang idKhachHang;

    @ManyToOne
    @JoinColumn(name = "idTrangThaiThanhToan")
    public TrangThaiThanhToan idTrangThaiThanhToan;

    /*@Column(name = "idNhanVien")
    public String idNhanVien;
    @Column(name = "ngayTao")
    public String ngayTao;
    @Column(name = "ngayCapNhat")
    public String ngayCapNhat;
    @Column(name = "nguoiTao")
    public String nguoiTao;
    @Column(name = "nguoiCapNhat")
    public String nguoiCapNhat;*/
    @Column(name = "tongTienDonHang")
    public Double tongTienDonHang;
    @Column(name = "tienGiamGia")
    public Double tienGiamGia;
    @Column(name = "thanhTien")
    public Double thanhTien;
    @Column(name = "hinhThucThanhToan")
    public String hinhThucThanhToan;
    @Column(name = "phiVanChuyen")
    public Double phiVanChuyen;
    @Column(name = "diaChiGiaoHang")
    public String diaChiGiaoHang;
    @Column(name = "thoiGianGiaoHang")
    public String thoiGianGiaoHang;
    @Column(name = "sdtNguoiNhan")
    public String sdtNguoiNhan;
    /*@Column(name = "nguoiThanhToan")
    public String nguoiThanhToan;*/
    /*@Column(name = "trangThaiThanhToan")
    public String trangThaiThanhToan;*/
    /*@Column(name = "trangThai")
    public String trangThai;*/
}
