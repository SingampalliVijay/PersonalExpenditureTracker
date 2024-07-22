package com.project.personalexpendituretracker.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.personalexpendituretracker.dto.CategoryTotalAmountDto;
import com.project.personalexpendituretracker.dto.ExpenditureDto;
import com.project.personalexpendituretracker.dto.ExpenditurePostDto;
import com.project.personalexpendituretracker.entities.Category;
import com.project.personalexpendituretracker.entities.Expenditure;
import com.project.personalexpendituretracker.entities.PaymentMode;
import com.project.personalexpendituretracker.repositories.CategoryRepo;
import com.project.personalexpendituretracker.repositories.ExpenditureRepo;
import com.project.personalexpendituretracker.repositories.PaymentModeRepo;

@Service
public class ExpenditureService {

    @Autowired
    private ExpenditureRepo expenditureRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private PaymentModeRepo paymentRepo;

    public List<ExpenditureDto> getExpensesByCategorySorted(int pageNumber, int size, Sort.Direction direction,
            String sortBy, int code) {
        try {
            Sort sort = Sort.by(direction, sortBy);
            PageRequest pageable = PageRequest.of(pageNumber - 1, size, sort);
            List<ExpenditureDto> expenditureDto = new ArrayList<>();
            Page<Expenditure> expenditurePage = expenditureRepo.findByCategoryCode(code, pageable);
            if (expenditurePage.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No expenses found.");
            }
            for (Expenditure expenditure : expenditurePage) {
                ExpenditureDto dto = new ExpenditureDto();
                dto.setId(expenditure.getId());
                dto.setUserName(expenditure.getUserName());
                dto.setDescription(expenditure.getDescription());
                dto.setSpentOn(expenditure.getSpentOn());
                dto.setAmount(expenditure.getAmount());
                dto.setCatCode(expenditure.getCategory().getCode());
                dto.setPaymentModeCode(expenditure.getPaymentMode().getCode());
                dto.setRemarks(expenditure.getRemarks());
                dto.setTags(expenditure.getTags());
                expenditureDto.add(dto);
            }
            return expenditureDto;
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to retrieve expenses by Category");
        }
    }

    public List<ExpenditureDto> getExpensesByPaymentModeSorted(int pageNumber, int size, Sort.Direction direction,
            String sortBy, int paymentMode) {
        try {
            Sort sort = Sort.by(direction, sortBy);
            PageRequest pageable = PageRequest.of(pageNumber - 1, size, sort);
            Page<Expenditure> expendituresPage = expenditureRepo.findByPaymentModeCode(paymentMode, pageable);
            if (expendituresPage.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No expenses found for the given Payment Mode.");
            }
            List<ExpenditureDto> expenditureDto = new ArrayList<>();
            for (Expenditure expenditure : expendituresPage) {
                ExpenditureDto dto = new ExpenditureDto();
                dto.setId(expenditure.getId());
                dto.setUserName(expenditure.getUserName());
                dto.setDescription(expenditure.getDescription());
                dto.setSpentOn(expenditure.getSpentOn());
                dto.setAmount(expenditure.getAmount());
                dto.setCatCode(expenditure.getCategory().getCode());
                dto.setPaymentModeCode(expenditure.getPaymentMode().getCode());
                dto.setRemarks(expenditure.getRemarks());
                dto.setTags(expenditure.getTags());
                expenditureDto.add(dto);
            }
            return expenditureDto;
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to retrieve expenses by PaymentMode");
        }
    }

    public List<ExpenditureDto> getByTagsContaining(String tags) {
        List<String> tagList = Arrays.asList(tags.split(","));
        List<Expenditure> expenses = expenditureRepo.findByTagsIn(tagList);
        List<ExpenditureDto> expDto = new ArrayList<>();
        try {
            if (expenses.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Not Found");
            }
            for (Expenditure expenditure : expenses) {
                ExpenditureDto dto = new ExpenditureDto();
                dto.setId(expenditure.getId());
                dto.setUserName(expenditure.getUserName());
                dto.setDescription(expenditure.getDescription());
                dto.setAmount(expenditure.getAmount());
                dto.setPaymentModeCode(expenditure.getPaymentMode().getCode());
                dto.setCatCode(expenditure.getCategory().getCode());
                dto.setRemarks(expenditure.getRemarks());
                dto.setSpentOn(expenditure.getSpentOn());
                dto.setTags(expenditure.getTags());
                expDto.add(dto);
            }
            return expDto;
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Exception Occured while retrieving Data for tags :" + tags);
        }
    }

    public List<CategoryTotalAmountDto> totalAmountGroupByCategory(int month, int year) {
        try {
            CategoryTotalAmountDto catDto = new CategoryTotalAmountDto();
            List<CategoryTotalAmountDto> catTotalAmountDto = new ArrayList<>();
            List<Expenditure> expenditures = expenditureRepo.findByCategoryAndMonthAndYear(month, year);
            double totalAmount = 0;
            for (Expenditure expenditure : expenditures) {
                totalAmount += expenditure.getAmount();
            catDto.setCode(expenditure.getCategory().getCode());
            catDto.setName(expenditure.getCategory().getName());
            }
            catDto.setTotalAmount(totalAmount);
            catTotalAmountDto.add(catDto);
            if (catTotalAmountDto.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No category amount found for the given month and Year");
            }
            return catTotalAmountDto;
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No category amount found for the given month and Year");
        }
    }

    public List<ExpenditureDto> getTopExpenses() {
        try {
            List<Expenditure> topExpenditures = expenditureRepo.findTop2ByOrderByAmountDesc();
            List<ExpenditureDto> expenditureDto = new ArrayList<>();
            for (Expenditure expenditure : topExpenditures) {
                ExpenditureDto dto = new ExpenditureDto();
                dto.setId(expenditure.getId());
                dto.setUserName(expenditure.getUserName());
                dto.setDescription(expenditure.getDescription());
                dto.setSpentOn(expenditure.getSpentOn());
                dto.setAmount(expenditure.getAmount());
                dto.setCatCode(expenditure.getCategory().getCode());
                dto.setPaymentModeCode(expenditure.getPaymentMode().getCode());
                dto.setRemarks(expenditure.getRemarks());
                dto.setTags(expenditure.getTags());
                expenditureDto.add(dto);
            }
            return expenditureDto;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve expenses", e);
        }
    }

    public List<ExpenditureDto> getExpensesByDate(LocalDate startDate, LocalDate endDate, int pageNumber, int size) {
        if (startDate.isAfter(endDate)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "StartDate Must be Before End Date");
        }
        try {
            Sort sort = Sort.by(Sort.Direction.DESC, "spentOn");
            PageRequest pageable = PageRequest.of(pageNumber, size, sort);
            Page<Expenditure> expenditurePage = expenditureRepo.findBySpentOnBetween(startDate, endDate, pageable);
            List<ExpenditureDto> expenditureDto = new ArrayList<>();
            if (expenditurePage.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No expenses found for Between the given SpentOn dates.");
            }
            // Convert Expenditure objects to ExpenditureDto objects
            for (Expenditure expenditure : expenditurePage) {
                ExpenditureDto dto = new ExpenditureDto();
                dto.setId(expenditure.getId());
                dto.setUserName(expenditure.getUserName());
                dto.setAmount(expenditure.getAmount());
                dto.setDescription(expenditure.getDescription());
                dto.setRemarks(expenditure.getRemarks());
                dto.setSpentOn(expenditure.getSpentOn());
                dto.setTags(expenditure.getTags());
                dto.setCatCode(expenditure.getCategory().getCode());
                dto.setPaymentModeCode(expenditure.getPaymentMode().getCode());
                expenditureDto.add(dto);
            }
            return expenditureDto;
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve expenses.");
        }
    }

    public ExpenditureDto addExpenditure(ExpenditurePostDto expenditureDto) {
        try {
            Expenditure exp = new Expenditure();
            exp.setAmount(expenditureDto.getAmount());
            exp.setDescription(expenditureDto.getDescription());
            exp.setRemarks(expenditureDto.getRemarks());
            exp.setSpentOn(expenditureDto.getSpentOn());
            exp.setTags(expenditureDto.getTags());
            Category expend = categoryRepo.findById(expenditureDto.getCatCode())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            exp.setCategory(expend);
            PaymentMode payment = paymentRepo.findById((expenditureDto.getPaymentModeCode()))
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            exp.setPaymentMode(payment);
            exp.setUserName(payment.getUsername());
            Expenditure expenditure = expenditureRepo.save(exp);
            ExpenditureDto expDto = convertToDto(expenditure);
            return expDto;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data Not Inserted");
        }
    }

    public ExpenditureDto updateExpenditure(int id, ExpenditurePostDto expenditureDto) {
        try {
            Expenditure expense = expenditureRepo.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Not Found for ID" + id));
            expense.setAmount(expenditureDto.getAmount());
            expense.setDescription(expenditureDto.getDescription());
            expense.setRemarks(expenditureDto.getRemarks());
            expense.setSpentOn(expenditureDto.getSpentOn());
            expense.setTags(expenditureDto.getTags());
            Category expend = categoryRepo.findById(expenditureDto.getCatCode())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            expense.setCategory(expend);
            PaymentMode payment = paymentRepo.findById((expenditureDto.getPaymentModeCode()))
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            expense.setPaymentMode(payment);
            expense.setUserName(payment.getUsername());
            expenditureRepo.save(expense);
            ExpenditureDto expDto = convertToDto(expense);
            return expDto;
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An Exception occured while updating data");
        }
    }

    public String deleteExpenditureById(int id) {
        try {
            expenditureRepo.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Not Found for " + id));
            expenditureRepo.deleteById(id);
            return "Data Deleted for " + id;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Failed to delete data with ID: " + id);
        }
    }

    // To convert Expenditure into ExpenditureDto
    private ExpenditureDto convertToDto(Expenditure expenditure) {
        ExpenditureDto dto = new ExpenditureDto();
        dto.setId(expenditure.getId());
        dto.setUserName(expenditure.getUserName());
        dto.setAmount(expenditure.getAmount());
        dto.setDescription(expenditure.getDescription());
        dto.setRemarks(expenditure.getRemarks());
        dto.setSpentOn(expenditure.getSpentOn());
        dto.setTags(expenditure.getTags());
        dto.setCatCode(expenditure.getCategory().getCode());
        dto.setPaymentModeCode(expenditure.getPaymentMode().getCode());
        return dto;
    }
}