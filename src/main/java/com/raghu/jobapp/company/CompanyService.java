package com.raghu.jobapp.company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<Company> getAllCompanies();
    Company UpdateCompany(Long id, Company company);
    Void createCompany(Company company);
    boolean deleteCompany(Long id);
    Company getSpecificCompanyByID(Long id);
}
