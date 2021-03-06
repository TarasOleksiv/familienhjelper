package ua.petros.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Taras on 18-12-2019.
 */

@Entity
@Table(name = "transaction_types")
@Proxy(lazy = false)
public class TransactionType {
    
    @Id
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @Column(name = "id", columnDefinition = "UUID")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "isDonation")
    private boolean isDonation;

    @OneToMany(mappedBy = "transactionType", fetch = FetchType.LAZY)
    private Set<Transaction> transactions;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public boolean getIsDonation() {
        return isDonation;
    }

    public void setIsDonation(boolean isDonation) {
        this.isDonation = isDonation;
    }

    @Override
    public String toString() {
        return name;
    }
}
