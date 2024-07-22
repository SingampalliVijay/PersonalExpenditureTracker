package com.project.personalexpendituretracker.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.personalexpendituretracker.entities.Expenditure;

public interface ExpenditureRepo extends JpaRepository<Expenditure, Integer> {

    Page<Expenditure> findByPaymentModeCode(int paymentMode, PageRequest pageable);

    Page<Expenditure> findByCategoryCode(int catCode,PageRequest pageable);

    Page<Expenditure> findBySpentOnBetween(LocalDate startDate, LocalDate endDate, PageRequest pageable);

    List<Expenditure> findTop2ByOrderByAmountDesc();

    List<Expenditure> findByTagsIn(List<String> tagList);

    @Query("SELECT e FROM Expenditure e WHERE FUNCTION('MONTH', e.spentOn) = :month AND FUNCTION('YEAR', e.spentOn) = :year")
    List<Expenditure> findByCategoryAndMonthAndYear(@Param("month") int month,
            @Param("year") int year);

}
