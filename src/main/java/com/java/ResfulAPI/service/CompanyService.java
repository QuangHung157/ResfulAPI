package com.java.ResfulAPI.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.java.ResfulAPI.domain.Company;
import com.java.ResfulAPI.exception.ResourceNotFoundException;
import com.java.ResfulAPI.repository.CompanyRepository;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy company với id = " + id));
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company updateCompany(Long id, Company request) {
        Company currentCompany = getCompanyById(id);

        currentCompany.setName(request.getName());
        currentCompany.setDescription(request.getDescription());
        currentCompany.setAddress(request.getAddress());

        return companyRepository.save(currentCompany);
    }

    public void deleteCompany(Long id) {
        Company company = getCompanyById(id);
        companyRepository.delete(company);
    }
}