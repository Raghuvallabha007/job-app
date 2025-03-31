package com.raghu.jobapp.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    Void addReview(Long companyId, Review review);
}
