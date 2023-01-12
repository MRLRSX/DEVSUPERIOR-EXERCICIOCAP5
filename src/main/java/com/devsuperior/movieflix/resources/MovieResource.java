package com.devsuperior.movieflix.resources;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieReviewDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    /** RSRSRS FUNCIONA MAIS NÃO PASSA NO TEST -> GAMBIARRA EU ACHEI QUANDO ESTA IMPLEMENTANDO
     * NA CLASS MovieService linha 48 denuncia a gambiarra FUNCIONA MAIS NÃO PASSA NO TESTE
     * DECIDI FAZER DO JEITO CERTO .......
    @GetMapping(value="/{id}/reviews")
    public ResponseEntity<MovieReviewDTO> findByReview (@PathVariable Long id){
        MovieReviewDTO dto = service.findMovieReview(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<MovieDTO>> findAll(
            @RequestParam(value = "genreId", defaultValue = "0") Long genreId,
            @PageableDefault(size = 12, sort = {"title"}) Pageable pageable) {

        Page<MovieDTO> list = service.findAllPaged(genreId, pageable);
        return ResponseEntity.ok().body(list);
    }
   */
    @GetMapping
    public ResponseEntity<Page<MovieDTO>> findAll(
            @RequestParam(value = "genreId", defaultValue = "0") Long genreId,
            @RequestParam(value = "title", defaultValue = "") String title,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "title") String orderBy){


        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction),orderBy);

        Page<MovieDTO> list = service.findAllPaged(genreId, title.trim(), pageRequest);
        return ResponseEntity.ok().body(list);

    }

}
