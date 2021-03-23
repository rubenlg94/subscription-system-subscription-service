package com.rubenlg94.subscriptionservice.constants;

public class DatabaseValues {

    public static class Subscription {

        private Subscription() {
        }

        public static final String TABLE_NAME = "subscriptions";
        public static final String ID = "id";
        public static final String EMAIL = "email";
        public static final String FIRST_NAME = "firs_name";
        public static final String GENDER = "gender";
        public static final String CREATED_ON = "created_on";
        public static final String UPDATED_ON = "updated_on";
        public static final String CONSENT = "consent";
        public static final String CAMPAIGN_ID = "campaign_id";
        public static final String ENABLED = "enabled";
        public static final String BIRTH_DATE = "birth_date";
    }

}
