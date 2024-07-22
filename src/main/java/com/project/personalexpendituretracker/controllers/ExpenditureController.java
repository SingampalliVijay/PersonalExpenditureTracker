package com.project.personalexpendituretracker.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.personalexpendituretracker.dto.CategoryTotalAmountDto;
import com.project.personalexpendituretracker.dto.ExpenditureDto;
import com.project.personalexpendituretracker.dto.ExpenditurePostDto;
import com.project.personalexpendituretracker.services.ExpenditureService;

@RestController
@RequestMapping("/expenditure")
public class ExpenditureController {

    @Autowired
    private ExpenditureService expenditureService;

    // 2.List Expenses of a User By CatCode
    @GetMapping("/PageSortingByCategory")
    public List<ExpenditureDto> getExpensesByCategorySorted(
            @RequestParam("pageNum") int pageNumber,
            @RequestParam("size") int size,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam("cat_Code") int code) {
        return expenditureService.getExpensesByCategorySorted(pageNumber, size, direction, sortBy, code);
    }

    // 3.List all expenses by payment mode with pagination and sort by id
    @GetMapping("/PageSortingByPaymentMode")
    public List<ExpenditureDto> getExpensesByPaymentModeSorted(
            @RequestParam("pageNum") int pageNumber,
            @RequestParam("size") int size,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam("paymentMode_Code") int paymentMode) {
        return expenditureService.getExpensesByPaymentModeSorted(pageNumber, size, direction, sortBy, paymentMode);
    }

    // 4.List all expenses between two given dates with pagination and sorted by
    // date in descending order
    @GetMapping("/ExpensesBySortDate")
    public List<ExpenditureDto> getExpensesByDate(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam("pageNum") int pageNumber,
            @RequestParam("size") int size) {
        return expenditureService.getExpensesByDate(startDate, endDate, pageNumber, size);
    }

    // 5. List all Expenses based oon given tags
    @GetMapping("/{tags}")
    public List<ExpenditureDto> getByTagsContaining(@PathVariable("tags") String tags) {
        return expenditureService.getByTagsContaining(tags);
    }

    // // 6. List expenses summary (total amount) for each category in a given month
    @GetMapping("/totalAmountGroupByCategory")
    public List<CategoryTotalAmountDto> totalAmountGroupByCategory(@RequestParam("month") int month,
            @RequestParam("year") int year) {
        return expenditureService.totalAmountGroupByCategory(month, year);
    }

    // 7.List Top 5 Expenses for a User
    @GetMapping("/top5Expenses")
    public List<ExpenditureDto> getTopExpenses() {
        return expenditureService.getTopExpenses();
    }

    // 12. Add A New Expenditure
    @PostMapping("/")
    public ExpenditureDto addExpenditure(@RequestBody ExpenditurePostDto expenditureDto) {
        return expenditureService.addExpenditure(expenditureDto);
    }

    // 8.Allow Delete and Update for Expenditure
    @DeleteMapping("/{id}")
    public String deleteExpenditureById(@PathVariable int id) {
        return expenditureService.deleteExpenditureById(id);
    }

    @PutMapping("/{id}")
    public ExpenditureDto updateExpenditure(@PathVariable int id,
            @RequestBody ExpenditurePostDto expenditureDto) {
        return expenditureService.updateExpenditure(id, expenditureDto);
    }

}
