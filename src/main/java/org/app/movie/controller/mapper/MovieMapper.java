package org.app.movie.controller.mapper;

import org.app.movie.controller.responses.MovieDetailResponse;
import org.app.movie.controller.responses.MovieSearchResponse;
import org.app.movie.db.entity.Movie;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    //MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    @Mapping(target = "fullPosterUrl", source = "posterUrl")
    @Mapping(source = "rating", target = "averageRating")
    @Mapping(source = "releaseDate", target = "releaseDate", dateFormat = "yyyy-MM-dd")
    MovieDetailResponse toResponse(Movie movie);


    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "posterUrl", source = "fullPosterUrl")
    Movie toEntity(MovieDetailResponse movieDetailResponse);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "releaseDate", target = "releaseDate", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "posterUrl", target = "posterUrl")
    @Mapping(source = "rating", target = "averageRating")
    MovieSearchResponse toSearchResponse(Movie movie);

    List<MovieSearchResponse> toSearchResponseList(List<Movie> movies);

}
