package com.datn.sd43_datn.service;

import com.datn.sd43_datn.entity.GioHang;

import java.util.Collection;
import java.util.List;

public interface GioHangService {
    Collection<GioHang> getAllGioHang();
    void themGioHang(GioHang gioHang);
    void xoaGioHang(Long id);
    GioHang suaGioHang(Long idPhamPham, int soLuong);
    void clear();
    double getTongTien();
    int getSoLuong();

}
