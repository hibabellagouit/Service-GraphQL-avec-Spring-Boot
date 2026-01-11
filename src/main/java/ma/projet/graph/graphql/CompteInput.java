// src/main/java/ma/projet/graph/graphql/CompteInput.java
package ma.projet.graph.graphql;

import ma.projet.graph.entities.TypeCompte;

public class CompteInput {
    private Double solde;
    private String dateCreation;
    private TypeCompte type;

    // Constructeur vide obligatoire
    public CompteInput() {}

    public Double getSolde() { return solde; }
    public void setSolde(Double solde) { this.solde = solde; }

    public String getDateCreation() { return dateCreation; }
    public void setDateCreation(String dateCreation) { this.dateCreation = dateCreation; }

    public TypeCompte getType() { return type; }
    public void setType(TypeCompte type) { this.type = type; }
}