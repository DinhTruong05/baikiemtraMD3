<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Thêm sản phẩm mới</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
<div class="container mt-4">
    <h2>Thêm sản phẩm mới</h2>


    <c:if test="${not empty errorMsg}">
        <div class="alert alert-danger">${errorMsg}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/product/addProduct" method="post">
        <div class="mb-3">
            <label for="name" class="form-label">Tên sản phẩm</label>
            <input type="text" class="form-control" id="name" name="name" required
                   value="${param.name != null ? param.name : ''}"/>
        </div>
        <div class="mb-3">
            <label for="price" class="form-label">Giá</label>
            <input type="number" class="form-control" id="price" name="price" required min="0"
                   value="${param.price != null ? param.price : ''}"/>
        </div>
        <div class="mb-3">
            <label for="quantity" class="form-label">Số lượng</label>
            <input type="number" class="form-control" id="quantity" name="quantity" required min="0"
                   value="${param.quantity != null ? param.quantity : ''}"/>
        </div>
        <div class="mb-3">
            <label for="color" class="form-label">Màu sắc</label>
            <select class="form-select" id="color" name="color" required>
                <option value="" disabled ${param.color == null ? "selected" : ""}>Chọn màu sắc</option>
                <option value="Đỏ" ${param.color == "Đỏ" ? "selected" : ""}>Đỏ</option>
                <option value="Xanh" ${param.color == "Xanh" ? "selected" : ""}>Xanh</option>
                <option value="Đen" ${param.color == "Đen" ? "selected" : ""}>Đen</option>
                <option value="Trắng" ${param.color == "Trắng" ? "selected" : ""}>Trắng</option>
                <option value="Vàng" ${param.color == "Vàng" ? "selected" : ""}>Vàng</option>
            </select>
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Mô tả</label>
            <textarea class="form-control" id="description" name="description" rows="3"
                      placeholder="Nhập mô tả sản phẩm...">${param.description != null ? param.description : ''}</textarea>
        </div>
        <div class="mb-3">
            <label for="categoryId" class="form-label">Danh mục</label>
            <select class="form-select" id="categoryId" name="categoryId" required>
                <option value="" disabled ${param.categoryId == null ? "selected" : ""}>Chọn danh mục</option>
                <c:forEach var="c" items="${categoryList}">
                    <option value="${c.id}" ${param.categoryId == c.id.toString() ? "selected" : ""}>${c.name}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-success">Thêm mới</button>
        <a href="${pageContext.request.contextPath}/product/" class="btn btn-secondary">Hủy</a>
    </form>
</div>
</body>
</html>
