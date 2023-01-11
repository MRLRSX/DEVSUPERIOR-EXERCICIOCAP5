package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Genre;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class GenreDTO {

    private Long id;
    @NotBlank
    private String name;
    private List<MovieDTO> movies = new ArrayList<>();
    public GenreDTO(){}

    public GenreDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public GenreDTO(Genre entity){
        this.id = entity.getId();
        this.name = entity.getName();
        entity.getMovies().forEach(mov -> this.movies.add(new MovieDTO(mov)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MovieDTO> getMovies() {
        return movies;
    }
}
