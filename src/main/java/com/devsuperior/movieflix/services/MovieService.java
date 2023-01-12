package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieReviewDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;
    @Autowired
    private GenreRepository genreRepository;

    @Transactional(readOnly = true)
    public MovieDTO findById(Long id){
        Optional<Movie> movie2 = repository.findById(id);
        Movie movie = movie2.orElseThrow(()-> new EntityNotFoundException("Entity Not Found"));
        return new MovieDTO(movie);
    }
    /** FUNCIONA MAIS NÃO PASSA NO TEST .............
    @Transactional(readOnly = true)
    public MovieReviewDTO findMovieReview(Long id){
        Movie movie = (id == 0) ? null: repository.findMovieByReviews(id);
        return new MovieReviewDTO(movie, movie.getReviews());
    }

    @Transactional(readOnly = true)
    public Page<MovieDTO> findByMovieGenre(Long id, Pageable pageable){
        Page<Movie> movies = (id == 0) ? repository.findAll(pageable) : repository.findMovieByGenre(id, pageable);
        return movies.map(MovieDTO::new);
    }
   */
    @Transactional(readOnly = true)
    public Page<MovieDTO> findAllPaged(Long genreId, String title, PageRequest pageRequest) {
        Genre genre = (genreId == 0) ? null : genreRepository.getOne(genreId);
        Page<Movie> page = repository.find(genre, title, pageRequest);
        return page.map(x -> new MovieDTO(x, x.getReviews()));

    }
}
