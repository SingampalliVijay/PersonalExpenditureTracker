package com.project.personalexpendituretracker.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.personalexpendituretracker.dto.CategoryDto;
import com.project.personalexpendituretracker.entities.Category;
import com.project.personalexpendituretracker.repositories.CategoryRepo;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public CategoryDto addCategory( Category category) {
        try {
            categoryRepo.save(category);
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setCode(category.getCode());
            categoryDto.setName(category.getName());
            return categoryDto;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Exception Occured while inserting Data");
        }
    }

     public List<CategoryDto> getAllCatogories() {
        try {
            List<Category> categories = categoryRepo.findAll();
            if (categories.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Category Found");
            } else {
                List<CategoryDto> categoryDto = new ArrayList<>();
                for (Category cat : categories) {
                    CategoryDto dto = new CategoryDto();
                    dto.setCode(cat.getCode());
                    dto.setName(cat.getName());
                    categoryDto.add(dto);
                }
                return categoryDto;
            }
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "An Error occured while Retrieving categories");
        }
    }

    public CategoryDto updateCategory(int id, Category category) {
        Category existingCategory = categoryRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Not Found for ID " + id));
        try {
            existingCategory.setName(category.getName());
            existingCategory.setCode(id);
            categoryRepo.save(existingCategory);
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setCode(id);
            categoryDto.setName(existingCategory.getName());
            return categoryDto;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "An error occured while updating data");
        }
    }

     public String deleteCategory(int id) {
        try {
            categoryRepo.findById(id).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Not Found for ID :" + id));
            categoryRepo.deleteById(id);
            return "Data Deleted for ID : " + id;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "An Error occured while deleting Category ");
        }
    }
}
