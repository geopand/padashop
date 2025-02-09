package gr.padashop.web.api;

import gr.padashop.models.Product;
import gr.padashop.repositories.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    private final ProductRepository productRepository;

    public ProductRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/all")
    List<Product> getAllProducts() {
        return productRepository.getAll();
    }

    //http://localhost:8080/api/products/slug?slug=vice/dsd
    @GetMapping("/slug")
    Optional<Product> getProductBySlug(@RequestParam String slug) {
        return productRepository.getBySlug(slug);
    }


//    http://localhost:8080/api/products/category/4
    @GetMapping("/category/{id}")
    List<Product> products(@PathVariable() long id) {
        return productRepository.getAllByCategoryId(id);
    }
}
