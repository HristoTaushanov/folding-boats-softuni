package bg.softuni.boats.model.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserLoginController {


    @GetMapping("/login")
    public String login() {
        return "user-login.txt";
    }

    @PostMapping("/login-error")
    public String onFail(
            @ModelAttribute("username") String username,
            Model model) {
        model.addAttribute("username", username);
        model.addAttribute("bad_credentials", "true");
        return "user-login.txt";
    }


}