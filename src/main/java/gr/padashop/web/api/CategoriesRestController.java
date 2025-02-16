package gr.padashop.web.api;

import gr.padashop.models.Category;
import gr.padashop.repositories.ProductCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoriesRestController {
    private static final Logger logger = LoggerFactory.getLogger(CategoriesRestController.class);
    private final ProductCategoryRepository categoryRepository;

    public CategoriesRestController(ProductCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("")
    List<Category> getAll() {
        return categoryRepository.getAll();
    }

}
