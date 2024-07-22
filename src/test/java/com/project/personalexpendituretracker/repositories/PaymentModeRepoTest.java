package com.project.personalexpendituretracker.repositories;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.project.personalexpendituretracker.entities.PaymentMode;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PaymentModeRepoTest {

    @Autowired
    private PaymentModeRepo paymentModeRepo;
    PaymentMode paymentMode1;
    PaymentMode paymentMode2;

    @BeforeEach
    void setUp() {
        paymentMode1 = new PaymentMode(1, "Credit Card", "user3", "Used Credit Card");
        paymentMode2 = new PaymentMode(2, "Debit Card", "user3", "Used Debit Card");

        paymentModeRepo.save(paymentMode1);
        paymentModeRepo.save(paymentMode2);
    }

    @AfterEach
    void tearDown() {
        paymentModeRepo.deleteAll();
    }

    @Test
    void testFindByUsername() {
        List<PaymentMode> pay = paymentModeRepo.findByUsername("user3");
        assertThat(pay).isNotNull();
        assertThat(pay.get(0).getUsername()).isEqualTo(paymentMode1.getUsername());
        assertThat(pay.get(1).getUsername()).isEqualTo(paymentMode2.getUsername());
    }
}



