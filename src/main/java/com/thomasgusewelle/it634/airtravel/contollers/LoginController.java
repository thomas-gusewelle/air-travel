package com.thomasgusewelle.it634.airtravel.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
//    Point to the HTML file for login
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
}
