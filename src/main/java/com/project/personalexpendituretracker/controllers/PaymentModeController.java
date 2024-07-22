package com.project.personalexpendituretracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.personalexpendituretracker.dto.PaymentModeDto;
import com.project.personalexpendituretracker.entities.PaymentMode;
import com.project.personalexpendituretracker.services.PaymentModeService;

@RestController
@RequestMapping("/paymentMode")
public class PaymentModeController {

    @Autowired
    private PaymentModeService paymentModeService;

    // 9. Add,Delete,Update PaymentMode of Current User
    @PostMapping("/")
    public PaymentMode addPaymentMode(@RequestBody PaymentModeDto paymentModeDto) {
        return paymentModeService.addPaymentMode(paymentModeDto);
    }

    @PutMapping("/{id}")
    public PaymentMode updatePaymentMode(@PathVariable("id") int id,
            @RequestBody PaymentModeDto paymentModeDto) {
        return paymentModeService.updatePaymentMode(id, paymentModeDto);
    }

    @DeleteMapping("/{id}")
    public String deleteUserDetails(@PathVariable("id") int id) {
        return paymentModeService.deleteUserDetails(id);
    }

    // 10.List Paymentmodes of a Current User
    @GetMapping("/{username}")
    public List<PaymentMode> getPaymentModeByUserName(@PathVariable("username") String userName) {
        return paymentModeService.getPaymentModeByUserName(userName);
    }

    @GetMapping("/get")
    public List<PaymentMode> getAllPaymentModes() {
        return paymentModeService.getAllPaymentModes();
    }
}
