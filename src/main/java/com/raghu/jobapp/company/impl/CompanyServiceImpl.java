package com.raghu.jobapp.company.impl;

import com.raghu.jobapp.company.Company;
import com.raghu.jobapp.company.CompanyRepository;
import com.raghu.jobapp.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company UpdateCompany(Long id, Company updatedCompany) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if(companyOptional.isPresent()){
            Company existedCompany = companyOptional.get();
            existedCompany.setDescription(updatedCompany.getDescription());
            existedCompany.setName(updatedCompany.getName());
            existedCompany.setJobs(updatedCompany.getJobs());
            companyRepository.save(existedCompany);
            return existedCompany;
        }
        return null;
    }

    @Override
    public Void createCompany(Company company) {
        companyRepository.save(company);
        return null;
    }

    @Override
    public boolean deleteCompany(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Company getSpecificCompanyByID(Long id) {
        return companyRepository.findById(id).orElse(null);
    }
}
