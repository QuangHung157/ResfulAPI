package com.java.ResfulAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.ResfulAPI.domain.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}