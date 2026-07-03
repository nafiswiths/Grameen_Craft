package com.grameencraft.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.math.BigDecimal;

@Entity
@Table(name = "grameen_orders")
public class Order {

    @Id
    private String id;

    @Column(nullable = false)
    private String date;

    @Column(name = "\"itemsCount\"", nullable = false)
    private Integer itemsCount;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(nullable = false)
    private String status;

    @Column(name = "\"productName\"")
    private String productName;

    @Column(name = "\"userEmail\"")
    private String userEmail;

    public Order() {
    }

    public Order(String id, String date, Integer itemsCount, BigDecimal total, String status, String productName, String userEmail) {
        this.id = id;
        this.date = date;
        this.itemsCount = itemsCount;
        this.total = total;
        this.status = status;
        this.productName = productName;
        this.userEmail = userEmail;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(Integer itemsCount) {
        this.itemsCount = itemsCount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
