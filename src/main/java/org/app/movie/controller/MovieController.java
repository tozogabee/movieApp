package org.app.movie.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.app.movie.controller.mapper.MovieMapper;
import org.app.movie.controller.responses.MovieDetailResponse;
import org.app.movie.controller.responses.MovieSearchResponse;
import org.app.movie.db.entity.Movie;
import org.app.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/movie")
@Tag(name = "Movies", description = "Operation to the movies")
public class MovieController {

    private MovieService movieService;
    private MovieMapper mapper;

    @Autowired
    public MovieController(MovieService movieService, MovieMapper mapper) {
        this.movieService = movieService;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDetailResponse> getMovieDetail(@PathVariable("id") UUID id) {
        Movie movie = this.movieService.getDetailedInformationById(id);
        MovieDetailResponse response = this.mapper.toResponse(movie);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    @Operation(summary = "search by title and filter/sort by fields optionally", description = "search by title and filter/sort by fields optionally")
    public ResponseEntity<List<MovieSearchResponse>> searchMovies(
            @RequestParam("query") String query,
            @RequestParam(value = "sort_by", required = false) String sortBy,
            @RequestParam(value = "filter", required = false) String filter
    ) {
        List<Movie> result = movieService.searchMovies(query, sortBy, filter);
        return ResponseEntity.ok(mapper.toSearchResponseList(result));
    }

    @GetMapping("/popular")
    @Operation(summary = "50 most popular film", description = "Return with 50 most popular movies by paging or without paging if page is not added.")
    public ResponseEntity<List<MovieSearchResponse>> getTopPopularMovies(
            @RequestParam @Nullable Integer page
    ) {
        List<Movie> topMovies = movieService.getTop50PopularMovies();
        List<MovieSearchResponse> allResponses = topMovies.stream()
                .map(movie -> new MovieSearchResponse(
                        movie.getId(),
                        movie.getRating(),
                        movie.getReleaseDate() != null ? movie.getReleaseDate().toString() : null,
                        movie.getTitle(),
                        movie.getPosterUrl()
                ))
                .toList();

        if(page==null) {
            return ResponseEntity.ok(allResponses);
        }

        int fromIndex = page * 10;
        int toIndex = Math.min(fromIndex + 10, allResponses.size());
        if (fromIndex > allResponses.size()) {
            return ResponseEntity.ok(List.of()); // üres oldal
        }

        List<MovieSearchResponse> paged = allResponses.subList(fromIndex, toIndex);
        return ResponseEntity.ok(paged);
    }


}
