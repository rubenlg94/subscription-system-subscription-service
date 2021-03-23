package com.rubenlg94.subscriptionservice.models;

import com.rubenlg94.subscriptionservice.constants.Genders;
import com.rubenlg94.subscriptionservice.entities.Subscription;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SubscriptionModel {

    private Long id;
    private String email;
    private String firstName;
    private Genders gender;
    private Boolean consent;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Boolean enabled;
    private Long campaignId;
    private LocalDate birthDate;


    public static SubscriptionModel from(Subscription subscription) {
        SubscriptionModel subscriptionModel = new SubscriptionModel();
        subscriptionModel.setId(subscription.getId());
        subscriptionModel.setEmail(subscription.getEmail());
        subscriptionModel.setFirstName(subscription.getFirstName());
        subscriptionModel.setGender(subscription.getGender());
        subscriptionModel.setConsent(subscription.getConsent());
        subscriptionModel.setCreatedOn(subscription.getCreatedOn());
        subscriptionModel.setUpdatedOn(subscription.getUpdatedOn());
        subscriptionModel.setCampaignId(subscription.getCampaignId());
        subscriptionModel.setEnabled(subscription.getEnabled());
        subscriptionModel.setBirthDate(subscription.getBirthDate());
        return subscriptionModel;
    }


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
