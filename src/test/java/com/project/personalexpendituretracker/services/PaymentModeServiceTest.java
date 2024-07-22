package com.project.personalexpendituretracker.services;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.project.personalexpendituretracker.dto.PaymentModeDto;
import com.project.personalexpendituretracker.entities.PaymentMode;
import com.project.personalexpendituretracker.repositories.PaymentModeRepo;

@SpringBootTest
public class PaymentModeServiceTest {

    @Mock
    private PaymentModeRepo paymentModeRepo;

    @Autowired
    private PaymentModeService paymentModeService;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("admin");
    }

    @Test
    void testAddPaymentMode() {
        PaymentMode paymentMode = new PaymentMode(1, "Credit Card", "admin", "Used  Credit card");
        PaymentModeDto dto = new PaymentModeDto();
        dto.setName(paymentMode.getName());
        paymentMode.getUsername();
        dto.setRemarks(paymentMode.getRemarks());
        when(paymentModeRepo.save(paymentMode)).thenReturn(paymentMode);
        assertThat(paymentModeService.addPaymentMode(dto)).isNotNull();
    }
}
