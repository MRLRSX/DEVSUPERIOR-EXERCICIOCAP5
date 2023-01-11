package com.devsuperior.movieflix.resource;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/movies")
public class MovieResource {

    @Autowired
    private MovieService service;

    @GetMapping
    public ResponseEntity<Page<MovieDTO>> findAll(@PageableDefault(size = 12, sort={"name"})Pageable pageable){
        Page<MovieDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieDTO> findById(@PathVariable Long id){
        MovieDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

}
