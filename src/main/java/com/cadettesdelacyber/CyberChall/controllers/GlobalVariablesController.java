package com.cadettesdelacyber.CyberChall.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalVariablesController {

    @ModelAttribute
    public void addSessionAttributes(Model model, HttpSession session) {
        model.addAttribute("estConnecte", session.getAttribute("estConnecte") != null ? session.getAttribute("estConnecte") : false);
        model.addAttribute("username", session.getAttribute("username") != null ? session.getAttribute("username") : "Invité");
        model.addAttribute("role", session.getAttribute("role") != null ? session.getAttribute("role") : "anonyme");
    }
}


