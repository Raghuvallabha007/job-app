package com.raghu.jobapp.review.impl;

import com.raghu.jobapp.company.Company;
import com.raghu.jobapp.company.CompanyService;
import com.raghu.jobapp.review.Review;
import com.raghu.jobapp.review.ReviewRepository;
import com.raghu.jobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public Void addReview(Long companyId, Review review) {
        Company company = companyService.getSpecificCompanyByID(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
        }
        return null;
    }
}
