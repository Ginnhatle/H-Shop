package com.datn.sd43_datn.service.impl;

import com.datn.sd43_datn.entity.GioHang;
import com.datn.sd43_datn.service.GioHangService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GioHangServiceImpl implements GioHangService {
    Map<Long, GioHang> map = new HashMap<>();

    @Override
    public Collection<GioHang> getAllGioHang() {
        return map.values();
    }

    @Override
    public void clear (){
        map.clear();
    }

    @Override
    public int getSoLuong(){
        return map.values().size();
    }

    @Override
    public double getTongTien(){
        return map.values().stream().mapToDouble(item -> item.getSoLuong() * item.getGiaBan()).sum();
    }

    @Override
    public void themGioHang(GioHang gioHang) {
        GioHang gh = map.get(gioHang.getIdSanPham());
        if (gh == null) {
            map.put(gioHang.getIdSanPham(), gioHang);
        }else {
            gh.setSoLuong(gh.getSoLuong() +1);
        }
    }

    @Override
    public void xoaGioHang(Long id) {
        map.remove(id);
    }

    @Override
    public GioHang suaGioHang (Long idSanPham, int soLuong){
        GioHang gh = map.get(idSanPham);
        gh.setSoLuong(soLuong);
        return gh;
    }



}
