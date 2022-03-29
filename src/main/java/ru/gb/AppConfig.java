package ru.gb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.gb.cart.Cart;
import ru.gb.product.ProductRepository;

@Configuration
@ComponentScan("ru.gb")
public class AppConfig {
    @Bean
    public Cart cart(ProductRepository repository) {
        Cart cart = new Cart();
        cart.setProductRepository(repository);
        return cart;
    }
}
