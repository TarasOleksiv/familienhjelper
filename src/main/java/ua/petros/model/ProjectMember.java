package ua.petros.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;


@Entity
@Table(name = "project_member")
@Proxy(lazy = false)
public class ProjectMember implements Serializable {

    @Id
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @Column(name = "id", columnDefinition = "UUID")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

    @Column(name = "pledge")
    private BigDecimal pledge;

    @Temporal(TemporalType.DATE)
    @Column(name = "startPledge")
    private Date startPledge;

    @Temporal(TemporalType.DATE)
    @Column(name = "stopPledge")
    private Date stopPledge;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public BigDecimal getPledge() {
        return pledge;
    }

    public void setPledge(BigDecimal pledge) {
        this.pledge = pledge;
    }

    public Date getStartPledge() {
        return startPledge;
    }

    public void setStartPledge(Date startPledge) {
        this.startPledge = startPledge;
    }

    public Date getStopPledge() {
        return stopPledge;
    }

    public void setStopPledge(Date stopPledge) {
        this.stopPledge = stopPledge;
    }
}
