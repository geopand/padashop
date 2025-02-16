package gr.padashop.web.api;

import gr.padashop.models.Product;
import gr.padashop.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
    private static final Logger logger = LoggerFactory.getLogger(ProductRestController.class);

    private final ProductRepository productRepository;

    public ProductRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/all")
    List<Product> getAllProducts() {
        return productRepository.getAll();
    }

    //http://localhost:8080/api/products/slug?slug=vivechrom-neopal-eco-56--195#/75-ποσότητα-10ltr
    @GetMapping("/slug")
    Optional<Product> getProductBySlug(@RequestParam String slug) {
        return productRepository.getBySlug(slug);
    }

    @GetMapping("/categories/slug")
    List<Product> getProductsUnderCategory(@RequestParam String slug) {
        return productRepository.getByCategorySlug(slug);
    }


//    http://localhost:8080/api/products/category/4
    @GetMapping("/category/{id}")
    List<Product> products(@PathVariable() long id) {
        return productRepository.getAllByCategoryId(id);
    }
}
