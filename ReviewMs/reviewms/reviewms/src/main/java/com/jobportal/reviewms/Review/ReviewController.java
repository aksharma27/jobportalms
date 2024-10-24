package com.jobportal.reviewms.Review;

//MONOLITH URLS : /companies/{comId}/reviews

//MS URLS :
//get(specific), post:   /reviews?companyId={companyId}    ==> query param
// getAll, put, del :     /reviews/{reviewId}               ==> path variable


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping()       //i.e : /companies/compId/reviews ==> /companies/3/reviews
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId) {       //parameter name should be same as the url in request mapping()
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
     }

     @PostMapping()
    public ResponseEntity<String> addReview (@RequestBody Review review, @RequestParam Long companyId) {
         boolean reviewAdded = reviewService.addReview(companyId, review);
         if (reviewAdded)
             return new ResponseEntity<>("Review Added Successfully", HttpStatus.OK);
         return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
     }

     //get a specific review of a specific company : review id + compId
    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview (@RequestParam Long companyId, @PathVariable Long reviewId) {
        Review review = reviewService.getReview(reviewId);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    //update a review by review id for a specific comp id :
    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview (@PathVariable Long reviewId, Review review) {
        boolean isReviewUpdated = reviewService.updateReview(reviewId, review);
        if (isReviewUpdated) return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
        return new ResponseEntity<>("Review Not updated", HttpStatus.NOT_FOUND);
    }

    //delete a review by review id for a given comp id :
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview (@PathVariable Long companyId, @PathVariable Long reviewId) {
        boolean isDeleted = reviewService.deleteReview(reviewId);
        if (isDeleted) return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        return new ResponseEntity<>("Review not found ", HttpStatus.NOT_FOUND);
    }
}