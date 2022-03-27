package ru.gb.servlet;

import ru.gb.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@WebServlet(name = "ProductsServlet", urlPatterns = "/products")
public class ProductsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("products", createProducts());
        getServletContext().getRequestDispatcher("/products.jsp")
                .forward(req, resp);
    }

    private List<Product> createProducts() {
        return List.of(
                new Product("1", "Product #1", getCost(99.99)),
                new Product("2", "Product #2", getCost(49.99)),
                new Product("3", "Product #3", getCost(149.99)),
                new Product("4", "Product #4", getCost(100.49)),
                new Product("5", "Product #5", getCost(220)),
                new Product("6", "Product #6", getCost(19.49)),
                new Product("7", "Product #7", getCost(49.99)),
                new Product("8", "Product #8", getCost(99.99)),
                new Product("9", "Product #9", getCost(149.99)),
                new Product("10", "Product #10", getCost(300))
        );
    }

    private BigDecimal getCost(double rawCost) {
        return new BigDecimal(rawCost).setScale(2, RoundingMode.HALF_EVEN);
    }
}
