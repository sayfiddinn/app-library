package com.example.demo.repository;

import com.example.demo.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepo extends JpaRepository<Library, Integer> {
    boolean existsByName(String name);
}
