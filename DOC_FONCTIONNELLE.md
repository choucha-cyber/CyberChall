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
Elle combine des contenus théoriques, des quiz (V1) et des challenges interactifs (V2).

---

## 2. 🎯 Objectifs de l’Application

- V1 :
   - Sensibiliser les élèves aux enjeux de la cybersécurité  
   - Évaluer leurs connaissances via des modules interactifs
- V2 :
   - Suivre leur progression grâce à des outils de reporting simples  
   - Fournir aux enseignants une interface de gestion intuitive  

---

## 3. 🧩 Fonctionnalités Principales

### 3.1 📚 Modules de Sensibilisation

Chaque module comprend :  
- ✅ Du contenu explicatif *(V1)*  
- ❓ Un quiz à choix multiples *(V1)*  
- 🔐 Un challenge ou mini-simulation *(V2)*  

**Exemples de modules :**
- Protection des données  
- Réseaux sociaux  
- Sécurité des appareils  
- Cyberattaques courantes  

---

### 3.2 🧭 Gestion des Sessions

- Création de sessions (admin, date, durée 1 mois, sous-modules)  
- Une session doit contenir **2 à 4 sous-modules obligatoires**  
- Un administrateur peut gérer plusieurs sessions  

---

#### 🔧 Diagramme UML - Architecture des Entités

```mermaid
classDiagram
    class Admin {
        - Long id
        - String username
        - String password
        + void createSession()
        + List~Session~ viewSessions()
        + void deleteSession()
    }

    class Session {
        - Long id
        - String token
        - Date startDate
        - Int durationInDays
        - Long admin_id [FK]
        + void addSubModules()
        + List~SousModule~ getSousModules()
    }

    class SousModule {
        - Long id
        - String title
        - String type
        - Long session_id [FK]
        - Long module_id [FK]
        - Long course_id [FK]
        - Long challenge_id [FK]
        + void addQuiz()
        + void addChallenge()
        + Cours getCours()
        + Challenge getChallenge()
        + Quizz getQuizz()
    }

    class Module {
        - Long id
        - String name
        + List~SousModule~ getSousModules()
    }

    class Quizz {
        - Long id
        - String questions
        - Long sousmodule_id [FK]
    }

    class Challenge {
        - Long id
        - String description
        - Long sousmodule_id [FK]
    }

    class Cours {
        - Long id
        - String content
        - Long sousmodule_id [FK]
    }

    Admin "1" --> "many" Session : crée
    Session "1" --> "2..4" SousModule : contient
    SousModule --> Quizz : contient
    SousModule --> Challenge : contient
    SousModule --> Cours : lié à
    SousModule --> Module : appartient à
### 🔄 3.2.1 Sessions Pédagogiques Temporaires via QR Code

Dans le cadre d’ateliers ponctuels ou de sessions de démonstration, l’application doit permettre à un administrateur de créer une **session pédagogique temporaire, anonyme et sans authentification**. Ce mode facilite un accès rapide aux modules via un simple **QR code** scannable par les élèves.

#### 🎯 Objectif Fonctionnel

- Création d'une session temporaire avec sélection de **2 à 4 modules existants**.
- Génération automatique d’un **QR code** contenant un lien unique.
- Accès public à une **page temporaire** qui :
  - Est visuellement identique à la page `accueil-admin`.
  - Affiche uniquement les modules sélectionnés.
  - Ne nécessite pas d’authentification.

#### 🔧 Fonctionnalités à Implémenter

1. **Création de session temporaire** :
   - Sélection de 2 à 4 modules par l’admin.
   - Session valide pendant **30 jours** (création + 30 jours).
   - Génération d’un **token UUID** servant d’identifiant unique.

2. **Construction de l’URL temporaire** :
   - Exemple de lien :
     ```
     http://localhost:4040/accueil-temporaire?modules=Cyberattaque&modules=Réseaux
     ```
   - Utilisé pour créer un QR code (via `zxing`, `QRCodeWriter`, etc.).

3. **Composants nécessaires** :
   - ✅ Un **QR code encodant** l’URL avec les modules sélectionnés.
   - ✅ Un **contrôleur Spring Boot** :
     ```java
     @GetMapping("/accueil-temporaire")
     public String afficherAccueilTemporaire(@RequestParam List<String> modules, Model model) {
         // Chargement des modules par noms
     }
     ```
   - ✅ Un **service** pour filtrer et charger dynamiquement les modules à afficher.
   - ✅ Une **vue Thymeleaf** `accueil-temporaire.html`, basée sur `accueil-admin.html` :
     - Supprimer le bloc d’authentification.
     - Supprimer le menu administrateur.
     - Afficher uniquement les modules reçus en paramètre.

#### 🔗 Exemple de lien généré via QR code
http://localhost:4040/accueil-temporaire?modules=Cyberattaque&modules=Réseaux
