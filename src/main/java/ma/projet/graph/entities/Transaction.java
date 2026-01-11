package ma.projet.graph.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Classe entité représentant une transaction bancaire dans le système.
 * Une transaction peut être de type DEPOT ou RETRAIT et est associée à un compte.
 * 
 * @Entity Indique que cette classe est une entité JPA
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    
    /**
     * Identifiant unique de la transaction
     * @Id Marque ce champ comme clé primaire
     * @GeneratedValue Stratégie de génération automatique de l'identifiant
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Montant de la transaction
     */
    private double montant;

    /**
     * Date à laquelle la transaction a été effectuée
     * @Temporal Spécifie que ce champ est une date (sans heure)
     */
    @Temporal(TemporalType.DATE)
    private Date date;

    /**
     * Type de transaction (DEPOT ou RETRAIT)
     * @Enumerated Spécifie que cette propriété est une énumération stockée comme chaîne
     */
    @Enumerated(EnumType.STRING)
    private TypeTransaction type;

    /**
     * Compte associé à cette transaction
     * @ManyToOne Relation plusieurs-à-un avec l'entité Compte
     * Une transaction appartient à un seul compte, un compte peut avoir plusieurs transactions
     */
    @ManyToOne
    private Compte compte;
    
    /**
     * Constructeur pour créer une nouvelle transaction avec un montant, un type et un compte
     * La date est automatiquement définie à la date actuelle
     * 
     * @param montant Montant de la transaction
     * @param type Type de transaction (DEPOT ou RETRAIT)
     * @param compte Compte associé à la transaction
     */
    public Transaction(double montant, TypeTransaction type, Compte compte) {
        this.montant = montant;
        this.type = type;
        this.compte = compte;
        this.date = new Date();
    }
}