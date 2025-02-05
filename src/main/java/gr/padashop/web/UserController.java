package gr.padashop.web;


import gr.padashop.models.User;
import gr.padashop.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserRepository userRepository;


    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        User user = new User();
        user.setfName("george");

        model.addAttribute("user", user);
        return "index";
    }


    @PostMapping("/save")
    public String saveUser(User user, Model model) {
        logger.info("user is {}", user);
        userRepository.create(user);
        model.addAttribute("message", "user information was saved successfully");
        return "index";
    }

    @PostMapping("/register")
    public String register() {
        return "todo";
    }


    @PostMapping("/login")
    public String login(){
        return "todo";
    }










}
