package ru.gb.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("products")
    public String products(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("newProduct", new Product());
        return "products";
    }

    @PostMapping("products/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @PostMapping("products/remove")
    public String removeProduct(@RequestParam String id) {
        productService.delete(id);
        return "redirect:/products";
    }
}
