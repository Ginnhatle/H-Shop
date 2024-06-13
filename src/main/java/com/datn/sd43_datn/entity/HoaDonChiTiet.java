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
@Table(name = "hoa_don_chi_tiet")
public class HoaDonChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @ManyToOne
    @JoinColumn(name = "idSanPhamChiTiet")
    public SanPhamChiTiet idSanPhamChiTiet;

    @ManyToOne
    @JoinColumn(name = "idHoaDon")
    public HoaDon idHoaDon;

    @Column(name = "soLuong")
    public Double soLuong;
    @Column(name = "donGia")
    public Double donGia;
    @Column(name = "thanhTien")
    public Double thanhTien;
    @Column(name = "trangThai")
    public String trangThai;
}
