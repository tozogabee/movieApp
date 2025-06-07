package org.app.movie.controller.responses;

public class MovieDetailResponse {
    private String title;
    private String releaseDate;
    private String fullPosterUrl;
    private String overview;
    private String genres;
    private Double averageRating;
    private Integer runtime;
    private String language;

    public MovieDetailResponse() {
    }

    public MovieDetailResponse(String title, String releaseDate, String fullPosterUrl, String overview, String genres, Double averageRating, Integer runtime, String language) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.fullPosterUrl = fullPosterUrl;
        this.overview = overview;
        this.genres = genres;
        this.averageRating = averageRating;
        this.runtime = runtime;
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getFullPosterUrl() {
        return fullPosterUrl;
    }

    public String getOverview() {
        return overview;
    }

    public String getGenres() {
        return genres;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public String getLanguage() {
        return language;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setFullPosterUrl(String fullPosterUrl) {
        this.fullPosterUrl = fullPosterUrl;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
