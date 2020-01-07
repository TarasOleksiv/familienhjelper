package ua.petros.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "users")
@Proxy(lazy = false)
public class User {

    //@Id
    //@GeneratedValue(generator = "uuid2")
    //@GenericGenerator(name = "uuid2", strategy = "uuid2")
    //@Column(name = "id",columnDefinition = "BINARY(16)")
    @Id
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @Column(name = "id", columnDefinition = "UUID")
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile1")
    private String mobile1;

    @Column(name = "mobile2")
    private String mobile2;

    @Column(name = "address")
    private String address;

    @Column(name = "account")
    private String account;

    @Column(name = "bank")
    private String bank;

    @Column(name = "password")
    private String password;

    @Transient
    private String confirmPassword;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Beneficiary> beneficiaries;

    @OneToMany(mappedBy = "fieldContactUser", fetch = FetchType.EAGER)
    private Set<Project> projectsFieldContact;

    @OneToMany(mappedBy = "fuUser", fetch = FetchType.EAGER)
    private Set<Project> projectsFU;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile1() {
        return mobile1;
    }

    public void setMobile1(String mobile1) {
        this.mobile1 = mobile1;
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Beneficiary> getBeneficiaries() {
        return beneficiaries;
    }

    public void setBeneficiaries(Set<Beneficiary> beneficiaries) {
        this.beneficiaries = beneficiaries;
    }

    public Set<Project> getProjectsFieldContact() {
        return projectsFieldContact;
    }

    public void setProjectsFieldContact(Set<Project> projectsFieldContact) {
        this.projectsFieldContact = projectsFieldContact;
    }

    public Set<Project> getProjectsFU() {
        return projectsFU;
    }

    public void setProjectsFU(Set<Project> projectsFU) {
        this.projectsFU = projectsFU;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
