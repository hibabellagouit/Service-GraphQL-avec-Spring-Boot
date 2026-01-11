package ma.projet.graph.entities;

/**
 * Énumération représentant les différents types de transactions bancaires possibles.
 * Ces types définissent la nature des opérations effectuées sur les comptes.
 */
public enum TypeTransaction {
    /**
     * Opération de dépôt - Ajout d'argent sur un compte
     */
    DEPOT,
    
    /**
     * Opération de retrait - Retrait d'argent depuis un compte
     */
    RETRAIT
}