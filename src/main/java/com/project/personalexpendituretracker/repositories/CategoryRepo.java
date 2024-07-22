package com.project.personalexpendituretracker.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.personalexpendituretracker.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
