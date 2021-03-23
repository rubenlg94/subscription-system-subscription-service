package com.rubenlg94.subscriptionservice.services;

public interface EmailingService {

    void sendCreatedSubscriptionEmail(String to);

    void sendCancelledSubscriptionEmail(String to);

}
