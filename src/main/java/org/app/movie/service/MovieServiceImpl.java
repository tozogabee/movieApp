package org.app.movie.service;

import org.app.movie.controller.exception.MovieNotFoundException;
import org.app.movie.db.entity.Movie;
import org.app.movie.db.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie getDetailedInformationById(UUID id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with ID: " + id));
    }

    @Override
    public List<Movie> searchMovies(String query, String sortBy, String filter) {
        if (query == null || query.isBlank()) {
            throw new IllegalArgumentException("Query parameter is required.");
        }

        List<Movie> result = movieRepository.findByTitleContainingIgnoreCase(query);

        if (filter != null && !filter.isBlank()) {
            Map<String, String> filters = parseFilterString(filter);

            for (Map.Entry<String, String> entry : filters.entrySet()) {
                String key = entry.getKey().toLowerCase();
                String value = entry.getValue();

                result = switch (key) {
                    case "genre" -> result.stream()
                            .filter(m -> m.getGenres() != null && m.getGenres().toLowerCase().contains(value.toLowerCase()))
                            .toList();
                    case "language" -> result.stream()
                            .filter(m -> m.getLanguage() != null && m.getLanguage().equalsIgnoreCase(value))
                            .toList();
                    case "releasedate" -> result.stream()
                            .filter(m -> m.getReleaseDate() != null && m.getReleaseDate().toString().equals(value))
                            .toList();
                    case "rating" -> result.stream()
                            .filter(m -> m.getRating() != null && m.getRating().toString().equals(value))
                            .toList();
                    case "runtime" -> result.stream()
                            .filter(m -> m.getRuntime() != null && m.getRuntime().toString().equals(value))
                            .toList();
                    case "overview" -> result.stream()
                            .filter(m -> m.getOverview() != null && m.getOverview().toString().equals(value))
                            .toList();
                    default -> result;
                };
            }
        }

        if (sortBy != null) {
            switch (sortBy) {
                case "rating" -> result.sort(Comparator.comparing(Movie::getRating).reversed());
                case "releaseDate" -> result.sort(Comparator.comparing(Movie::getReleaseDate).reversed());
            }
        }
        if(result != null && result.isEmpty())
            throw new MovieNotFoundException("Movie not found by "+query);
        return result;
    }

    @Override
    public Page<Movie> findAll(Pageable pageAble) {
        return this.movieRepository.findAll(pageAble);
    }

    private Map<String, String> parseFilterString(String filter) {
        Map<String, String> filters = new HashMap<>();
        if (filter == null || filter.isBlank()) return filters;

        String[] parts = filter.split("&");
        for (String part : parts) {
            String[] keyValue = part.split("=");
            if (keyValue.length == 2) {
                filters.put(keyValue[0].trim(), keyValue[1].trim());
            }
        }
        return filters;
    }
}

