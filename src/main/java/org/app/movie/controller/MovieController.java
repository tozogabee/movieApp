package org.app.movie.controller;

import org.app.movie.controller.mapper.MovieMapper;
import org.app.movie.controller.responses.MovieDetailResponse;
import org.app.movie.controller.responses.MovieSearchResponse;
import org.app.movie.controller.responses.PagedResponse;
import org.app.movie.db.entity.Movie;
import org.app.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/movie")
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
    public ResponseEntity<List<MovieSearchResponse>> searchMovies(
            @RequestParam("query") String query,
            @RequestParam(value = "sort_by", required = false) String sortBy,
            @RequestParam(value = "filter", required = false) String filter
    ) {
        List<Movie> result = movieService.searchMovies(query, sortBy, filter);
        return ResponseEntity.ok(mapper.toSearchResponseList(result));
    }

    @GetMapping("/popular")
    public ResponseEntity<PagedResponse<MovieSearchResponse>> getPopularMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "rating"));
        Page<Movie> moviePage = this.movieService.findAll(pageable);

        List<MovieSearchResponse> responses = moviePage.getContent()
                .stream()
                .map(mapper::toSearchResponse)
                .toList();

        PagedResponse<MovieSearchResponse> pagedResponse = new PagedResponse<>(
                responses,
                moviePage.getNumber(),
                moviePage.getSize(),
                moviePage.getTotalElements(),
                moviePage.getTotalPages(),
                moviePage.isLast()
        );

        return ResponseEntity.ok(pagedResponse);
    }

}
