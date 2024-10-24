package com.jobportal.reviewms.Review.ReviewServiceImpl;

import com.jobportal.reviewms.Review.Review;
import com.jobportal.reviewms.Review.ReviewRepository;
import com.jobportal.reviewms.Review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
//    private final CompanyService companyService;      //for adding reviews we need this object of company service

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
//        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(reviewId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
//        Company company = companyService.getCompanyById(companyId);
        if (companyId != null && review != null) {
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long reviewId) {
       return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReview(Long reviewId, Review updatedReview) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null) {
            //check if given review exists or not :
                review.setTitle(updatedReview.getTitle());
                review.setDescription(updatedReview.getDescription());
                review.setRating(updatedReview.getRating());
                review.setCompanyId(updatedReview.getCompanyId());
                reviewRepository.save(updatedReview);
                return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null) {
                reviewRepository.delete(review);
                return true;
        }
        return false;
    }

}
