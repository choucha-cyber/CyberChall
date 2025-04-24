package com.cadettesdelacyber.CyberChall.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cadettesdelacyber.CyberChall.models.User;
import com.cadettesdelacyber.CyberChall.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // Cherche un utilisateur par son nom
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Crée un nouvel utilisateur (admin ou élève)
    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    // Trouve un utilisateur par son nom et son rôle (admin / élève)
    @Override
    public User findByUsernameAndRole(String username, String role) {
        return userRepository.findByUsernameAndRole(username, role);
    }
}