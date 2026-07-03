package com.grameencraft.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.math.BigDecimal;

@Entity
@Table(name = "grameen_artisans")
public class Artisan {

    @Id
    private String id;

    @Column(name = "\"nameEn\"", nullable = false)
    private String nameEn;

    @Column(name = "\"nameBn\"", nullable = false)
    private String nameBn;

    @Column(name = "\"districtEn\"")
    private String districtEn;

    @Column(name = "\"districtBn\"")
    private String districtBn;

    @Column(name = "\"experienceYears\"")
    private Integer experienceYears;

    @Column(columnDefinition = "TEXT")
    private String avatar;

    @Column(name = "\"bioEn\"", columnDefinition = "TEXT")
    private String bioEn;

    @Column(name = "\"bioBn\"", columnDefinition = "TEXT")
    private String bioBn;

    private BigDecimal rating;

    @Column(name = "\"specialtyEn\"")
    private String specialtyEn;

    @Column(name = "\"specialtyBn\"")
    private String specialtyBn;

    public Artisan() {
    }

    public Artisan(String id, String nameEn, String nameBn, String districtEn, String districtBn, 
                   Integer experienceYears, String avatar, String bioEn, String bioBn, 
                   BigDecimal rating, String specialtyEn, String specialtyBn) {
        this.id = id;
        this.nameEn = nameEn;
        this.nameBn = nameBn;
        this.districtEn = districtEn;
        this.districtBn = districtBn;
        this.experienceYears = experienceYears;
        this.avatar = avatar;
        this.bioEn = bioEn;
        this.bioBn = bioBn;
        this.rating = rating;
        this.specialtyEn = specialtyEn;
        this.specialtyBn = specialtyBn;
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

    public String getDistrictEn() {
        return districtEn;
    }

    public void setDistrictEn(String districtEn) {
        this.districtEn = districtEn;
    }

    public String getDistrictBn() {
        return districtBn;
    }

    public void setDistrictBn(String districtBn) {
        this.districtBn = districtBn;
    }

    public Integer getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(Integer experienceYears) {
        this.experienceYears = experienceYears;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBioEn() {
        return bioEn;
    }

    public void setBioEn(String bioEn) {
        this.bioEn = bioEn;
    }

    public String getBioBn() {
        return bioBn;
    }

    public void setBioBn(String bioBn) {
        this.bioBn = bioBn;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public String getSpecialtyEn() {
        return specialtyEn;
    }

    public void setSpecialtyEn(String specialtyEn) {
        this.specialtyEn = specialtyEn;
    }

    public String getSpecialtyBn() {
        return specialtyBn;
    }

    public void setSpecialtyBn(String specialtyBn) {
        this.specialtyBn = specialtyBn;
    }
}
