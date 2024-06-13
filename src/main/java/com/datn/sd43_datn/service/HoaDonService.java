package com.datn.sd43_datn.service;

import com.datn.sd43_datn.entity.HoaDon;
import com.datn.sd43_datn.entity.HoaDonChiTiet;

import java.util.List;

public interface HoaDonService {
    List<HoaDon> getAll();
    List<HoaDonChiTiet> getAllHDCT();
    HoaDon getById(Long id);
    HoaDon update(HoaDon hoaDon);
}
