package com.raghu.jobapp.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id,
                                        @RequestBody Company company) {
        companyService.UpdateCompany(id, company);
        return ResponseEntity.ok().body("Company Updated Sucessfully!");
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company) {
        companyService.createCompany(company);
        return ResponseEntity.ok().body("Company is Saved!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTheCompany(@PathVariable Long id) {
        boolean isDeleted = companyService.deleteCompany(id);
        return isDeleted ? ResponseEntity.ok().body("Deleted Successfully") :
                ResponseEntity.status(404).body("Company not found");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company existedCompany = companyService.getSpecificCompanyByID(id);
        return existedCompany != null ?
                ResponseEntity.ok().body(existedCompany) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
