package com.project.personalexpendituretracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.personalexpendituretracker.dto.CategoryDto;
import com.project.personalexpendituretracker.entities.Category;
import com.project.personalexpendituretracker.services.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public CategoryDto addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    // 11.List all Cateogries
    @GetMapping("/")
    public List<CategoryDto> getAllCatogories() {
        return categoryService.getAllCatogories();
    }

    @PutMapping("/{id}")
    public CategoryDto updateCategory(@PathVariable("id") int id, @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable("id") int id) {
        return categoryService.deleteCategory(id);
    }
}
