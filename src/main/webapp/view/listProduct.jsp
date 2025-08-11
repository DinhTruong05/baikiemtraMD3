<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>Danh sách sản phẩm</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
<div class="container mt-4">
  <h2>Danh sách sản phẩm</h2>


  <table class="table table-bordered table-hover">
    <thead class="table-light">
    <tr>
      <th>ID</th>
      <th>Tên sản phẩm</th>
      <th>Giá</th>
      <th>Số lượng</th>
      <th>Màu sắc</th>
      <th>Danh mục</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="product" items="${productList}">
      <tr>
        <td>${product.id}</td>
        <td>${product.name}</td>
        <td>${product.price}</td>
        <td>${product.quantity}</td>
        <td>${product.color}</td>
        <td>${product.category.name}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>
