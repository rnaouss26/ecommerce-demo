package com.cnam.ecommerce;

import com.cnam.ecommerce.model.Product;
import com.cnam.ecommerce.repo.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataSeeder implements CommandLineRunner {

    private final ProductRepository repo;

    public DataSeeder(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        // Insert demo products every time for now
        repo.save(make("Laptop", "Good for study and work", new BigDecimal("799.00"), 10,
                "https://picsum.photos/seed/laptop/400/300"));
        repo.save(make("Headphones", "Noise cancelling", new BigDecimal("99.00"), 25,
                "https://picsum.photos/seed/headphones/400/300"));
        repo.save(make("Keyboard", "Mechanical keyboard", new BigDecimal("55.00"), 30,
                "https://picsum.photos/seed/keyboard/400/300"));
    }

    private Product make(String name, String desc, BigDecimal price, int stock, String img) {
        Product p = new Product();
        p.setName(name);
        p.setDescription(desc);
        p.setPrice(price);
        p.setStock(stock);
        p.setImageUrl(img);
        return p;
    }
}
