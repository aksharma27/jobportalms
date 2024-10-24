package com.jobportal.companyms.company.impl;

import com.jobportal.companyms.company.Company;
import com.jobportal.companyms.company.CompanyRepository;
import com.jobportal.companyms.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
//@Slf4j is shorthand for “Simple Logging Facade for Java” — it automates the creation of a
//logger field named logwithin the class, facilitating the logging of messages.
//@Slf4j
public class CompanyServiceImp implements CompanyService {
//    private ReviewRepository reviewRepository;
    private final CompanyRepository companyRepository;


    public CompanyServiceImp (CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company company, Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company companyToUpdate = companyOptional.get();
            companyToUpdate.setDescription(company.getDescription());
            companyToUpdate.setName(company.getName());
//            companyToUpdate.setJobs(company.getJobs());           //DOES NOT EXISTS IN MS
            companyRepository.save(companyToUpdate);
            return true;
        }
        return false;    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompany(Long id) {
        if (companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

//    @Override
//    public void addReview(Long companyId, Review review) {
//        Company company = companyService.getCompanyById(companyId);
//        if (company != null) {
//            review.setCompany(company);
//            reviewRepository.save(review);
//        }
//    }


}
