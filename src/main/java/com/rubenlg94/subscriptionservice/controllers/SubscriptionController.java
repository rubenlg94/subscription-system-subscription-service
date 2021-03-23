package com.rubenlg94.subscriptionservice.controllers;

import com.rubenlg94.subscriptionservice.exceptions.ConflictException;
import com.rubenlg94.subscriptionservice.exceptions.NotFoundException;
import com.rubenlg94.subscriptionservice.models.SubscriptionModel;
import com.rubenlg94.subscriptionservice.services.SubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public ResponseEntity<?> createSubscription(@RequestBody SubscriptionModel subscriptionModel) throws ConflictException, NotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionService.createSubscription(subscriptionModel));
    }

    @GetMapping("/{subscriptionId}/details")
    public ResponseEntity<?> getDetailsOfSubscription(@PathVariable Long subscriptionId) throws NotFoundException {
        return ResponseEntity.ok(subscriptionService.findById(subscriptionId));
    }

    @PutMapping("/{subscriptionId}/cancel")
    public ResponseEntity<?> cancelSubscription(@PathVariable Long subscriptionId) throws NotFoundException, ConflictException {
        return ResponseEntity.ok(subscriptionService.cancelSubscription(subscriptionId));
    }

    @PutMapping("/cancel")
    public ResponseEntity<?> cancelSubscription(@RequestParam String email,
                                                @RequestParam Long campaignId) throws NotFoundException, ConflictException {
        return ResponseEntity.ok(subscriptionService.cancelSubscription(email, campaignId));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllSubscriptions() {
        return ResponseEntity.ok(subscriptionService.findAll());
    }

}
