package org.app.movie.controller.responses;

import java.util.UUID;

public class MovieSearchResponse {
    private UUID id;
    private String title;
    private String releaseDate;
    private String posterUrl;
    private Double averageRating;

    public MovieSearchResponse() {
    }

    public MovieSearchResponse(UUID id, Double averageRating, String releaseDate, String title, String posterUrl) {
        this.id = id;
        this.averageRating = averageRating;
        this.releaseDate = releaseDate;
        this.title = title;
        this.posterUrl = posterUrl;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}
