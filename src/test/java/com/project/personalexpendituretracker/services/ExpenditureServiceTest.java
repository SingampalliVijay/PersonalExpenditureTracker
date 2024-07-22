package com.project.personalexpendituretracker.services;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.personalexpendituretracker.entities.Category;
import com.project.personalexpendituretracker.entities.Expenditure;
import com.project.personalexpendituretracker.entities.PaymentMode;
import com.project.personalexpendituretracker.repositories.CategoryRepo;
import com.project.personalexpendituretracker.repositories.ExpenditureRepo;
import com.project.personalexpendituretracker.repositories.PaymentModeRepo;

@SpringBootTest
public class ExpenditureServiceTest {

    @Mock
    private CategoryRepo categoryRepo;

    @Mock
    private PaymentModeRepo paymentModeRepo;

    @InjectMocks
    private ExpenditureService expenditureService;
    @Mock
    private ExpenditureRepo expenditureRepo;
    Expenditure expenditure;
    Category category;
    PaymentMode paymentMode;

    @Test
    void testDeleteExpenditureById() {
        category = new Category("Education");
        categoryRepo.save(category);
        paymentMode = new PaymentMode(1, "Credit Card", "user3", "Used Credit Card");
        paymentModeRepo.save(paymentMode);
        expenditure = new Expenditure(paymentMode.getUsername(), 20000, LocalDate.of(2023, 6, 14),
                "Course Fee Payment",
                "Payed for Online Course", "Education", paymentMode, category);
        expenditureRepo.save(expenditure);
        when(expenditureRepo.findById(expenditure.getId())).thenReturn(Optional.of(expenditure));
        doNothing().when(expenditureRepo).deleteById(expenditure.getId());
        assertThat(expenditureService.deleteExpenditureById(expenditure.getId()))
                .isEqualTo("Data Deleted for " + expenditure.getId());
        verify(expenditureRepo).deleteById(expenditure.getId());
    }

    
}
