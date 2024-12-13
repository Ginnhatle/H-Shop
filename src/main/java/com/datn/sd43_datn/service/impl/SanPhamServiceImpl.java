package com.datn.sd43_datn.service.impl;

import com.datn.sd43_datn.entity.SanPham;
import com.datn.sd43_datn.repository.SanPhamRepository;
import com.datn.sd43_datn.service.SanPhamService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SanPhamServiceImpl implements SanPhamService {
    final private SanPhamRepository sanPhamRepository;
    @Override
    public List<SanPham> getAllSanPham() {
        return sanPhamRepository.findAll();
    }
}
