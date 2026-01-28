# Ensitech Backend With Spring boot

## Règles
### Nommage 
    Il est préferable de nommer Les classes, interfaces, enumerations et les dossiers en utlisant la langue anglaise.
    Exemple : StudentService.java.

### Branches

#### Nommage

Il est préferable de nommer les branches liées aux fonctionnalités en commençant par  **feature/** 
e.g: git checkout -b **feature/create-student** : pour la fonctionnalité créer un etudiant.

#### Push 
    Une fois dans la branche feature/create-student par exemple.
    la commande pour faire un push est:
    git push -u origin feature/create-student


Ce dépôt contient le code source du backend de l'application de gestion de l'établissement scolaire ENSITECH. Il est développé avec le framework Spring Boot et expose une API RESTful consommée par une application front-end (Angular).

## 🚀 Démarrage rapide

### Prérequis

-   [JDK 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) ou une version supérieure
-   [Apache Maven](https://maven.apache.org/download.cgi) 3.8+
-   Une instance de base de données [MySQL](https://dev.mysql.com/downloads/mysql/) en cours d'exécution
-   Un IDE comme [IntelliJ IDEA](https://www.jetbrains.com/idea/) ou [VS Code](https://code.visualstudio.com/)

### Configuration

1.  **Cloner le dépôt :**
    ```bash
    git clone [URL_DE_VOTRE_DEPOT]
    cd ensithech-spring-boot
    ```

2.  **Configurer la base de données :**
    -   Créez une base de données MySQL nommée `ensitech`.
    -   Ouvrez le fichier `src/main/resources/application-local.yml` (ou `.properties`).
    -   Modifiez les propriétés `spring.datasource.url`, `spring.datasource.username`, et `spring.datasource.password` pour correspondre à votre configuration MySQL locale.

3.  **Lancer l'application :**
    -   Vous pouvez lancer l'application directement depuis votre IDE en exécutant la classe `EnsitechApplication`.
    -   Ou via Maven :
        ```bash
        mvn spring-boot:run -Dspring-boot.run.profiles=local
        ```

L'application démarrera par défaut sur le port `8084`.

## 📖 Documentation de l'API

Une fois l'application lancée, la documentation interactive de l'API (générée avec Swagger/OpenAPI) est disponible à l'adresse suivante :

[http://localhost:8084/swagger-ui/index.html](http://localhost:8084/swagger-ui/index.html)

Cette interface vous permet de visualiser et de tester tous les endpoints de l'API directement depuis votre navigateur.

## ✨ Module `Teacher` (Gestion des Enseignants)

Ce module fournit les fonctionnalités CRUD (Create, Read, Update, Delete) pour la gestion des enseignants.

### Endpoints de l'API

| Verbe HTTP | URL                               | Description                                     |
| :--------- | :-------------------------------- | :---------------------------------------------- |
| `POST`     | `/api/v1/teachers`                | Crée un nouvel enseignant.                      |
| `GET`      | `/api/v1/teachers`                | Récupère la liste de tous les enseignants.      |
| `GET`      | `/api/v1/teachers/{id}`           | Récupère un enseignant par son identifiant.     |
| `PUT`      | `/api/v1/teachers/{id}`           | Met à jour un enseignant existant.              |
| `DELETE`   | `/api/v1/teachers/{id}`           | Supprime un enseignant par son identifiant.     |

### Structure du module

-   **Controller (`TeacherController`) :** Expose les endpoints REST et gère les requêtes/réponses HTTP.
-   **Service (`TeacherServiceImpl`) :** Contient la logique métier (validation, orchestration).
-   **Repository (`PersonRepository`) :** Gère l'accès aux données via Spring Data JPA.
-   **Entités (`Person`, `Teacher`) :** Modèles de données représentant les tables de la base de données.
-   **DTO (`TeacherDto`) :** Objet de transfert de données utilisé pour communiquer avec le client, assurant la sécurité et la flexibilité de l'API.
-   **Mapper (`TeacherMapper`) :** S'occupe de la conversion entre les Entités et les DTOs, grâce à MapStruct.

### Gestion des Exceptions et Logging

-   **Gestion Centralisée :** La classe `GlobalExceptionHandler` intercepte toutes les exceptions pour fournir des réponses d'erreur JSON standardisées (404, 400, 500).
-   **Logging :** Le projet est configuré avec **Log4j2**. Les logs sont écrits dans la console et dans le fichier `logs/ensitech-app.log`.

## ✅ Tests

Les tests unitaires sont écrits avec JUnit 5 et Mockito. Pour lancer les tests :

```bash
mvn test
```

## 🛠️ Stack Technique

-   **Langage :** Java 17/21
-   **Framework :** Spring Boot 3.3
-   **Accès aux données :** Spring Data JPA / Hibernate
-   **Base de données :** MySQL
-   **Migration de BDD :** Flyway (configuré, en attente d'utilisation)
-   **Validation :** Spring Boot Starter Validation (Hibernate Validator)
-   **Mapping Objet :** MapStruct
-   **Logging :** Log4j2
-   **Build :** Apache Maven
-   **Documentation API :** Springdoc (Swagger/OpenAPI)


