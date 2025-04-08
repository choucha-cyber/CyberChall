package com.cadettesdelacyber.CyberChall.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cadettesdelacyber.CyberChall.models.Session;
import com.cadettesdelacyber.CyberChall.services.SessionService;

@RestController
@RequestMapping("/sessions")
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @GetMapping
    public List<Session> getAll() {
        return sessionService.getAllSessions();
    }

    @GetMapping("/{id}")
    public Session getOne(@PathVariable Long id) {
        return sessionService.getSession(id);
    }

    @PostMapping
    public Session create(@RequestBody Session session) {
        return sessionService.saveSession(session);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        sessionService.deleteSession(id);
    }
}

