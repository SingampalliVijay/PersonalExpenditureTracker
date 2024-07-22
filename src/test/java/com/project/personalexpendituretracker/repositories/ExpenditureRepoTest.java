package com.project.personalexpendituretracker.repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.project.personalexpendituretracker.entities.Category;
import com.project.personalexpendituretracker.entities.Expenditure;
import com.project.personalexpendituretracker.entities.PaymentMode;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ExpenditureRepoTest {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private PaymentModeRepo paymentModeRepo;

    @Autowired
    private ExpenditureRepo expenditureRepo;
    Expenditure expenditure1;
    Expenditure expenditure2;
    Category category1;
    Category category2;
    PaymentMode paymentMode;

    @BeforeEach
    void setUp() {
        category1 = new Category("Education");
        categoryRepo.save(category1);
        category2 = new Category("Groceries");
        categoryRepo.save(category2);
        paymentMode = new PaymentMode(1, "Credit Card", "user3", "Used Credit Card");
        paymentModeRepo.save(paymentMode);
        expenditure1 = new Expenditure(paymentMode.getUsername(), 20000, LocalDate.of(2023, 6, 14),
                "Course Fee Payment",
                "Payed for Online Course", "Education", paymentMode, category1);
        expenditureRepo.save(expenditure1);
        expenditure2 = new Expenditure(paymentMode.getUsername(), 5000, LocalDate.of(2023, 6, 21),
                "Groceries Purchase", "Bought weekly groceries", "Groceries", paymentMode, category2);
        expenditureRepo.save(expenditure2);

    }

    @AfterEach
    void tearDown() {
        expenditureRepo.deleteAll();
        paymentModeRepo.deleteAll();
        categoryRepo.deleteAll();
    }

    @Test
    void testFindByCategoryAndMonthAndYear() {
        int month = 6;
        int year = 2023;
        List<Expenditure> expenses = expenditureRepo.findByCategoryAndMonthAndYear(month, year);
        assertThat(expenses).isNotNull();
        Expenditure exp = expenses.get(0);
        assertThat(exp.getSpentOn().getMonthValue()).isEqualTo(month);
        assertThat(exp.getSpentOn().getYear()).isEqualTo(year);
    }

    @Test
    void testFindByTagsIn() {
        List<String> tags = new ArrayList<>();
        tags.add("Education");
        List<Expenditure> expenses = expenditureRepo.findByTagsIn(tags);
        assertThat(expenses).isNotNull();
        Expenditure exp = expenses.get(0);
        assertThat(exp.getTags()).isNotEmpty();
        assertThat(exp.getTags()).containsAnyOf("Education");

    }

    @Test
    void testFindTop2ByOrderByAmountDesc() {
        List<Expenditure> top2Expenditures = expenditureRepo.findTop2ByOrderByAmountDesc();
        assertThat(top2Expenditures).isNotNull();
        assertThat(top2Expenditures).isSortedAccordingTo(
                (expenditure1, expenditure2) -> -Double.compare(expenditure1.getAmount(), expenditure2.getAmount()));
    }

    @Test
    void testFindByCategoryCode() {
        PageRequest pageable = PageRequest.of(0, 10);
        Page<Expenditure> expendituresPage = expenditureRepo.findByCategoryCode(category1.getCode(), pageable);
        assertThat(expendituresPage).isNotNull();
        assertThat(expendituresPage.getContent()).isNotEmpty();
        for (Expenditure expenditure : expendituresPage.getContent()) {
            assertThat(expenditure.getCategory().getCode()).isEqualTo(category1.getCode());
        }
    }

    @Test
    void testFindByPaymentModeCode() {
        PageRequest pageable = PageRequest.of(0, 10);
        Page<Expenditure> expendituresPage = expenditureRepo.findByPaymentModeCode(paymentMode.getCode(), pageable);
        assertThat(expendituresPage).isNotNull();
        assertThat(expendituresPage.getContent()).isNotEmpty();
        for (Expenditure expenditure : expendituresPage.getContent()) {
            assertThat(expenditure.getPaymentMode().getCode()).isEqualTo(paymentMode.getCode());
        }
    }

    @Test
    void testFindBySpentOnBetween() {
        LocalDate startDate = LocalDate.of(2023, 6, 11);
        LocalDate endDate = LocalDate.of(2023, 6, 20);
        PageRequest pageable = PageRequest.of(0, 10);
        Page<Expenditure> exp = expenditureRepo.findBySpentOnBetween(startDate, endDate, pageable);
        assertThat(exp).isNotNull();
        assertThat(exp.getContent());
        for (Expenditure expenditure : exp.getContent()) {
            assertThat(expenditure.getSpentOn()).isEqualTo(expenditure1.getSpentOn());
        }
    }

}
