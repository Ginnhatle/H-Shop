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
            <th>Tên khách hàng</th>
            <th>Hình thức thanh toán</th>
            <th>Thành tiền</th>
            <th>Trạng thái thanh toán</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="hoadon">
        <tr>
            <td>${hoadon.id}</td>
            <td>${hoadon.idKhachHang.tenKhachHang}</td>
            <td>${hoadon.hinhThucThanhToan}</td>
            <td>${hoadon.thanhTien}</td>
            <td>${hoadon.idTrangThaiThanhToan.tenTrangThai}</td>
            <td>
                <form action="/hoa-don/chi-tiet/${hoadon.id}" method="get">
                    <button type="submit">Chi Tiết Hóa Đơn</button>
                </form>
            </td>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>