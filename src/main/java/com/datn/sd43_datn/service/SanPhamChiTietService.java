package com.datn.sd43_datn.service;

import com.datn.sd43_datn.entity.SanPhamChiTiet;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SanPhamChiTietService {
    List<SanPhamChiTiet> getSanPhamChiTiet();
}