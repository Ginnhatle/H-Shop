package com.datn.sd43_datn.service.impl;

import com.datn.sd43_datn.dto.HoaDonChiTietDto;
import com.datn.sd43_datn.entity.*;
import com.datn.sd43_datn.repository.*;
import com.datn.sd43_datn.request.HoaDonRequest;
import com.datn.sd43_datn.request.TaoDonHangRequest;
import com.datn.sd43_datn.request.UpdateDonHangRequest;
import com.datn.sd43_datn.service.HoaDonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class HoaDonServiceImpl implements HoaDonService {
    final private HoaDonRepository hoaDonRepository;
    final private HoaDonChiTietRepository hoaDonChiTietRepository;
    final private TrangThaiDonHangRepository trangThaiDonRepository;
    final private NhanVienRepository nhanVienRepository;
    final private KhachHangRepository khachHangRepository;
    final private GiamGiaRepository giamGiaRepository;
    private final SanPhamChiTietRepository sanPhamChiTietRepository;

    @Override
    public List<HoaDonRequest> getHoaDonRequests() {
        List<HoaDon> hoaDons = hoaDonRepository.findAll();
        List<HoaDonRequest> hoaDonRequests = new ArrayList<>();
        for(HoaDon hoaDon : hoaDons){
            long tongSl = 0,tongTien = 0;
            for(HoaDonChiTiet hoaDonChiTiet : hoaDonChiTietRepository.findHoaDonChiTietsByHoaDon(hoaDon)){
                tongSl += hoaDonChiTiet.getSoLuong();
                tongTien += hoaDonChiTiet.getThanhTien();
            }
            HoaDonRequest hoaDonRequest = HoaDonRequest.builder()
                    .ID(hoaDon.getID())
                    .tenKH(hoaDon.getKhachHang().getTenKhachHang())
                    .tongSp(tongSl)
                    .tongTien(tongTien)
                    .ngayTao(hoaDon.getNgayTao())
                    .trangThaiDon(hoaDon.getTrangThaiDon().getTenTrangThai())
                    .build();
            hoaDonRequests.add(hoaDonRequest);
        }
        return hoaDonRequests;
    }

    @Override
    public HoaDonChiTietDto getHoaDonDetail(long hoaDonId) {
        HoaDon hoaDon = hoaDonRepository.findById(hoaDonId).get();

        String tenGiamGia = "Không sử dụng";
        float giaTriGiamGia = 0;
//        if(hoaDon.getGiamGia() != null){
//            tenGiamGia = hoaDon.getGiamGia().getTenGiamGia();
//            giaTriGiamGia = hoaDon.getGiamGia().getGiaTriGiamGia();
//        }
        List<TrangThaiHoaDon> trangThaiDonList = new ArrayList<>();
        long idTrangThai = hoaDon.getTrangThaiDon().getID();

        List<String> idTrangThaiList = List.of(hoaDon.getIdStatus().split(","));
        for(String id : idTrangThaiList){
            TrangThaiHoaDon trangThaiDon = trangThaiDonRepository.findById(Long.parseLong(id)).get();
            trangThaiDonList.add(trangThaiDon);
        }
        HoaDonChiTietDto hoaDonDetailDTO = HoaDonChiTietDto.builder()
                .id(hoaDonId)
                .tenKH(hoaDon.getKhachHang().getTenKhachHang())
                .sdt(hoaDon.getSdtNguoiNhan())
                .tenNguoiNhan(hoaDon.getKhachHang().getTenKhachHang())
                .trangThai(hoaDon.getTrangThaiDon().getTenTrangThai())
                .trangThaiDonList(trangThaiDonList)
                .trangThaiDon(trangThaiDonRepository.findById(idTrangThai).get())
                .hoaDonChiTietList(hoaDonChiTietRepository.findHoaDonChiTietsByHoaDon(hoaDon))
                .tenGiamGia(tenGiamGia)
                .giaTriGiamGia(giaTriGiamGia)
                .giamGia(String.valueOf(hoaDon.getTienGiamGia()))
                .tongTienHang(String.valueOf(hoaDon.getTongTienDonHang()))
                .phiVanChuyen(String.valueOf(hoaDon.getPhiVanChuyen()))
                .tongTien(String.valueOf(hoaDon.getThanhTien()))
                .build();
        return hoaDonDetailDTO;
    }

    @Override
    public HoaDonChiTietDto updateTrangThai(long hoaDonId) {

        HoaDon hoaDon = hoaDonRepository.findById(hoaDonId).get();
        List<TrangThaiHoaDon> trangThaiDonList = new ArrayList<>();
        long idTrangThai = hoaDon.getTrangThaiDon().getID();
        String status = hoaDon.getIdStatus();
        if(idTrangThai != 5 && idTrangThai != 6 && idTrangThai != 7) {
            idTrangThai += 1;
            status = status + "," + String.valueOf(idTrangThai);
        }
        List<String> idTrangThaiList = List.of(status.split(","));
        for(String id : idTrangThaiList){
            TrangThaiHoaDon trangThaiDon = trangThaiDonRepository.findById(Long.parseLong(id)).get();
            trangThaiDonList.add(trangThaiDon);
        }
        hoaDon.setTrangThaiDon(trangThaiDonRepository.findById(idTrangThai).get());
        hoaDon.setIdStatus(status);
        hoaDonRepository.save(hoaDon);
        String tenGiamGia = "Không sử dụng";
        float giaTriGiamGia = 0;
//        if(hoaDon.getGiamGia() != null){
//            tenGiamGia = hoaDon.getGiamGia().getTenGiamGia();
//            giaTriGiamGia = hoaDon.getGiamGia().getGiaTriGiamGia();
//        }
        HoaDonChiTietDto hoaDonDetailDTO = HoaDonChiTietDto.builder()
                .id(hoaDonId)
                .tenKH(hoaDon.getKhachHang().getTenKhachHang())
                .sdt(hoaDon.getSdtNguoiNhan())
                .tenNguoiNhan(hoaDon.getKhachHang().getTenKhachHang())
                .trangThai(hoaDon.getTrangThaiDon().getTenTrangThai())
                .trangThaiDonList(trangThaiDonList)
                .trangThaiDon(trangThaiDonRepository.findById(idTrangThai).get())
                .hoaDonChiTietList(hoaDonChiTietRepository.findHoaDonChiTietsByHoaDon(hoaDon))
                .tenGiamGia(tenGiamGia)
                .giaTriGiamGia(giaTriGiamGia)
                .giamGia(String.valueOf(hoaDon.getTienGiamGia()))
                .tongTienHang(String.valueOf(hoaDon.getTongTienDonHang()))
                .phiVanChuyen(String.valueOf(hoaDon.getPhiVanChuyen()))
                .tongTien(String.valueOf(hoaDon.getThanhTien()))
                .build();
        return hoaDonDetailDTO;
    }

    @Override
    public HoaDonChiTietDto huyTrangThai(long hoaDonId) {
        HoaDon hoaDon = hoaDonRepository.findById(hoaDonId).get();
        List<TrangThaiHoaDon> trangThaiDonList = new ArrayList<>();

        long idTrangThai = hoaDon.getTrangThaiDon().getID();
        String status = hoaDon.getIdStatus();
        if(idTrangThai != 4 && idTrangThai != 5 && idTrangThai != 6 && idTrangThai != 7) {
            idTrangThai = 6;
            status = status + "," + String.valueOf(idTrangThai);
        } else if (idTrangThai == 5 && idTrangThai != 6 && idTrangThai != 7) {
            idTrangThai = 7;
            status = status + "," + String.valueOf(idTrangThai);
        }

        List<String> idTrangThaiList = List.of(status.split(","));
        for(String id : idTrangThaiList){
            TrangThaiHoaDon trangThaiDon = trangThaiDonRepository.findById(Long.parseLong(id)).get();
            trangThaiDonList.add(trangThaiDon);
        }
        hoaDon.setTrangThaiDon(trangThaiDonRepository.findById(idTrangThai).get());
        hoaDon.setIdStatus(status);
        hoaDonRepository.save(hoaDon);
        String tenGiamGia = "Không sử dụng";
        float giaTriGiamGia = 0;
//        if(hoaDon.getGiamGia() != null){
//            tenGiamGia = hoaDon.getGiamGia().getTenGiamGia();
//            giaTriGiamGia = hoaDon.getGiamGia().getGiaTriGiamGia();
//        }
        HoaDonChiTietDto hoaDonDetailDTO = HoaDonChiTietDto.builder()
                .id(hoaDonId)
                .tenKH(hoaDon.getKhachHang().getTenKhachHang())
                .sdt(hoaDon.getSdtNguoiNhan())
                .tenNguoiNhan(hoaDon.getKhachHang().getTenKhachHang())
                .trangThai(hoaDon.getTrangThaiDon().getTenTrangThai())
                .trangThaiDonList(trangThaiDonList)
                .trangThaiDon(trangThaiDonRepository.findById(idTrangThai).get())
                .hoaDonChiTietList(hoaDonChiTietRepository.findHoaDonChiTietsByHoaDon(hoaDon))
                .tenGiamGia(tenGiamGia)
                .giaTriGiamGia(giaTriGiamGia)
                .giamGia(String.valueOf(hoaDon.getTienGiamGia()))
                .tongTienHang(String.valueOf(hoaDon.getTongTienDonHang()))
                .phiVanChuyen(String.valueOf(hoaDon.getPhiVanChuyen()))
                .tongTien(String.valueOf(hoaDon.getThanhTien()))
                .build();
        return hoaDonDetailDTO;
    }

    @Override
    public List<HoaDonRequest> filterHoaDonRequest(String search, String status, String batDau, String ketThuc) throws ParseException {
        List<HoaDon> hoaDons = hoaDonRepository.findAll();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date bd = null, kt = null;
        if (!batDau.isEmpty() && !ketThuc.isEmpty()) {
            bd = formatter.parse(batDau);
            kt = formatter.parse(ketThuc);
        }

        List<HoaDonRequest> hoaDonRequests = new ArrayList<>();
        for (HoaDon hoaDon : hoaDons) {
            boolean match = true;

            if (search != null && !search.isEmpty()) {
                if (!hoaDon.getKhachHang().getTenKhachHang().contains(search)) {
                    match = false;
                }
            }

            if (status != null && !status.isEmpty()) {
                if (!(hoaDon.getTrangThaiDon().getID() == Long.valueOf(status) || Long.valueOf(status) == 0)) {
                    match = false;
                }
            }

            if (bd != null && kt != null) {
                if (!(hoaDon.getNgayTao().compareTo(bd) > 0 && hoaDon.getNgayTao().compareTo(kt) < 0)) {
                    match = false;
                }
            }

            if (match) {
                long tongSl = 0, tongTien = 0;
                for (HoaDonChiTiet hoaDonChiTiet : hoaDonChiTietRepository.findHoaDonChiTietsByHoaDon(hoaDon)) {
                    tongSl += hoaDonChiTiet.getSoLuong();
                    tongTien += hoaDonChiTiet.getThanhTien();
                }
                HoaDonRequest hoaDonRequest = HoaDonRequest.builder()
                        .ID(hoaDon.getID())
                        .tenKH(hoaDon.getKhachHang().getTenKhachHang())
                        .tongSp(tongSl)
                        .tongTien(tongTien)
                        .ngayTao(hoaDon.getNgayTao())
                        .trangThaiDon(hoaDon.getTrangThaiDon().getTenTrangThai())
                        .build();
                hoaDonRequests.add(hoaDonRequest);
            }
        }
        return hoaDonRequests;
    }


    @Override
    public boolean addHoaDon(TaoDonHangRequest createDonHangRequest) {
        NhanVien nhanVien = nhanVienRepository.findById(1L).get();
        KhachHang khachHang = KhachHang.builder()
                .tenKhachHang(createDonHangRequest.getName())
                .sdt(createDonHangRequest.getPhone())
                .ngayTao(new Date())
                .trangThai(true)
                .build();
        khachHangRepository.save(khachHang);

//        GiamGia giamGia = giamGiaRepository.findGiamGiaByTenGiamGia(createDonHangRequest.getDiscount_name());
//        Date ngayHienTai = new Date();
//        boolean check = true;
//        if(giamGia != null){
//            if(ngayHienTai.compareTo(giamGia.getNgayBatDau()) < 0 || ngayHienTai.compareTo(giamGia.getNgayKetThuc()) > 0){
//                giamGia.setGiaTriGiamGia(0F);
//                check = false;
//            } else if (giamGia.getSoLuong() == 0) {
//                giamGia.setGiaTriGiamGia(0F);
//                check = false;
//            }
//        }

        List<HoaDonChiTiet> hoaDonChiTietList = new ArrayList<>();
        String strippedInput = createDonHangRequest.getList_product().substring(1, createDonHangRequest.getList_product().length() - 1);
        String[] pairs = strippedInput.split(",");
        for (String pair : pairs) {
            pair = pair.replace("\"", "");
            String[] numbers = pair.split(":");
            String id = numbers[0];
            String sl = numbers[1];

            SanPhamChiTiet  sanPhamChiTiet = sanPhamChiTietRepository.findById(Long.valueOf(id)).get();
            HoaDonChiTiet hoaDonChiTiet = HoaDonChiTiet.builder()
                    .sanPhamChiTiet(sanPhamChiTiet)
                    .soLuong(Long.valueOf(sl))
                    .donGia(Long.valueOf(sanPhamChiTiet.getGiaBan()))
                    .thanhTien(Long.valueOf(sl) * Long.valueOf(sanPhamChiTiet.getGiaBan()))
                    .trangThai(true)
                    .build();
            hoaDonChiTietList.add(hoaDonChiTiet);
        }
        long tongTienDonHang = 0;
        float tienGiamGia = 0,thanhTien = 0;;
        for(HoaDonChiTiet hd : hoaDonChiTietList){
            tongTienDonHang += hd.getThanhTien();
        }
//        if(giamGia != null){
//            tienGiamGia =(float) tongTienDonHang*giamGia.getGiaTriGiamGia()/100;
//        }

        thanhTien = tongTienDonHang - tienGiamGia;
        String hinhThuc = "";
        if(createDonHangRequest.getType().equals("2")){
            hinhThuc = "Chuyển khoản";
        }else{
            hinhThuc = "Tiền mặt";
        }
        TrangThaiHoaDon trangThaiDon = trangThaiDonRepository.findById(5L).get();
        HoaDon hoaDon = HoaDon.builder()
                .khachHang(khachHang)
                .ngayTao(new Date())
                .nguoiTao(nhanVien.getHoTen())
                .tongTienDonHang((float) tongTienDonHang)
                .tienGiamGia(tienGiamGia)
                .thanhTien((thanhTien))
                .hinhThucThanhToan(hinhThuc)
                .phiVanChuyen(0F)
                .diaCHiGiaoHang(null)
                .thoiGianGiaoHang(null)
                .sdtNguoiNhan(khachHang.getSdt())
                .trangThaiDon(trangThaiDon)
                .idStatus("1,5")
                .build();
//        if(check && giamGia != null){
//            hoaDon.setGiamGia(giamGia);
//            giamGia.setSoLuong(giamGia.getSoLuong()-1);
//        }else {
//            hoaDon.setGiamGia(null);
//        }
        hoaDonRepository.save(hoaDon);
        for(HoaDonChiTiet hoaDonChiTiet : hoaDonChiTietList){
            hoaDonChiTiet.setHoaDon(hoaDon);
            hoaDonChiTietRepository.save(hoaDonChiTiet);
        }
        return true;
    }

    @Override
    public boolean updateHoaDon(UpdateDonHangRequest updateDonHangRequest, long hoaDonId) {
        HoaDon hoaDon = hoaDonRepository.findById(hoaDonId).get();

        hoaDonChiTietRepository.deleteAll(hoaDonChiTietRepository.findHoaDonChiTietsByHoaDon(hoaDon));

        List<HoaDonChiTiet> hoaDonChiTietList = new ArrayList<>();
        String strippedInput = updateDonHangRequest.getList_product().substring(1, updateDonHangRequest.getList_product().length() - 1);
        String[] pairs = strippedInput.split(",");
        for (String pair : pairs) {
            pair = pair.replace("\"", "");
            String[] numbers = pair.split(":");
            String id = numbers[0];
            String sl = numbers[1];

            SanPhamChiTiet  sanPhamChiTiet = sanPhamChiTietRepository.findById(Long.valueOf(id)).get();
            HoaDonChiTiet hoaDonChiTiet = HoaDonChiTiet.builder()
                    .hoaDon(hoaDon)
                    .sanPhamChiTiet(sanPhamChiTiet)
                    .soLuong(Long.valueOf(sl))
                    .donGia(Long.valueOf(sanPhamChiTiet.getGiaBan()))
                    .thanhTien(Long.valueOf(sl) * Long.valueOf(sanPhamChiTiet.getGiaBan()))
                    .trangThai(true)
                    .build();
            hoaDonChiTietRepository.save(hoaDonChiTiet);
            hoaDonChiTietList.add(hoaDonChiTiet);
        }
        long tongTienDonHang = 0;
        float tienGiamGia = 0,thanhTien = 0;;
        for(HoaDonChiTiet hd : hoaDonChiTietList){
            tongTienDonHang += hd.getThanhTien();
        }
//        if(hoaDon.getGiamGia() != null){
//            tienGiamGia =(float) tongTienDonHang*hoaDon.getGiamGia().getGiaTriGiamGia()/100;
//        }
        thanhTien = tongTienDonHang - tienGiamGia;
        hoaDon.setTongTienDonHang((float) tongTienDonHang);
        hoaDon.setTienGiamGia(tienGiamGia);
        hoaDon.setThanhTien(thanhTien);
        hoaDon.setNgayCapNhat(new Date());
        hoaDonRepository.save(hoaDon);
        return true;
    }

}
