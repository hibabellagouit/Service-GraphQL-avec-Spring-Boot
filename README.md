# 🏦 Service Bancaire GraphQL avec Spring Boot

## 📋 Description
Ce projet est une application de gestion bancaire développée avec Spring Boot et GraphQL. Elle permet de gérer des comptes bancaires (courant et épargne) avec des opérations de dépôt et de retrait, ainsi que des statistiques globales.

## 🚀 Technologies Utilisées
- **Framework Backend** : Spring Boot 3.5.8
- **API GraphQL** : Spring GraphQL
- **Langage** : Java 21
- **Base de données** : H2 Database (en mémoire)
- **Outils** : Lombok, Maven
- **Interface** : GraphiQL pour les tests

## 🛠️ Fonctionnalités

### Comptes Bancaires
- Création de comptes (COURANT / EPARGNE)
- Consultation des comptes par ID
- Liste de tous les comptes
- Mise à jour automatique des soldes

### Opérations
- Dépôts d'argent
- Retraits d'argent
- Vérification des soldes
- Historique des transactions

### Statistiques
- Solde total de tous les comptes
- Statistiques des transactions
- Suivi des opérations

## 📊 Schéma GraphQL

### Types
```graphql
type Compte {
    id: ID!
    solde: Float!
    type: TypeCompte!
    dateCreation: String!
    transactions: [Transaction!]!
}

type Transaction {
    id: ID!
    montant: Float!
    type: TypeTransaction!
    date: String!
    compte: Compte!
}

type Statistiques {
    totalSolde: Float!
    nombreTransactions: Int!
    montantMoyen: Float!
}

enum TypeCompte {
    COURANT
    EPARGNE
}

enum TypeTransaction {
    DEPOT
    RETRAIT
}
```

### Queries Disponibles
```graphql
type Query {
    # Récupère un compte par son ID
    compteById(id: ID!): Compte
    
    # Liste tous les comptes
    comptes: [Compte!]!
    
    # Obtient les statistiques globales
    statistiques: Statistiques!
}
```

### Mutations Disponibles
```graphql
type Mutation {
    # Crée un nouveau compte
    creerCompte(solde: Float!, type: TypeCompte!): Compte!
    
    # Effectue un dépôt sur un compte
    effectuerDepot(compteId: ID!, montant: Float!): Transaction!
    
    # Effectue un retrait sur un compte
    effectuerRetrait(compteId: ID!, montant: Float!): Transaction!
}
```

## 🖼️ Captures d'écran

| Description | Capture |
|-------------|---------|
| **Création de comptes** - Interface de création de comptes avec solde initial | ![Création de comptes](captures/1.png) |
| **Opérations bancaires** - Exemple de dépôts et retraits | ![Opérations](captures/2.png) |
| **Liste des comptes** - Vue d'ensemble de tous les comptes | ![Liste des comptes](captures/3.png) |
| **Statistiques** - Aperçu des statistiques globales | ![Statistiques](captures/4.png) |
| **Vérification finale** - Vérification du solde du compte n°1 | ![Vérification](captures/5.png) |

## 🚀 Démarrage Rapide

### Prérequis
- Java 21 ou supérieur
- Maven 3.6 ou supérieur

### Installation
1. Cloner le dépôt
2. Lancer l'application :
   ```bash
   mvn spring-boot:run
   ```
3. Accéder à l'interface GraphiQL :
   ```
   http://localhost:8080/graphiql
   ```

## 📝 Exemple de Requête

### Création d'un compte
```graphql
mutation {
  creerCompte(solde: 5000.0, type: COURANT) {
    id
    solde
    type
  }
}
```

### Effectuer un dépôt
```graphql
mutation {
  effectuerDepot(compteId: 1, montant: 2000.0) {
    id
    montant
    type
    date
  }
}
```

### Obtenir un compte avec ses transactions
```graphql
query {
  compteById(id: 1) {
    id
    solde
    type
    transactions {
      id
      montant
      type
      date
    }
  }
}
```

## 📊 Structure du Projet
```
src/
├── main/
│   ├── java/com/example/bank/
│   │   ├── controller/    # Contrôleurs GraphQL
│   │   ├── model/         # Modèles de données
│   │   ├── repository/    # Couche d'accès aux données
│   │   ├── service/       # Logique métier
│   │   └── BankApplication.java
│   └── resources/
│       ├── application.yml # Configuration
│       └── schema/        # Schémas GraphQL
└── test/                  # Tests unitaires et d'intégration
```

## 📈 Améliorations Possibles
- [ ] Ajouter l'authentification utilisateur
- [ ] Implémenter des vues pour la gestion des clients
- [ ] Ajouter des notifications par email pour les transactions
- [ ] Implémenter des limites de retrait
- [ ] Ajouter des tests d'intégration complets

## 📄 Licence
Ce projet est sous licence MIT - voir le fichier [LICENSE](LICENSE) pour plus de détails.