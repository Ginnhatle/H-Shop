package com.datn.sd43_datn.controller;

import com.datn.sd43_datn.entity.SanPhamChiTiet;
import com.datn.sd43_datn.service.SanPhamChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("san-pham-chi-tiet")
public class SanPhamChiTietController {
    @Autowired
    private SanPhamChiTietService sanPhamChiTietService;

    @GetMapping("index")
    public String getAll(@ModelAttribute("spct")
                         SanPhamChiTiet spct,
                         Model model) {
        model.addAttribute("list", sanPhamChiTietService.getAll());
        return "SanPhamChiTiet/Spct_info";
    }
}
