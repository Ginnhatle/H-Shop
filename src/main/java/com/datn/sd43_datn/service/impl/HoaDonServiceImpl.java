package com.datn.sd43_datn.service.impl;

import com.datn.sd43_datn.entity.HoaDon;
import com.datn.sd43_datn.entity.HoaDonChiTiet;
import com.datn.sd43_datn.repository.HoaDonChiTietRepository;
import com.datn.sd43_datn.repository.HoaDonRepository;
import com.datn.sd43_datn.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HoaDonServiceImpl implements HoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private HoaDonChiTietRepository hoaDonCTRepository;

    @Override
    public List<HoaDon> getAll() {
        return hoaDonRepository.findAll();
    }

    @Override
    public List<HoaDonChiTiet> getAllHDCT() {
        return hoaDonCTRepository.findAll();
    }

    @Override
    public HoaDon getById(Long id) {
        Optional<HoaDon> cthd = hoaDonRepository.findById(id);
        if(cthd.isPresent()) {
            return cthd.get();
        }
        return null;
    }

    @Override
    public HoaDon update(HoaDon hoaDon) {
        Long id = hoaDon.getId();
        boolean tontai = hoaDonRepository.existsById(id);
        if(tontai) {
            return hoaDonRepository.save(hoaDon);
        }
        return null;
    }


}
