package com.example.goorm.oreum.repository;

import com.example.goorm.oreum.MyOreum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyOreumRepository extends JpaRepository<MyOreum, Long> {
    Optional<MyOreum> findById(Long id);
}
