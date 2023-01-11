package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Review;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ReviewDTO {

    private Long id;
    @NotBlank
    private String text;
    private Long movieId;
    private UserDTO user;

    public ReviewDTO(){}

    public ReviewDTO(Long id, String text, Long movieId, UserDTO user) {
        this.id = id;
        this.text = text;
        this.movieId = movieId;
        this.user = user;
    }

    public ReviewDTO(Review entity){
         this.id = entity.getId();
         this.text = entity.getText();
         this.user = new UserDTO(entity.getUser());
         this.movieId = entity.getMovie().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
