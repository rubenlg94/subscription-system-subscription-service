package com.rubenlg94.subscriptionservice.constants;

public final class Endpoints {

    public static class EmailingService {

        private EmailingService() {
        }

        public static final String CREATED_SUBSCRIPTION_ENPOINT = "/email-notifications/created-subscription";
        public static final String CANCELLED_SUBSCRIPTION_ENPOINT = "/email-notifications/cancelled-subscription";

    }

}
