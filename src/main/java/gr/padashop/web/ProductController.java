package gr.padashop.web;

import gr.padashop.models.Product;
import gr.padashop.models.ProductCategory;
import gr.padashop.repositories.ProductCategoryRepository;
import gr.padashop.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/product")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductRepository productRepository;
    private final ProductCategoryRepository categoryRepository;
    List<ProductCategory> categories = new ArrayList<>();


    public ProductController(ProductRepository productRepository, ProductCategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        Product product = new Product();

        List<ProductCategory> categories = categoryRepository.getAll();
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "product";
    }

    @PostMapping("/save")
    public String saveUser(Product product, Model model) {
        logger.info("product is {}", product);
        productRepository.create(product);
        model.addAttribute("message", "Το προϊόν σώθηκε επιτυχώς");
        return "product";
    }

}
