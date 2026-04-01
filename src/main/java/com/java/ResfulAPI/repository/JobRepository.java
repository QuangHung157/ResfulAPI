package com.java.ResfulAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.ResfulAPI.domain.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
}