package ma.projet.graph.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe entité représentant un compte bancaire dans le système.
 * Un compte peut être de type COURANT ou EPARGNE et contient une liste de transactions.
 * 
 * @Entity Indique que cette classe est une entité JPA
 * @Table Spécifie le nom de la table dans la base de données
 */
@Entity
@Table(name = "COMPTE")
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Compte {
    
    /**
     * Identifiant unique du compte
     * @Id Marque ce champ comme clé primaire
     * @GeneratedValue Stratégie de génération automatique de l'identifiant
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Solde actuel du compte
     */
    private double solde;
    
    /**
     * Date et heure de création du compte
     */
    private LocalDateTime dateCreation;
    
    /**
     * Type du compte (COURANT ou EPARGNE)
     * @Enumerated Spécifie que cette propriété est une énumération stockée comme chaîne
     */
    @Enumerated(EnumType.STRING)
    private TypeCompte type;
    
    /**
     * Liste des transactions associées à ce compte
     * @OneToMany Relation un-à-plusieurs avec l'entité Transaction
     * @mappedBy Spécifie que la relation est gérée par l'attribut 'compte' dans Transaction
     * @FetchType.LAZY Le chargement des transactions est différé jusqu'à ce qu'elles soient explicitement demandées
     */
    @OneToMany(mappedBy = "compte", fetch = FetchType.LAZY)
    private List<Transaction> transactions = new ArrayList<>();

    /**
     * Constructeur pour créer un nouveau compte avec un solde et un type spécifiés
     * La date de création est automatiquement définie à la date et l'heure actuelles
     * 
     * @param solde Montant initial du compte
     * @param type Type de compte (COURANT ou EPARGNE)
     */
    public Compte(double solde, TypeCompte type) {
        this.solde = solde;
        this.type = type;
        this.dateCreation = LocalDateTime.now();
    }
}