package com.otus.movies.repository

import com.otus.movies.dto.MovieDTO
import io.reactivex.Single

interface MoviesRepository {

    fun getMovies(query: String): Single<MovieDTO>
}