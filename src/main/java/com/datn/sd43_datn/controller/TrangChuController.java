package com.datn.sd43_datn.controller;

import com.datn.sd43_datn.entity.HoaDon;
import com.datn.sd43_datn.entity.SanPhamChiTiet;
import com.datn.sd43_datn.service.SanPhamChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("trang-chu")
public class TrangChuController {
    @Autowired
    private SanPhamChiTietService sanPhamChiTietService;

    @GetMapping("quan-li")
    public String getAll(Model model) {
        model.addAttribute("list", sanPhamChiTietService.getAll());
        return "TrangChu";
    }


}
