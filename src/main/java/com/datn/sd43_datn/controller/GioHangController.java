package com.datn.sd43_datn.controller;


import com.datn.sd43_datn.entity.GioHang;
import com.datn.sd43_datn.entity.SanPhamChiTiet;
import com.datn.sd43_datn.service.GioHangService;
import com.datn.sd43_datn.service.SanPhamChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("gio-hang")
public class GioHangController {

    @Autowired
    SanPhamChiTietService sanPhamChiTietService;
    @Autowired
    GioHangService gioHangService;

    @GetMapping("chi-tiet")
    public String chiTiet(Model model) {
        model.addAttribute("gioHang",gioHangService.getAllGioHang());
        model.addAttribute("tongTien", gioHangService.getTongTien());
        return "GioHang/Giohang";
    }

    @GetMapping("them/{id}")
    public String them(@PathVariable("id") Long id) {
        SanPhamChiTiet spct = sanPhamChiTietService.getById(id);
        if(spct != null) {
            GioHang gh = new GioHang();
            gh.setIdSanPham(spct.getId());
            gh.setTenSanPham(spct.getIdSanPham().getTenSanPham());
            gh.setGiaBan(spct.getGiaBan());
            gh.setSoLuong(1);
            gioHangService.themGioHang(gh);
        }
        return "redirect:/gio-hang/chi-tiet";
    }

    @GetMapping("xoa/{id}")
    public String xoaGioHang(@PathVariable("id") Long id) {
        gioHangService.xoaGioHang(id);
        return "redirect:/gio-hang/chi-tiet";
    }

    @GetMapping("clear")
    public String clearGioHang() {
        gioHangService.clear();
        return "redirect:/gio-hang/chi-tiet";
    }

}
