package com.rubenlg94.subscriptionservice.repositories;

import com.rubenlg94.subscriptionservice.entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    Subscription findSubscriptionByIdEquals(Long subscriptionPk);

    Subscription findSubscriptionByEmailEqualsAndAndCampaignIdEquals(String email, Long campaignId);

}
