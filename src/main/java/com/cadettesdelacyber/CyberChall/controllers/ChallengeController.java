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

import com.cadettesdelacyber.CyberChall.models.Challenge;
import com.cadettesdelacyber.CyberChall.services.ChallengeService;

@RestController
@RequestMapping("/challenges")
public class ChallengeController {
    @Autowired
    private ChallengeService challengeService;

    @GetMapping
    public List<Challenge> getAll() {
        return challengeService.getAllChallenges();
    }

    @GetMapping("/{id}")
    public Challenge getOne(@PathVariable Long id) {
        return challengeService.getChallenge(id);
    }

    @PostMapping
    public Challenge create(@RequestBody Challenge challenge) {
        return challengeService.saveChallenge(challenge);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        challengeService.deleteChallenge(id);
    }
}

