package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private AuthService authService;

    @Autowired
    private MovieRepository movieRepository;

    public ReviewDTO save(@Valid  ReviewDTO reviewDTO){
        Review review = new Review();
        copyReviewDTOToReview(reviewDTO, review);
        review.setUser(authService.authenticated());
        review = repository.save(review);
        return new ReviewDTO(review);
    }

    private void copyReviewDTOToReview(ReviewDTO dto, Review entity){
        entity = repository.getOne(dto.getId());
        entity.setText(dto.getText());
        Movie movie = movieRepository.getOne(entity.getMovie().getId());
        entity.setMovie(movie);
    }
}
