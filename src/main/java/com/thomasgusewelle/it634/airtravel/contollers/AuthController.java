package com.thomasgusewelle.it634.airtravel.contollers;

import com.thomasgusewelle.it634.airtravel.models.CustomUser;
import com.thomasgusewelle.it634.airtravel.security.CustomUserDetailsService;
import jakarta.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    //    Point to the HTML file for login
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("user", new CustomUser());
        return "signup";
    }

    @PostMapping("/signup")
    public String PostSignUp(@ModelAttribute CustomUser user) {
        try {
            customUserDetailsService.createUser(user);
        } catch (JAXBException e) {
            System.out.println(e);
//            redirect back to signup on error
            return "redirect:/signup?error";
        }
        return "redirect:/login";
    }

    @PostMapping("/deleteUser")
    public String PostDeleteUser(@ModelAttribute CustomUser user) {
        try {
            customUserDetailsService.deleteUser(user);
        } catch (JAXBException e) {
            System.err.println(e);
            return "redirect:/";
        }
        return "redirect:/signup";
    }
}
