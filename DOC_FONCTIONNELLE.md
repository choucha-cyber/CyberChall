# 📄 Documentation Fonctionnelle de l’Application Web **CyberChall**

## ✅ Sommaire

1. [Introduction](#1-introduction)  
2. [Objectifs de l’Application](#2-objectifs-de-lapplication)  
3. [Fonctionnalités Principales](#3-fonctionnalités-principales)  
   - [3.1 Modules de Sensibilisation](#31-modules-de-sensibilisation)  
   - [3.2 Gestion des Sessions](#32-gestion-des-sessions)  
   - [3.3 Résultats et Suivi de Progression](#33-résultats-et-suivi-de-progression)  
   - [3.4 Gestion des Utilisateurs](#34-gestion-des-utilisateurs)  
   - [3.5 Authentification et Sécurité](#35-authentification-et-sécurité)  
   - [3.6 Interface Utilisateur (UI)](#36-interface-utilisateur-ui)  
4. [Parcours Utilisateur](#4-parcours-utilisateur)  
5. [Technologies Utilisées](#5-technologies-utilisées)  
6. [Évolutions Possibles](#6-évolutions-possibles)  
7. [Conclusion](#7-conclusion)

---

## 1. 🎯 Introduction

**CyberChall** est une application web pédagogique développée pour initier les collégiens et lycéens aux bonnes pratiques en cybersécurité.  
Elle combine des contenus théoriques, des quiz et des challenges interactifs.

Développée avec **Spring Boot** et **Thymeleaf**, elle permet de suivre la progression des utilisateurs et de gérer les sessions pédagogiques.

---

## 2. 🎯 Objectifs de l’Application

- Sensibiliser les élèves aux enjeux de la cybersécurité.
- Évaluer leurs connaissances via des modules interactifs.
- Suivre leur progression grâce à des outils de reporting simples.
- Fournir aux enseignants une interface de gestion intuitive.

---

## 3. 🧩 Fonctionnalités Principales

### 3.1 📚 Modules de Sensibilisation

Chaque module comprend :
- ✅ Du contenu explicatif sur les risques numériques. --> V1
- ❓ Un quiz à choix multiples. --> V1
- 🔐 Un challenge ou mini-simulation. --> V2

**Exemples de modules :**
- Protection des données
- Réseaux sociaux
- Sécurité des appareils
- Cyberattaques courantes

---

### 3.2 🧭 Gestion des Sessions

- Création de sessions (nom, date, thématiques selectionnées)
- Liste des sessions disponibles dans un dashboard
- Participation à une session existante
- durée de vie Session : 1 mois

---

### 3.3 📊 Résultats et Suivi de Progression

- Stockage des scores et réponses
- Nombre de connexion par session/Ecole
- Affichage des performances dans un tableau de bord (admin)

---

### 3.4 👥 Gestion des Utilisateurs

Deux rôles :
- **Élève** : accès aux modules, quiz et résultats personnels
- **Administrateur** : dans un premier temps les cadettes chargées de la sensibilisation et les profs (V2) gestion des sessions, vue sur les résultats

Fonctionnalités :
- Connexion simple via formulaire --> redirection vers les pages d'accueil selon le rôle
- Cookie de session généré après authentification
- Accès différencié selon le rôle

---

### 3.5 🔐 Authentification et Sécurité

- Vérification des identifiants (username + password)
- Token de session stocké côté navigateur via cookie
- Redirections conditionnelles si non connecté

---

### 3.6 🎨 Interface Utilisateur (UI)

- Design responsive
- Mode **clair / sombre** activable via le menu
- Utilisation de **fragments Thymeleaf** pour le header et le footer
- Navigation fluide entre modules, résultats et profil

---

## 4. 👣 Parcours Utilisateur

### Élève :
1. Arrive sur `/` → page de connexion.
2. Se connecte → redirection vers `accueil_admin`.
3. Accède aux modules, répond aux quiz/challenges.
4. Visualise ses résultats.

### Administrateur :
1. Connexion comme un utilisateur classique.
2. Accès à des vues supplémentaires :
   - Création de session
   - Liste des sessions actives
   - Vue sur les performances des utilisateurs

---

## 5. 🛠 Technologies Utilisées

| Composant        | Technologie        |
|------------------|--------------------|
| Backend          | Spring Boot (Java) |
| Frontend         | Thymeleaf, HTML/CSS |
| Authentification | Cookie + Session    |
| Build            | Maven              |
| Base de données  | (H2 À implémenter, version actuelle utilise une mémoire temporaire) |

---

## 6. 🚀 Évolutions Possibles

- Système d’inscription avec hachage des mots de passe
- Export des résultats (CSV, PDF)
- Interface d’administration dédiée
- Niveaux de difficulté par module
- Gamification (badges, niveaux, timer)

---

## 7. 🧩 Conclusion

**CyberChall** est un outil éducatif moderne, conçu pour éveiller les jeunes aux enjeux de la cybersécurité.  
Avec son interface simple et ses contenus ludiques, elle favorise une prise de conscience numérique tout en rendant l'apprentissage attractif.

---

> 🛠 *Projet open-source maintenu dans un but pédagogique. Pour toute suggestion, merci de proposer une issue ou un pull request.*

