package gr.padashop.web;

import gr.padashop.web.models.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController implements PageController {
    private static final PageInfo PAGE_INFO = new PageInfo("Φόρμα δημιουργίας προϊόντος","");

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("pageInfo", pageInfo());
        return "index";
    }

    @Override
    public PageInfo pageInfo() {
        return PAGE_INFO;
    }
}
