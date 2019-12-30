package ua.petros.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Taras on 18-12-2019.
 */

@Entity
@Table(name = "projects")
@Proxy(lazy = false)
public class Project {

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

}
