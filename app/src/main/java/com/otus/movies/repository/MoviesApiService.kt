package com.otus.movies.repository

import com.otus.movies.dto.MovieDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {

    @GET("/")
    fun getMovies(@Query("t") query: String, @Query("apikey") key: String): Single<MovieDTO>
}