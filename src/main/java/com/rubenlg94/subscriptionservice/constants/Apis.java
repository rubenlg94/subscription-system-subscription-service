package com.rubenlg94.subscriptionservice.constants;

public enum Apis {

    EMAILING_SERVICE("emailing-service");

    private String name;

    Apis(String name) {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
