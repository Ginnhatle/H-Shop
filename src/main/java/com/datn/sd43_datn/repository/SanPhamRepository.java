package com.datn.sd43_datn.repository;

import com.datn.sd43_datn.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Long> {
}
