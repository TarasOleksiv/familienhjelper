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
@Table(name = "transactions")
@Proxy(lazy = false)
public class Transaction {
    
    @Id
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @Column(name = "id", columnDefinition = "UUID")
    private UUID id;

    @Column(name = "isIncome")
    private boolean isIncome;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "amountNOK")
    private BigDecimal amountNOK;

    @Temporal(TemporalType.DATE)
    @Column(name = "tradingDate")
    private Date tradingDate;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "projectId", referencedColumnName = "id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "currencyId", referencedColumnName = "id")
    private Currency currency;

    @ManyToOne
    @JoinColumn(name = "fromMemberId", referencedColumnName = "id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "toBeneficiaryId", referencedColumnName = "id")
    private Beneficiary beneficiary;

    @ManyToOne
    @JoinColumn(name = "typeId", referencedColumnName = "id")
    private TransactionType transactionType;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean getIsIncome() {
        return isIncome;
    }

    public void setIsIncome(boolean isIncome) {
        this.isIncome = isIncome;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmountNOK() {
        return amountNOK;
    }

    public void setAmountNOK(BigDecimal amountNOK) {
        this.amountNOK = amountNOK;
    }

    public Date getTradingDate() {
        return tradingDate;
    }

    public void setTradingDate(Date tradingDate) {
        this.tradingDate = tradingDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Beneficiary getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(Beneficiary beneficiary) {
        this.beneficiary = beneficiary;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
