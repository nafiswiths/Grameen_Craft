package com.grameencraft.backend.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "grameen_products")
public class Product {

    @Id
    private String id;

    @Column(name = "\"nameEn\"", nullable = false)
    private String nameEn;

    @Column(name = "\"nameBn\"", nullable = false)
    private String nameBn;

    @Column(nullable = false)
    private BigDecimal price;

    private BigDecimal rating;

    @Column(name = "\"reviewsCount\"")
    private Integer reviewsCount;

    private String category;

    @Column(columnDefinition = "TEXT")
    private String image;

    // Hibernate 6 maps text[] directly if we use list with custom element type
    @Column(name = "\"gallery\"", columnDefinition = "text[]")
    private List<String> gallery;

    @Column(name = "\"artisanId\"")
    private String artisanId;

    @Column(name = "\"materialsEn\"", columnDefinition = "text[]")
    private List<String> materialsEn;

    @Column(name = "\"materialsBn\"", columnDefinition = "text[]")
    private List<String> materialsBn;

    @Column(name = "\"deliveryDays\"")
    private Integer deliveryDays;

    @Column(name = "\"descEn\"", columnDefinition = "TEXT")
    private String descEn;

    @Column(name = "\"descBn\"", columnDefinition = "TEXT")
    private String descBn;

    @Column(name = "\"inStock\"")
    private Boolean inStock = true;

    private Boolean featured = false;

    private Boolean trending = false;

    public Product() {
    }

    public Product(String id, String nameEn, String nameBn, BigDecimal price, BigDecimal rating, 
                   Integer reviewsCount, String category, String image, List<String> gallery, 
                   String artisanId, List<String> materialsEn, List<String> materialsBn, 
                   Integer deliveryDays, String descEn, String descBn, Boolean inStock, 
                   Boolean featured, Boolean trending) {
        this.id = id;
        this.nameEn = nameEn;
        this.nameBn = nameBn;
        this.price = price;
        this.rating = rating;
        this.reviewsCount = reviewsCount;
        this.category = category;
        this.image = image;
        this.gallery = gallery;
        this.artisanId = artisanId;
        this.materialsEn = materialsEn;
        this.materialsBn = materialsBn;
        this.deliveryDays = deliveryDays;
        this.descEn = descEn;
        this.descBn = descBn;
        this.inStock = inStock;
        this.featured = featured;
        this.trending = trending;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameBn() {
        return nameBn;
    }

    public void setNameBn(String nameBn) {
        this.nameBn = nameBn;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public Integer getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(Integer reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getGallery() {
        return gallery;
    }

    public void setGallery(List<String> gallery) {
        this.gallery = gallery;
    }

    public String getArtisanId() {
        return artisanId;
    }

    public void setArtisanId(String artisanId) {
        this.artisanId = artisanId;
    }

    public List<String> getMaterialsEn() {
        return materialsEn;
    }

    public void setMaterialsEn(List<String> materialsEn) {
        this.materialsEn = materialsEn;
    }

    public List<String> getMaterialsBn() {
        return materialsBn;
    }

    public void setMaterialsBn(List<String> materialsBn) {
        this.materialsBn = materialsBn;
    }

    public Integer getDeliveryDays() {
        return deliveryDays;
    }

    public void setDeliveryDays(Integer deliveryDays) {
        this.deliveryDays = deliveryDays;
    }

    public String getDescEn() {
        return descEn;
    }

    public void setDescEn(String descEn) {
        this.descEn = descEn;
    }

    public String getDescBn() {
        return descBn;
    }

    public void setDescBn(String descBn) {
        this.descBn = descBn;
    }

    public Boolean getInStock() {
        return inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public Boolean getTrending() {
        return trending;
    }

    public void setTrending(Boolean trending) {
        this.trending = trending;
    }
}
