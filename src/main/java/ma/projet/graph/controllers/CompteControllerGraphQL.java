package ma.projet.graph.controllers;

import lombok.AllArgsConstructor;
import ma.projet.graph.entities.*;
import ma.projet.graph.graphql.CompteInput;
import ma.projet.graph.graphql.TransactionInput;
import ma.projet.graph.repositories.CompteRepository;
import ma.projet.graph.repositories.TransactionRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contrôleur GraphQL pour la gestion des comptes bancaires et des transactions.
 * Fournit des endpoints pour effectuer des opérations CRUD sur les comptes et les transactions.
 * 
 * @Controller Indique que cette classe est un contrôleur Spring
 * @AllArgsConstructor Génère un constructeur avec tous les champs requis
 */
@Controller
@AllArgsConstructor
public class CompteControllerGraphQL {

    private final CompteRepository compteRepository;
    private final TransactionRepository transactionRepository;

    /**
     * Récupère tous les comptes bancaires
     * @return Liste de tous les comptes
     */
    @QueryMapping 
    public List<Compte> allComptes() { 
        return compteRepository.findAll(); 
    }
    
    /**
     * Récupère un compte par son identifiant
     * @param id Identifiant du compte à récupérer
     * @return Le compte correspondant à l'identifiant, ou null si non trouvé
     */
    @QueryMapping 
    public Compte compteById(@Argument Long id) { 
        return compteRepository.findById(id).orElse(null); 
    }

    /**
     * Calcule les statistiques globales des soldes des comptes
     * @return Map contenant le nombre de comptes, la somme des soldes et la moyenne
     */
    @QueryMapping
    public Map<String, Object> totalSolde() {
        long count = compteRepository.count();
        Double sum = compteRepository.sumSoldes();
        if (sum == null) sum = 0.0;
        double average = count > 0 ? sum / count : 0.0;

        Map<String, Object> result = new HashMap<>();
        result.put("count", (int) count);
        result.put("sum", sum.floatValue());
        result.put("average", (float) average);
        return result;
    }

    /**
     * Récupère toutes les transactions d'un compte spécifique
     * @param id Identifiant du compte
     * @return Liste des transactions du compte
     */
    @QueryMapping 
    public List<Transaction> compteTransactions(@Argument Long id) { 
        return transactionRepository.findByCompteId(id); 
    }
    
    /**
     * Récupère toutes les transactions
     * @return Liste de toutes les transactions
     */
    @QueryMapping 
    public List<Transaction> allTransactions() { 
        return transactionRepository.findAll(); 
    }

    /**
     * Calcule les statistiques sur les transactions
     * @return Map contenant le nombre total de transactions, la somme des dépôts et la somme des retraits
     */
    @QueryMapping
    public Map<String, Object> transactionStats() {
        long count = transactionRepository.count();
        Double depots = transactionRepository.sumByType(TypeTransaction.DEPOT);
        Double retraits = transactionRepository.sumByType(TypeTransaction.RETRAIT);
        if (depots == null) depots = 0.0;
        if (retraits == null) retraits = 0.0;

        Map<String, Object> result = new HashMap<>();
        result.put("count", (int) count);
        result.put("sumDepots", depots.floatValue());
        result.put("sumRetraits", retraits.floatValue());
        return result;
    }

    /**
     * Crée un nouveau compte bancaire
     * @param input Objet contenant les informations du compte à créer
     * @return Le compte créé
     * @throws Exception En cas d'erreur lors du formatage de la date
     */
    @MutationMapping
    public Compte saveCompte(@Argument CompteInput input) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Compte c = new Compte();
        c.setSolde(input.getSolde());
        c.setDateCreation(sdf.parse(input.getDateCreation()));
        c.setType(input.getType());
        return compteRepository.save(c);
    }

    /**
     * Ajoute une nouvelle transaction (dépôt ou retrait) à un compte
     * Met automatiquement à jour le solde du compte concerné
     * 
     * @param input Objet contenant les informations de la transaction
     * @return La transaction créée
     * @throws Exception En cas d'erreur lors du formatage de la date ou si le compte n'est pas trouvé
     */
    @MutationMapping
    public Transaction addTransaction(@Argument TransactionInput input) throws Exception {
        // Vérifie que le compte existe
        Compte compte = compteRepository.findById(input.getCompteId())
                .orElseThrow(() -> new RuntimeException("Compte non trouvé"));

        // Crée une nouvelle transaction
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Transaction t = new Transaction();
        t.setMontant(input.getMontant());
        t.setDate(sdf.parse(input.getDate()));
        t.setType(input.getType());
        t.setCompte(compte);

        // Met à jour le solde du compte en fonction du type de transaction
        if (input.getType() == TypeTransaction.DEPOT) {
            compte.setSolde(compte.getSolde() + input.getMontant());
        } else {
            compte.setSolde(compte.getSolde() - input.getMontant());
        }

        // Sauvegarde les modifications
        compteRepository.save(compte);
        return transactionRepository.save(t);
    }
}