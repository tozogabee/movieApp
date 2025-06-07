package org.app.movie.service;

import org.app.movie.db.entity.Movie;
import java.util.List;
import java.util.UUID;

public interface MovieService {
    Movie getDetailedInformationById(UUID id);
    List<Movie> searchMovies(String query, String sortBy, String filter);
    List<Movie> getTop50PopularMovies();
}