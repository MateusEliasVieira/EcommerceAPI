package com.ecommerce.api.api.controller;
// SDK de Mercado Pago

import com.ecommerce.api.api.dto.PaymentPreferenceDTO;
import com.ecommerce.api.domain.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/payment")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @PostMapping("/preference")
    public ResponseEntity<String> paymentPreference(@RequestBody PaymentPreferenceDTO paymentPreferenceDTO) {
        String preferenceId = service.createPreference(paymentPreferenceDTO);
        return new ResponseEntity<String>(preferenceId, HttpStatus.OK);
    }

    @GetMapping("/success")
    public void paymentSuccess() {

    }

    @GetMapping("/failure")
    public void paymentFailure() {

    }

    @GetMapping("/pending")
    public void paymentPending() {

    }


}