package com.example.goorm.oreum.repository;

import com.example.goorm.oreum.domain.Oreum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OreumRepository extends JpaRepository<Oreum, Long> {
    @Query("select o from Oreum o where o.month=:month and o.day=:day")
    Oreum findByBirthday(int month, int day);
}
