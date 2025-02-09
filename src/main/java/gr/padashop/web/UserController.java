package gr.padashop.web;


import gr.padashop.models.User;
import gr.padashop.repositories.UserRepository;
import gr.padashop.web.models.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController implements PageController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final PageInfo PAGE_INFO = new PageInfo("Φόρμα Εισαγωγής Χρήστη", "");

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        User user = new User();
        user.setfName("george");

        model.addAttribute("user", user);
        model.addAttribute("page", pageInfo());
        return "user";
    }

    @PostMapping("/save")
    public String saveUser(User user, Model model) {
        logger.info("user is {}", user);
        userRepository.create(user);
        model.addAttribute("page", pageInfo());
        model.addAttribute("message", "user information was saved successfully");
        return "user";
    }

    @PostMapping("/register")
    public String register() {
        return "todo";
    }


    @PostMapping("/login")
    public String login() {
        return "todo";
    }


    @Override
    public PageInfo pageInfo() {
        return PAGE_INFO;
    }
}
