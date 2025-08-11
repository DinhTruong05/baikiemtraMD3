<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Quản lý Sản phẩm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
<div class="container mt-4">
    <h2>Danh sách Sản phẩm</h2>
    <div class="mb-3">
        <a href="/product/addProduct" class="btn btn-primary">Thêm sản phẩm mới</a>
    </div>
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Tên sản phẩm</th>
            <th>Giá</th>
            <th>Số lượng</th>
            <th>Màu sắc</th>
            <th>Danh mục</th>
            <th>Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${productList}" >
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.quantity}</td>
                <td>${product.color}</td>
                <td>${product.category.name}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/product/edit?id=${product.id}" class="btn btn-warning btn-sm">Sửa</a>
                    <a href="${pageContext.request.contextPath}/product/delete?id=${product.id}" class="btn btn-danger btn-sm"
                       onclick="return confirm('Bạn có chắc muốn xóa sản phẩm này không?');">Xóa</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
