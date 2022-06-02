package ru.job4j.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.model.User;
import ru.job4j.service.AuthorityService;
import ru.job4j.service.UserService;

@Controller
public class RegController {

    private final UserService userService;
    private final PasswordEncoder encoder;
    private final AuthorityService authorityService;


    public RegController(UserService userService, PasswordEncoder encoder, AuthorityService authorityService) {
        this.userService = userService;
        this.encoder = encoder;
        this.authorityService = authorityService;
    }

    @GetMapping("/reg")
    public String regPage(Model model,
                          @RequestParam(name = "fail", required = false) Boolean fail) {
        if (fail != null) {
            model.addAttribute("errMessage", "User with this username already exists!");
        }
        model.addAttribute("fail", fail != null);
        return "regPage";
    }

//    @GetMapping("/reg")
//    public String regPage() {
//        return "regPage";
//    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorityService.findByAuthority("ROLE_USER"));
        User rsl = userService.save(user);
        if (rsl == null) {
            return "redirect:/registration?fail=true";
        }
        return "redirect:/login";
    }
}
