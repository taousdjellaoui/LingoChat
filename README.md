# Documentation du Projet

Ce projet est une application web développée à l'aide de technologies modernes. Vous trouverez ci-dessous les détails du stack technologique, les identifiants des utilisateurs, et les problèmes connus.

## Stack Technologique

L'application a été réalisée avec les technologies suivantes :

- **Backend :** Spring Boot
- **Moteur de templates côté serveur :** Thymeleaf
- **Base de données :** MySQL
- **Framework Frontend :** Angular
- **Opérations Asynchrones :** AJAX
- **Utilitaires :** jQuery

## Identifiants des Utilisateurs

Voici les identifiants pour accéder aux différents types de comptes :

| Rôle          | Email                   | Mot de passe  |
|---------------|-------------------------|---------------|
| **Utilisateur** | `marie1@example.com`  | `marie123`    |
| **MembrePlus** | `leanna2@example.com`  | `leanna123`   |
| **Admin**     | `marco3@example.com`   | `marco123`    |

## Problèmes Connus

### Fonctionnalité de Suppression d'Utilisateurs Partielle

La fonctionnalité de suppression des utilisateurs fonctionne uniquement dans certains cas :

1. **Gestion des Dépendances :** Les utilisateurs ayant des dépendances (par exemple, des amis ou des données associées) ne peuvent pas être supprimés en raison d'une gestion incomplète des dépendances dans l'application.
2. **Suppression d'un Nouvel Utilisateur :** Seuls les utilisateurs sans dépendances peuvent être supprimés avec succès. Par exemple, l'utilisateur **Martin** peut être supprimé car il n'a aucun ami et n'est ami avec personne.

> **Remarque :** La gestion des dépendances doit être améliorée pour permettre une suppression complète et sans restriction des utilisateurs.

---

## Démarrage

1. **Clonez le dépôt** et configurez le projet.
2. **Configurez la base de données MySQL** dans le fichier `application.properties`.
3. **Lancez l'application** via votre IDE ou avec une commande comme :
   ```bash
   mvn spring-boot:run
