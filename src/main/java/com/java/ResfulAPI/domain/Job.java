package com.java.ResfulAPI.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 150)
    private String location;

    private Double salary;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    public Job() {
    }

    public Job(String title, String description, String location, Double salary, Company company) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.salary = salary;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public Double getSalary() {
        return salary;
    }

    public Company getCompany() {
        return company;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}