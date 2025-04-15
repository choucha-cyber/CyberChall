# Documentation Fonctionnelle de l'Application Web CyberChall

## Sommaire
1. [Introduction](#introduction)
2. [Modules de Sensibilisation](#modules-de-sensibilisation)
3. [Création de Sessions](#création-de-sessions)
4. [Gestion des Résultats](#gestion-des-résultats)
5. [Gestion des Utilisateurs](#gestion-des-utilisateurs)
6. [Conclusion](#conclusion)

## Introduction
L'application web **CyberChall** est destinée à sensibiliser les élèves à la cybersécurité à travers des modules interactifs. Ce projet est construit sur le framework **Spring Boot** et utilise **Thymeleaf** pour rendre les vues côté client. Elle permet également de gérer des sessions d'utilisateurs, de suivre leurs résultats, et d'administrer les accès aux modules.

## Modules de Sensibilisation
L'application propose plusieurs modules de sensibilisation à la cybersécurité. Chaque module peut inclure des éléments suivants :
- **Textes explicatifs** sur les dangers et bonnes pratiques de la cybersécurité.
- **Quizz interactifs** pour tester les connaissances des élèves.
- **Challenges pratiques** pour tester les compétences acquises.

Les modules sont organisés sous forme de catégories et peuvent être suivis par les élèves.

## Création de Sessions
Un **utilisateur** peut créer une **session** pour participer à un challenge. Lors de la création de la session, les informations suivantes sont demandées :
- **Nom de la session**
- **Date et heure de début**
- **Thématique** du challenge (ex. cybersécurité, protection des données, etc.)

Les utilisateurs peuvent voir les sessions disponibles et s'inscrire pour participer.

## Gestion des Résultats
Les résultats des élèves sont enregistrés à la fin de chaque module ou challenge. Les enseignants ou administrateurs peuvent consulter les performances des élèves à travers un tableau de bord.

Les données enregistrées peuvent inclure :
- **Score** obtenu.
- **Temps passé** sur chaque module.
- **Réponses** aux quizz.

## Gestion des Utilisateurs
L'application permet aux utilisateurs de se connecter, s'inscrire, et gérer leurs profils. Les administrateurs ont des privilèges supplémentaires pour gérer les sessions et consulter les résultats des utilisateurs.

### Rôles des utilisateurs :
- **Élève** : Peut participer aux modules et voir ses résultats.
- **Administrateur** : Peut gérer les sessions, consulter les résultats, et gérer les utilisateurs.

## Conclusion
L'application **CyberChall** offre une solution complète de sensibilisation à la cybersécurité pour les écoles. Elle permet non seulement de diffuser des informations mais aussi de rendre l'apprentissage plus interactif et engageant pour les élèves.

