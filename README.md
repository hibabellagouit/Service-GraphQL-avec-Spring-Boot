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

<img width="1366" height="728" alt="GraphiQL - Google Chrome 11_01_2026 17_36_13" src="https://github.com/user-attachments/assets/b66d1dc8-2f4d-4bda-b1c8-343d17256459" />
<img width="1366" height="728" alt="GraphiQL - Google Chrome 11_01_2026 17_37_23" src="https://github.com/user-attachments/assets/a31d5bf7-d48f-49a4-8ffe-7056698b1dea" />
<img width="1366" height="728" alt="GraphiQL - Google Chrome 11_01_2026 17_37_45" src="https://github.com/user-attachments/assets/d7413993-17c7-4a07-87cc-54ab5b2360f7" />
<img width="1366" height="728" alt="GraphiQL - Google Chrome 11_01_2026 17_40_50 (1)" src="https://github.com/user-attachments/assets/f5015ae9-633b-4c4f-8589-815d72089134" />
<img width="1366" height="728" alt="GraphiQL - Google Chrome 11_01_2026 17_40_50 (2)" src="https://github.com/user-attachments/assets/2fb35add-495c-4bdc-b24a-2b4d32b7a51c" />
<img width="1366" height="728" alt="GraphiQL - Google Chrome 11_01_2026 17_40_50" src="https://github.com/user-attachments/assets/27577e40-7063-46f6-919f-e9257e6a86b9" />


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

