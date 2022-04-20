package ru.gb.gbspringframework.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.gbspringframework.entity.Cart;
import ru.gb.gbspringframework.entity.Product;
import ru.gb.gbspringframework.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@Slf4j
@AllArgsConstructor
public class ProductController {
    private ProductService productService;
    private Cart cart;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("products")
    public String products(Model model,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        final var products = productService.findAll(PageRequest.of(currentPage - 1, pageSize));

        int totalPages = products.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("products", products);
        model.addAttribute("newProduct", new Product());
        model.addAttribute("cart", cart);
        return "products";
    }

    @PostMapping("products/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @PostMapping("products/remove")
    public String removeProduct(@RequestParam Long id) {
        productService.delete(id);
        return "redirect:/products";
    }

    @PostMapping("products/addToCart")
    public String addToCart(@RequestParam Long id) {
        Product product = productService.findOne(id);
        cart.save(product);
        return "redirect:/products";
    }

    @PostMapping("products/removeFromCart")
    public String removeFromCart(@RequestParam Long id) {
        cart.removeById(id);
        return "redirect:/products";
    }

    @PostMapping("products/increase")
    public String icnrease(@RequestParam Long id) {
        cart.increase(id);
        return "redirect:/products";
    }

    @PostMapping("products/decrease")
    public String decrease(@RequestParam Long id) {
        cart.decrease(id);
        return "redirect:/products";
    }
}
