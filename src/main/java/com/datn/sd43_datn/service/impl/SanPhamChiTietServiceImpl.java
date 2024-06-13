package com.datn.sd43_datn.service.impl;

import com.datn.sd43_datn.entity.SanPhamChiTiet;
import com.datn.sd43_datn.repository.SanPhamChiTietRepository;
import com.datn.sd43_datn.service.SanPhamChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SanPhamChiTietServiceImpl implements SanPhamChiTietService {

    @Autowired
    private SanPhamChiTietRepository sanPhamChiTietRepository;

    @Override
    public List<SanPhamChiTiet> getAll() {
        return sanPhamChiTietRepository.findAll();
    }

    @Override
    public SanPhamChiTiet getById(Long id) {
        Optional<SanPhamChiTiet> spct = sanPhamChiTietRepository.findById(id);
        if(spct.isPresent()) {
            return spct.get();
        }
        return null;
    }
}
