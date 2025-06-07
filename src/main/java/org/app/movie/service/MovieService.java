package org.app.movie.service;

import org.app.movie.db.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovieService {
    Movie getDetailedInformationById(UUID id);
    List<Movie> searchMovies(String query, String sortBy, String filter);
    Page<Movie> findAll(Pageable pageAble);
}