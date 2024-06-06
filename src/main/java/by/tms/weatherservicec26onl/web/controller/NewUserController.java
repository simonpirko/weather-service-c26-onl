package by.tms.weatherservicec26onl.web.controller;

import by.tms.weatherservicec26onl.auth.TokenProvider;
import by.tms.weatherservicec26onl.entity.User;
import by.tms.weatherservicec26onl.service.NewUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class NewUserController {

    @Autowired
    private NewUserService newUserService;
    @Autowired
    private TokenProvider tokenProvider;

    @GetMapping("/registration")
    public String reg() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(User user, Model model) {
        try {
            newUserService.add(user);
            String token = tokenProvider.generateToken(user);
            model.addAttribute("token", token);
            return "token";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "registration";
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(User user, Model model) {
        try {
            user = newUserService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
            String token = tokenProvider.generateToken(user);
            model.addAttribute("token", token);
            return "token";
        } catch (UsernameNotFoundException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

}
