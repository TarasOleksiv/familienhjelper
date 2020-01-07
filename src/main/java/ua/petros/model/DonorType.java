package ua.petros.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Taras on 18-12-2019.
 */

@Entity
@Table(name = "donor_types")
@Proxy(lazy = false)
public class DonorType {
    
    @Id
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @Column(name = "id", columnDefinition = "UUID")
    private UUID id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "donorType", fetch = FetchType.EAGER)
    private Set<Member> members;

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

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return name;
    }
}
