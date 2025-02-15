package gr.padashop.web;

import gr.padashop.models.Category;
import gr.padashop.models.Product;
import gr.padashop.models.ProductDto;
import gr.padashop.repositories.ProductCategoryRepository;
import gr.padashop.repositories.ProductRepository;
import gr.padashop.services.ImageFileService;
import gr.padashop.web.models.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController implements PageController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private static final PageInfo PAGE_INFO = new PageInfo("Φόρμα δημιουργίας προϊόντος", "");

    private final ProductRepository productRepository;
    private final ProductCategoryRepository categoryRepository;
    private final ImageFileService imageService;
    List<Category> categories = new ArrayList<>();


    public ProductController(ProductRepository productRepository,
                             ProductCategoryRepository categoryRepository,
                             ImageFileService imageService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.imageService = imageService;
    }

    @GetMapping("/")
    public String index(Model model) {
        categories = categoryRepository.getAll();
        model.addAttribute("page", pageInfo());
        model.addAttribute("categories", categories);
        return "product";
    }

    @PostMapping("/save")
    public String save(ProductDto productDto, Model model, @RequestPart("file") MultipartFile file) {
        Product product = Product.copy(productDto);

        logger.info("product is {}", product);
        if (categories.isEmpty()) {
            categories = categoryRepository.getAll();
        }
        model.addAttribute("page", pageInfo());
        model.addAttribute("categories", categories);
        try {
            product.setPicture(file.getOriginalFilename());
            productRepository.create(product);
            imageService.save(file);
            model.addAttribute("message", "Το προϊόν σώθηκε επιτυχώς");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            model.addAttribute("error", "Αποτυχία αποθήκευσης προϊόντος");
        }
        return "product";
    }

    @Override
    public PageInfo pageInfo() {
        return PAGE_INFO;
    }
}
