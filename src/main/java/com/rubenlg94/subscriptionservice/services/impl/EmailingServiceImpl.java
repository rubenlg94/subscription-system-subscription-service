package com.rubenlg94.subscriptionservice.services.impl;

import com.rubenlg94.subscriptionservice.apifactory.components.APIFactory;
import com.rubenlg94.subscriptionservice.constants.Apis;
import com.rubenlg94.subscriptionservice.constants.Endpoints;
import com.rubenlg94.subscriptionservice.services.EmailingService;
import org.springframework.stereotype.Service;

@Service
public class EmailingServiceImpl implements EmailingService {

    @Override
    public void sendCreatedSubscriptionEmail(String to) {
        try {
            APIFactory.fromApi(Apis.EMAILING_SERVICE.getName()).post(Endpoints.EmailingService.CREATED_SUBSCRIPTION_ENPOINT, to);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendCancelledSubscriptionEmail(String to) {
        try {
            APIFactory.fromApi(Apis.EMAILING_SERVICE.getName()).post(Endpoints.EmailingService.CANCELLED_SUBSCRIPTION_ENPOINT, to);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
