# 📄 Documentation Fonctionnelle de l’Application Web **CyberChall**

## ✅ Sommaire

1. [Introduction](#1-🎯-introduction)  
2. [Objectifs de l’Application](#2-🎯-objectifs-de-lapplication)  
3. [Fonctionnalités Principales](#3-🧩-fonctionnalités-principales)  
   - [3.1 Modules de Sensibilisation](#31-📚-modules-de-sensibilisation)  
   - [3.2 Gestion des Sessions](#32-🧭-gestion-des-sessions)  
   - [3.3 Résultats et Suivi de Progression](#33-📊-résultats-et-suivi-de-progression)  
   - [3.4 Gestion des Utilisateurs](#34-👥-gestion-des-utilisateurs)  
   - [3.5 Authentification et Sécurité](#35-🔐-authentification-et-sécurité)  
   - [3.6 Interface Utilisateur (UI)](#36-🎨-interface-utilisateur-ui)  
4. [Parcours Utilisateur](#4-👣-parcours-utilisateur)  
5. [Technologies Utilisées](#5-🛠-technologies-utilisées)  
6. [Évolutions Possibles](#6-🚀-évolutions-possibles)  
7. [Conclusion](#7-🧩-conclusion)

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
- ✅ Du contenu explicatif sur les risques numériques. *(V1)*  
- ❓ Un quiz à choix multiples. *(V1)*  
- 🔐 Un challenge ou mini-simulation. *(V2)*  

**Exemples de modules :**
- Protection des données  
- Réseaux sociaux  
- Sécurité des appareils  
- Cyberattaques courantes  

---

### 3.2 🧭 Gestion des Sessions

- Création de sessions (nom, date, thématiques sélectionnées)  
- Liste des sessions disponibles dans un dashboard  
- Participation à une session existante  
- Durée de vie d'une session : 1 mois  

#### 🔧 Diagramme UML - Architecture des Entités

```mermaid
classDiagram
    class Admin {
        - Long id
        - String username
        - String password
        + void createSession()
        + List~Session~ viewSessions()
    }

    class Session {
        - Long id
        - String token
        - Date startDate
        - Int durationInDays
        - Long admin_id
        + void addSubModules()
    }

    class SousModule {
        - Long id
        - String title
        - String type
        - Long session_id
        - Long module_id
        - Long course_id
        - Long challenge_id
        + void addQuiz()
        + void addChallenge()
    }

    class Quizz {
        - String questions
        - Long sousmodule_id
    }

    class Challenge {
        - String description
        - Long sousmodule_id
    }

    class Cours {
        - Long id
        - String content
        - Long sousmodule_id
    }

    class Module {
        - Long id
        - String name
    }

    Admin "1" --> "many" Session : crée
    Session "1" --> "2..4" SousModule : contient
    SousModule --> Quizz : contient
    SousModule --> Challenge : contient
    SousModule --> Cours : lié à
    SousModule --> Module : appartient à
