package ua.petros.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Taras on 18-12-2019.
 */

@Entity
@Table(name = "beneficiaries")
@Proxy(lazy = false)
public class Beneficiary implements Comparable<Beneficiary> {

    @Id
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @Column(name = "id", columnDefinition = "UUID")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "family")
    private String family;

    @Column(name = "description")
    private String description;

    @Column(name = "income")
    private BigDecimal income;

    @Column(name = "active")
    private boolean active;

    @Temporal(TemporalType.DATE)
    @Column(name = "datefield")
    private Date datefield;

    @Column(name = "imageFolderLink")
    private String imageFolderLink;

    @Column(name = "donation")
    private BigDecimal donation;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "helper_id", referencedColumnName = "id")
    private User helperUser;

    @ManyToOne
    @JoinColumn(name = "incometype_id", referencedColumnName = "id")
    private IncomeType incomeType;

    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    private Currency currency;

    @OneToMany(mappedBy = "beneficiary", fetch = FetchType.LAZY)
    private Set<Transaction> transactions;

    @OneToMany(mappedBy = "beneficiary", fetch = FetchType.LAZY)
    private Set<Project> projects;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

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

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public Date getDatefield() {
        return datefield;
    }

    public void setDatefield(Date datefield) {
        this.datefield = datefield;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public IncomeType getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(IncomeType incomeType) {
        this.incomeType = incomeType;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getImageFolderLink() {
        return imageFolderLink;
    }

    public void setImageFolderLink(String imageFolderLink) {
        this.imageFolderLink = imageFolderLink;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public BigDecimal getDonation() {
        return donation;
    }

    public void setDonation(BigDecimal donation) {
        this.donation = donation;
    }

    public User getHelperUser() {
        return helperUser;
    }

    public void setHelperUser(User helperUser) {
        this.helperUser = helperUser;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Beneficiary b) {
        return this.getName().compareTo(b.getName());
    }
}
