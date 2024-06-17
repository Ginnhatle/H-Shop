package com.datn.sd43_datn.repository;

import com.datn.sd43_datn.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang,Long> {
}
