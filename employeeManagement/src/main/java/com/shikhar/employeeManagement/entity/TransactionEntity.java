package com.shikhar.employeeManagement.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="transaction")
public class TransactionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private double amount;

    private String emailAdd;

    public void setEmailAdd(String emailAdd) {
        this.emailAdd = emailAdd;
    }

    public String getEmailAdd() {
        return emailAdd;
    }

    public TransactionEntity() {
    }

    public TransactionEntity(Long id, Long userId, double amount, String emailAdd) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.emailAdd = emailAdd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
