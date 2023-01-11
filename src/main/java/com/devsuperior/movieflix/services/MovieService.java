package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;


@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;
    @Transactional(readOnly = true)
    public Page<MovieDTO> findAll(Pageable pageable){
        return repository.findAll(pageable).map(MovieDTO::new);
    }

    @Transactional(readOnly = true)
    public MovieDTO findById(Long id){
        Optional<Movie> movie2 = repository.findById(id);
        Movie movie = movie2.orElseThrow(()-> new EntityNotFoundException("Entity Not Found"));
        return new MovieDTO(movie);
    }
}
