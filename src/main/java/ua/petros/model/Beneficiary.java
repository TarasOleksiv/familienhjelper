package ua.petros.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Taras on 18-12-2019.
 */

@Entity
@Table(name = "beneficiaries")
@Proxy(lazy = false)
public class Beneficiary {

    @Id
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @Column(name = "id", columnDefinition = "UUID")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "family")
    private String family;

    @Column(name = "description")
    private String description;

    @Column(name = "income")
    private BigDecimal income;

    @Temporal(TemporalType.DATE)
    @Column(name = "datafield")
    private Date date;

}
