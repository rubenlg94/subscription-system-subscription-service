package com.rubenlg94.subscriptionservice.services.impl;

import com.rubenlg94.subscriptionservice.entities.Subscription;
import com.rubenlg94.subscriptionservice.exceptions.ConflictException;
import com.rubenlg94.subscriptionservice.exceptions.NotFoundException;
import com.rubenlg94.subscriptionservice.models.SubscriptionModel;
import com.rubenlg94.subscriptionservice.repositories.SubscriptionRepository;
import com.rubenlg94.subscriptionservice.services.EmailingService;
import com.rubenlg94.subscriptionservice.services.SubscriptionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final EmailingService emailingService;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, EmailingService emailingService) {
        this.subscriptionRepository = subscriptionRepository;
        this.emailingService = emailingService;
    }

    @Override
    public List<SubscriptionModel> findAll() {
        return subscriptionRepository.findAll().stream().map(SubscriptionModel::from).collect(Collectors.toList());
    }

    @Override
    public Long createSubscription(SubscriptionModel subscriptionModel) throws ConflictException {
        Subscription subscription = subscriptionRepository.findSubscriptionByEmailEqualsAndAndCampaignIdEquals(subscriptionModel.getEmail(), subscriptionModel.getCampaignId());
        if (subscription != null) {
            if (!subscription.getEnabled()) {
                subscription.setEnabled(true);
                subscription.setUpdatedOn(LocalDateTime.now());
            } else {
                throw new ConflictException("Subscription already exist");
            }
        } else {
            subscription = new Subscription();
            subscription.setEmail(subscriptionModel.getEmail());
            subscription.setCampaignId(subscriptionModel.getCampaignId());
            subscription.setFirstName(subscriptionModel.getFirstName());
            subscription.setGender(subscriptionModel.getGender());
            subscription.setConsent(subscriptionModel.getConsent());
            subscription.setCreatedOn(LocalDateTime.now());
            subscription.setEnabled(subscriptionModel.getConsent());
            subscription.setBirthDate(subscriptionModel.getBirthDate());
        }
        try {
            subscription = subscriptionRepository.save(subscription);
            CompletableFuture.runAsync(() -> emailingService.sendCreatedSubscriptionEmail(subscriptionModel.getEmail()));
            return subscription.getId();
        } catch (Exception e) {
            throw new ConflictException("Error creating subscription. Try again in a few minutes.");
        }
    }

    @Override
    public Long cancelSubscription(Long subscriptionId) throws NotFoundException, ConflictException {
        Subscription subscription = subscriptionRepository.findSubscriptionByIdEquals(subscriptionId);
        if (subscription != null) {
            subscription.setEnabled(false);
            subscription.setUpdatedOn(LocalDateTime.now());
            try {
                subscription = subscriptionRepository.save(subscription);
                Subscription finalSubscription = subscription;
                CompletableFuture.runAsync(() -> emailingService.sendCancelledSubscriptionEmail(finalSubscription.getEmail()));
                return subscription.getId();
            } catch (Exception e) {
                throw new ConflictException("Error creating subscription. Try again in a few minutes.");
            }
        } else {
            throw new NotFoundException(String.format("Subscription with id %d doesn't exist", subscriptionId));
        }
    }

    @Override
    public SubscriptionModel findById(Long subscriptionId) throws NotFoundException {
        Subscription subscription = subscriptionRepository.findSubscriptionByIdEquals(subscriptionId);
        if (subscription != null) {
            return SubscriptionModel.from(subscription);
        } else {
            throw new NotFoundException(String.format("Subscription with id %d doesn't exist", subscriptionId));
        }
    }

    @Override
    public Long cancelSubscription(String email, Long campaignId) throws NotFoundException, ConflictException {
        Subscription subscription = subscriptionRepository.findSubscriptionByEmailEqualsAndAndCampaignIdEquals(email, campaignId);
        if (subscription != null) {
            return cancelSubscription(subscription.getId());
        } else {
            throw new NotFoundException(String.format("There are no subscriptions for email %s and campaign %d", email, campaignId));
        }
    }

}
