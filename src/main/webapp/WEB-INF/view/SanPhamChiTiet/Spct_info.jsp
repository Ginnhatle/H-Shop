<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>1</title>
</head>
<body>
<div class="container ">
    <table class="table table-success table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Tên Sản Phẩm</th>
            <th>Giá Bán</th>
            <th>Giá Nhập</th>
            <th>Số Lượng</th>
            <th>Mô Tả</th>
            <th>Trạng Thái</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="spct">
            <tr>
                <td>${spct.id}</td>
                <td>${spct.idSanPham.tenSanPham}</td>
                <td>${spct.giaBan}</td>
                <td>${spct.giaNhap}</td>
                <td>${spct.soLuong}</td>
                <td>${spct.moTa}</td>
                <td>${spct.trangThai}</td>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>