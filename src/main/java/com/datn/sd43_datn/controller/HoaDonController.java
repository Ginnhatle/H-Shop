package com.datn.sd43_datn.controller;

import com.datn.sd43_datn.entity.HoaDon;
import com.datn.sd43_datn.entity.HoaDonChiTiet;
import com.datn.sd43_datn.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/hoa-don")
public class HoaDonController {
    @Autowired
    private HoaDonService hoaDonService;

    @GetMapping("index")
    public String getAll(Model model) {
        model.addAttribute("list", hoaDonService.getAll());
        return "HoaDon/Hoadon";
    }


    @GetMapping("chi-tiet/{id}")
    public String chiTietHoaDon(@PathVariable Long id, Model model) {
        HoaDon hoadon = hoaDonService.getById(id);
        double tongTienDH = 0;
        model.addAttribute("hoadon",hoadon);
        model.addAttribute("listHDCT", hoaDonService.getAllHDCT());
        model.addAttribute("tongTienDonHang", tongTienDH);
        return "HoaDon/ThongTinHD";
    }

    @PostMapping("/chi-tiet")
    public String chiTietHoaDon(@ModelAttribute("hoadon") HoaDon hd) {
        hoaDonService.update(hd);
        return "redirect:/hoa-don/index";
    }



}
