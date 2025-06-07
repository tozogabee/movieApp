
package org.app.movie.service;

import org.app.movie.controller.exception.MovieNotFoundException;
import org.app.movie.db.entity.Movie;
import org.app.movie.db.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    private UUID validId;
    private Movie testMovie;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        validId = UUID.randomUUID();
        testMovie = new Movie();
        testMovie.setId(validId);
        testMovie.setTitle("Inception");
        testMovie.setGenres("Sci-Fi");
        testMovie.setLanguage("English");
        testMovie.setRating(9.0);
        testMovie.setReleaseDate(LocalDate.of(2010, 7, 16));
    }

    @Test
    public void testGetDetailedInformationByIdSuccess() {
        when(movieRepository.findById(validId)).thenReturn(Optional.of(testMovie));
        Movie result = movieService.getDetailedInformationById(validId);
        assertNotNull(result);
        assertEquals("Inception", result.getTitle());
    }

    @Test
    public void testGetDetailedInformationByIdNotFound() {
        when(movieRepository.findById(validId)).thenReturn(Optional.empty());
        assertThrows(MovieNotFoundException.class, () -> movieService.getDetailedInformationById(validId));
    }

    @Test
    public void testSearchMoviesByTitle() {
        when(movieRepository.findByTitleContainingIgnoreCase("incep"))
                .thenReturn(Arrays.asList(testMovie));

        List<Movie> results = movieService.searchMovies("incep", null, null);

        assertFalse(results.isEmpty());
        assertEquals("Inception", results.get(0).getTitle());
    }

    @Test
    public void testSearchMoviesWithFilterGenre() {
        when(movieRepository.findByTitleContainingIgnoreCase("incep"))
                .thenReturn(Arrays.asList(testMovie));

        List<Movie> results = movieService.searchMovies("incep", null, "genre=Sci-Fi");

        assertFalse(results.isEmpty());
        assertEquals("Inception", results.get(0).getTitle());
    }

    @Test
    public void testSearchMoviesWithSortByRating() {
        Movie anotherMovie = new Movie();
        anotherMovie.setId(UUID.randomUUID());
        anotherMovie.setTitle("Another Movie");
        anotherMovie.setGenres("Action");
        anotherMovie.setRating(7.0);
        anotherMovie.setReleaseDate(LocalDate.of(2005, 1, 1));

        when(movieRepository.findByTitleContainingIgnoreCase("movie"))
                .thenReturn(Arrays.asList(testMovie, anotherMovie));

        List<Movie> results = movieService.searchMovies("movie", "rating", null);

        assertEquals(2, results.size());
        assertTrue(results.get(0).getRating() >= results.get(1).getRating());
    }
}
