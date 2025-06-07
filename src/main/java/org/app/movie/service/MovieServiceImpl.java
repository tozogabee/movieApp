package org.app.movie.service;

import org.app.movie.controller.exception.MovieNotFoundException;
import org.app.movie.db.entity.Movie;
import org.app.movie.db.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        List<Movie> result = new ArrayList<>(movieRepository.findByTitleContainingIgnoreCase(query));

        if (filter != null && !filter.isBlank()) {
            Map<String, String> filters = parseFilterString(filter);

            for (Map.Entry<String, String> entry : filters.entrySet()) {
                String key = entry.getKey().toLowerCase();
                String value = entry.getValue();

                result = result.stream()
                        .filter(m -> {
                            switch (key) {
                                case "genre":
                                    return m.getGenres() != null && m.getGenres().toLowerCase().contains(value.toLowerCase());
                                case "language":
                                    return m.getLanguage() != null && m.getLanguage().equalsIgnoreCase(value);
                                case "releasedate":
                                    return m.getReleaseDate() != null && m.getReleaseDate().toString().equals(value);
                                case "rating":
                                    return m.getRating() != null && m.getRating().toString().equals(value);
                                case "runtime":
                                    return m.getRuntime() != null && m.getRuntime().toString().equals(value);
                                case "overview":
                                    return m.getOverview() != null && m.getOverview().equalsIgnoreCase(value);
                                default:
                                    return true;
                            }
                        })
                        .collect(java.util.stream.Collectors.toCollection(ArrayList::new));
            }
        }

        if (sortBy != null && !sortBy.isBlank()) {
            switch (sortBy) {
                case "rating" -> result.sort(
                        Comparator.comparing(Movie::getRating, Comparator.nullsLast(Double::compareTo)).reversed()
                );
                case "releaseDate" -> result.sort(
                        Comparator.comparing(Movie::getReleaseDate, Comparator.nullsLast(java.time.LocalDate::compareTo)).reversed()
                );
            }
        }

        if (result == null || result.isEmpty()) {
            throw new MovieNotFoundException("Movie not found by query: " + query);
        }

        return result;
    }


    @Override
    public List<Movie> getTop50PopularMovies() {
        return this.movieRepository.findTop50ByOrderByRatingDesc();
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

