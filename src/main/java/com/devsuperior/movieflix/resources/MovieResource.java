package com.devsuperior.movieflix.resources;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieReviewDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/movies")
public class MovieResource {

    @Autowired
    private MovieService service;


    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieDTO> findById(@PathVariable Long id){
        MovieDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value="/{id}/reviews")
    public ResponseEntity<MovieReviewDTO> findByReview (@PathVariable Long id){
        MovieReviewDTO dto = service.findMovieReview(id);
        return ResponseEntity.ok().body(dto);
    }



    @GetMapping
    public ResponseEntity<Page<MovieDTO>> findAll(
            @RequestParam(value = "genreId", defaultValue = "0") Long genreId,
            @PageableDefault(size = 12, sort = {"title"}) Pageable pageable) {

        Page<MovieDTO> list = service.findByMovieGenre(genreId, pageable);
        return ResponseEntity.ok().body(list);
    }


}
