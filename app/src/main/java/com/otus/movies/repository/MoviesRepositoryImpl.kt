package com.otus.movies.repository

import com.otus.movies.dto.MovieDTO
import io.reactivex.Single
import javax.inject.Inject

class MoviesRepositoryImpl
@Inject constructor(
    private val moviesApiService: MoviesApiService
) : MoviesRepository {

    override fun getMovies(query: String): Single<MovieDTO> {
        return moviesApiService.getMovies(query, "d31f705e")
    }
}