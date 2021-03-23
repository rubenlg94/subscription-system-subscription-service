package com.rubenlg94.subscriptionservice.services;

import com.rubenlg94.subscriptionservice.exceptions.ConflictException;
import com.rubenlg94.subscriptionservice.exceptions.NotFoundException;
import com.rubenlg94.subscriptionservice.models.SubscriptionModel;

import java.util.List;

public interface SubscriptionService {

    List<SubscriptionModel> findAll();

    Long createSubscription(SubscriptionModel subscriptionModel) throws ConflictException, NotFoundException;

    Long cancelSubscription(Long subscriptionId) throws NotFoundException, ConflictException;

    SubscriptionModel findById(Long subscriptionId) throws NotFoundException;

    Long cancelSubscription(String email, Long campaignId) throws NotFoundException, ConflictException;

}
