package org.example.managementproduct.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.managementproduct.entity.Category;
import org.example.managementproduct.entity.Product;
import org.example.managementproduct.model.productModel;

import java.io.IOException;

@WebServlet(name = "ProductServlet", urlPatterns = {"/product/*"})

public class ProductServlet extends HttpServlet {
    productModel productModel;

    @Override
    public void init() throws ServletException {
        productModel = new productModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getPathInfo();

        if (uri == null)
            uri = "";

        switch (uri) {
            case "/":
            case "":
                showProductManager(req, resp);
                break;
            case "/listProduct":
                showListProduct(req, resp);
                break;
            case "/delete":
                doDeleteProduct(req, resp);
                break;
            case "/addProduct":
                showAddProductPage(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getPathInfo();

        if (uri == null)
            uri = "";

        switch (uri) {
            case "/addProduct":
                addProduct(req,resp);
                break;

        }
    }

    public void showProductManager(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("productList", productModel.getAllProduct());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/view/productmanager.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void showListProduct(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setAttribute("productList", productModel.getAllProduct());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/view/listProduct.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void doDeleteProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");

        try {
            productModel.deleteProduct(Integer.parseInt(id));
            resp.sendRedirect(req.getContextPath() + "/product/");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void showAddProductPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("categoryList", productModel.getAllCategory());

        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/addProduct.jsp");
        dispatcher.forward(req, resp);
    }
    public void addProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String name = req.getParameter("name");
            String priceStr = req.getParameter("price");
            String quantityStr = req.getParameter("quantity");
            String color = req.getParameter("color");
            String categoryIdStr = req.getParameter("categoryId");

            String errorMsg = null;
            if (name == null || name.trim().isEmpty()) {
                errorMsg = "Tên sản phẩm không được để trống";
            }

            long price = 0;
            try {
                price = Long.parseLong(priceStr);
                if (price <= 10000000) {
                    errorMsg = "Giá phải lớn hơn 10,000,000 VND";
                }
            } catch (NumberFormatException e) {
                errorMsg = "Giá không hợp lệ";
            }

            int quantity = 0;
            try {
                quantity = Integer.parseInt(quantityStr);
                if (quantity <= 0) {
                    errorMsg = "Số lượng phải là số nguyên dương";
                }
            } catch (NumberFormatException e) {
                errorMsg = "Số lượng không hợp lệ";
            }

            int categoryId = 0;
            try {
                categoryId = Integer.parseInt(categoryIdStr);
            } catch (NumberFormatException e) {
                errorMsg = "Danh mục không hợp lệ";
            }
            if (errorMsg != null) {
                req.setAttribute("errorMsg", errorMsg);
                req.getRequestDispatcher("/view/productadd.jsp").forward(req, resp);
                return;
            }


            Category category = new Category();
            category.setId(categoryId);

            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setQuantity(quantity);
            product.setColor(color);
            product.setCategory(category);

            productModel.insertProduct(product);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/product/");

    }

}
