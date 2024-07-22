package com.project.personalexpendituretracker.services;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.personalexpendituretracker.entities.Category;
import com.project.personalexpendituretracker.repositories.CategoryRepo;

@SpringBootTest
public class CategoryServiceTest {
   
    @Mock
    private CategoryRepo categoryRepo;

    @Autowired
    @InjectMocks
    private CategoryService categoryService;
    
    @Test
    void testGetAllCatogories() {
        Category category =new Category("Rent");
        when(categoryRepo.save(category)).thenReturn(category);
        assertThat(categoryService.getAllCatogories()).isNotNull();
    }

}
