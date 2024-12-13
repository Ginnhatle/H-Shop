package com.datn.sd43_datn.repository;

import com.datn.sd43_datn.entity.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet, Long> {

}
