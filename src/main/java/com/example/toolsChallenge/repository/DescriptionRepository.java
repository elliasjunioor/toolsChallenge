package com.example.toolsChallenge.repository;

import com.example.toolsChallenge.domain.Description;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescriptionRepository extends JpaRepository<Description, Integer> {
}
