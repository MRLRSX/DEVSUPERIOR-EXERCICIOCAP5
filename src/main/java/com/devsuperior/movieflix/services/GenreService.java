package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Transactional(readOnly = true)
    public List<GenreDTO> findAll() {
        return genreRepository.findAll().stream().map(GenreDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public GenreDTO findById(Long id) {
        Optional<Genre> genre2 = genreRepository.findById(id);
        Genre genre = genre2.orElseThrow(() -> new EntityNotFoundException("Entity Not Found! "));
        return new GenreDTO(genre);
    }
}
