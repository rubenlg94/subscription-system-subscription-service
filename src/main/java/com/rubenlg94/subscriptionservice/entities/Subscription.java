package com.rubenlg94.subscriptionservice.entities;

import com.rubenlg94.subscriptionservice.constants.DatabaseValues;
import com.rubenlg94.subscriptionservice.constants.Genders;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = DatabaseValues.Subscription.TABLE_NAME)
public class Subscription implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DatabaseValues.Subscription.ID)
    private Long id;

    @Column(name = DatabaseValues.Subscription.EMAIL, nullable = false)
    private String email;

    @Column(name = DatabaseValues.Subscription.FIRST_NAME)
    private String firstName;

    @Column(name = DatabaseValues.Subscription.GENDER)
    private Genders gender;

    @Column(name = DatabaseValues.Subscription.CONSENT, nullable = false)
    private Boolean consent;

    @Column(name = DatabaseValues.Subscription.CREATED_ON)
    private LocalDateTime createdOn;

    @Column(name = DatabaseValues.Subscription.UPDATED_ON)
    private LocalDateTime updatedOn;

    @Column(name = DatabaseValues.Subscription.ENABLED, nullable = false)
    private Boolean enabled;

    @Column(name = DatabaseValues.Subscription.CAMPAIGN_ID, nullable = false)
    private Long campaignId;

    @Column(name = DatabaseValues.Subscription.BIRTH_DATE, nullable = false)
    private LocalDate birthDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Genders getGender() {
        return gender;
    }

    public void setGender(Genders gender) {
        this.gender = gender;
    }

    public Boolean getConsent() {
        return consent;
    }

    public void setConsent(Boolean consent) {
        this.consent = consent;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
