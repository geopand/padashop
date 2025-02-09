package gr.padashop.web;


import gr.padashop.models.ProductCategory;
import gr.padashop.repositories.ProductCategoryRepository;
import gr.padashop.web.models.PageInfo;
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
@RequestMapping("/category")
public class ProductCategoryController implements PageController {
    public static final Logger logger = LoggerFactory.getLogger(ProductCategoryController.class);
    private List<ProductCategory> categories = new ArrayList<>();
    private static final PageInfo PAGE_INFO = new PageInfo("Φόρμα δημιουργίας κατηγορίας", "");

    private final ProductCategoryRepository categoryRepository;

    public ProductCategoryController(ProductCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        ProductCategory category = new ProductCategory();
        categories = categoryRepository.getAll();

        model.addAttribute("page", pageInfo());
        model.addAttribute("category", category);
        model.addAttribute("categories", categories);
        return "category";
    }

    @PostMapping("/save")
    public String saveCategory(ProductCategory category, Model model) {
        logger.info("category is {}", category);
        categoryRepository.create(category);
        model.addAttribute("page", pageInfo());
        model.addAttribute("message", "Η κατηγορία σώθηκε επιτυχώς");
        model.addAttribute("category", category);
        model.addAttribute("categories", categories);

        return "category";
    }

    @Override
    public PageInfo pageInfo() {
        return PAGE_INFO;
    }
}
