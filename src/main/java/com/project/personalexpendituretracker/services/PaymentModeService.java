package com.project.personalexpendituretracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.personalexpendituretracker.dto.PaymentModeDto;
import com.project.personalexpendituretracker.entities.PaymentMode;
import com.project.personalexpendituretracker.repositories.PaymentModeRepo;

@Service
public class PaymentModeService {

    @Autowired
    private PaymentModeRepo paymentRepo;

     private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public PaymentMode addPaymentMode(PaymentModeDto paymentModeDto) {
        try {
            PaymentMode paymentMode = new PaymentMode();
            paymentMode.setName(paymentModeDto.getName());
            paymentMode.setUsername(getCurrentUsername());
            paymentMode.setRemarks(paymentModeDto.getRemarks());
            return paymentRepo.save(paymentMode);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Input : Provide Valid Data");
        }
    }

    public PaymentMode updatePaymentMode(int id, PaymentModeDto paymentModeDto) {
        try {
            PaymentMode paymentMode = paymentRepo.findById(id)
                    .orElseThrow(
                            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found for ID " + id));
            paymentMode.setName(paymentModeDto.getName());
            paymentMode.setUsername(getCurrentUsername());
            paymentMode.setRemarks(paymentModeDto.getRemarks());
            return paymentRepo.save(paymentMode);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Input: Provide Valid Data");
        }
    }

    public String deleteUserDetails(int id) {
        try {
            paymentRepo.findById(id).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found for ID " + id));
            paymentRepo.deleteById(id);
            return "Data Deleted for ID " + id;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Exception Occured while Deleting Data");
        }
    }

    public List<PaymentMode> getPaymentModeByUserName(String userName) {
        try {
            List<PaymentMode> paymentModes = paymentRepo.findByUsername(userName);
            if (paymentModes.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No PaymentMode found for the Username");
            } else {
                return paymentModes;
            }
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to retrieve Payment Modes for username: " + userName);
        }
    }

    public List<PaymentMode> getAllPaymentModes() {
        try {
            List<PaymentMode> paymentModes = paymentRepo.findAll();
            return paymentModes;
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Exception occured while Retrieving Data");
        }
    }
}
