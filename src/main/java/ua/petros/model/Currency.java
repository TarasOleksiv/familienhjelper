package ua.petros.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Taras on 18-12-2019.
 */

@Entity
@Table(name = "currency")
@Proxy(lazy = false)
public class Currency {
    
    @Id
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @Column(name = "id", columnDefinition = "UUID")
    private UUID id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "currency", fetch = FetchType.EAGER)
    private Set<Beneficiary> beneficiaries;

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

}
