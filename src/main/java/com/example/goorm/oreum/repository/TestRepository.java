package com.example.goorm.oreum.repository;

import com.example.goorm.oreum.Oreum;
import com.example.goorm.oreum.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
}