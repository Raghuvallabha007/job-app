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
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getSpecificCompanyByID(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> review = reviewRepository.findByCompanyId(companyId);
        return review.stream()
                .filter(eachReview -> eachReview.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }
}
