package ua.petros.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Taras on 18-12-2019.
 */

@Entity
@Table(name = "projects")
@Proxy(lazy = false)
public class Project implements Comparable<Project> {

    @Id
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @Column(name = "id", columnDefinition = "UUID")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "balance")
    private BigDecimal balance;

    @Temporal(TemporalType.DATE)
    @Column(name = "startDate")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "stopDate")
    private Date stopDate;

    @Column(name = "feedback")
    private String feedback;

    @Column(name = "active")
    private boolean active;

    @Column(name = "imageFolderLink")
    private String imageFolderLink;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "fieldContact_id", referencedColumnName = "id")
    private User fieldContactUser;

    @ManyToOne
    @JoinColumn(name = "fu_id", referencedColumnName = "id")
    private User fuUser;

    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    private Set<ProjectMember> projectMembers;

    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    private Set<Image> images;

    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getFieldContactUser() {
        return fieldContactUser;
    }

    public void setFieldContactUser(User fieldContactUser) {
        this.fieldContactUser = fieldContactUser;
    }

    public User getFuUser() {
        return fuUser;
    }

    public void setFuUser(User fuUser) {
        this.fuUser = fuUser;
    }

    public Set<ProjectMember> getProjectMembers() {
        return projectMembers;
    }

    public void setProjectMembers(Set<ProjectMember> projectMembers) {
        this.projectMembers = projectMembers;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public List<Transaction> getSortedTransactions() {
        return transactions.stream().sorted().collect(Collectors.toList());
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getImageFolderLink() {
        return imageFolderLink;
    }

    public void setImageFolderLink(String imageFolderLink) {
        this.imageFolderLink = imageFolderLink;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Project p) {
        return this.getName().compareTo(p.getName());
    }
}
