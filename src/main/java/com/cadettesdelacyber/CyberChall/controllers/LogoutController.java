package com.cadettesdelacyber.CyberChall.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cadettesdelacyber.CyberChall.utils.Constants;

@Controller
public class LogoutController {

	@GetMapping("/deconnexion")
    public String deconnexion(HttpServletResponse response) {
        Cookie cookie = new Cookie("SESSION_TOKEN", null);
        cookie.setMaxAge(0); // Expirer immédiatement
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/";  // Redirige vers la page de connexion
    }
}
