package com.raghu.jobapp.review;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
        return ResponseEntity.ok().body(reviewService.getAllReviews(companyId));
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review) {
        boolean isReviewCreated = reviewService.addReview(companyId, review);
        return isReviewCreated ?
                ResponseEntity.ok().body("Created Review successfully!") :
                ResponseEntity.status(404).body("Review Not created");
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId,
                                            @PathVariable Long reviewId) {
        Review review = reviewService.getReviewById(companyId, reviewId);
        return review != null
                ? ResponseEntity.ok(review)
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId,
                                               @RequestBody Review review) {
        Review updated = reviewService.updateReview(companyId, reviewId, review);
        return ResponseEntity.ok().body(updated);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId) {
        boolean isReviewDeleted = reviewService.deleteReview(companyId, reviewId);
        return isReviewDeleted
                ? ResponseEntity.ok("deleted sucessfully")
                : ResponseEntity.notFound().build();
    }
}
