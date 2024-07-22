package com.project.personalexpendituretracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.personalexpendituretracker.entities.PaymentMode;

public interface PaymentModeRepo extends JpaRepository<PaymentMode, Integer> {

    List<PaymentMode> findByUsername(String username);
}
