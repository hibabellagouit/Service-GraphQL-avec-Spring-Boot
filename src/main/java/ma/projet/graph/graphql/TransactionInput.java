// src/main/java/ma/projet/graph/graphql/TransactionInput.java
package ma.projet.graph.graphql;

import ma.projet.graph.entities.TypeTransaction;

public class TransactionInput {
    private Long compteId;
    private Double montant;
    private String date;
    private TypeTransaction type;

    public TransactionInput() {}

    public Long getCompteId() { return compteId; }
    public void setCompteId(Long compteId) { this.compteId = compteId; }

    public Double getMontant() { return montant; }
    public void setMontant(Double montant) { this.montant = montant; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public TypeTransaction getType() { return type; }
    public void setType(TypeTransaction type) { this.type = type; }
}