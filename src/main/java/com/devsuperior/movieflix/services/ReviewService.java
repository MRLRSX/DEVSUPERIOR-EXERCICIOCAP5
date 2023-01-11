package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private AuthService authService;

    @Autowired
    private MovieRepository movieRepository;

    @Transactional
    public ReviewDTO save(@Valid ReviewDTO dto) {
        Review entity = new Review();
        copyDtoToEntity(dto, entity);
        entity.setUser(authService.authenticated());
        entity = repository.save(entity);
        return new ReviewDTO(entity);

    }
    private void copyDtoToEntity(ReviewDTO dto, Review entity) {

        entity.setText(dto.getText());

        MovieDTO movieDto = new MovieDTO();
        movieDto.setId(dto.getMovieId());
        Movie movie = movieRepository.getOne(movieDto.getId());
        entity.setMovie(movie);
    }
}
