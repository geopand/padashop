package gr.padashop.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model, @RequestParam String name ){

        model.addAttribute("name", name);
        return "index";


        //return "redirect:success.html"
    }
}
