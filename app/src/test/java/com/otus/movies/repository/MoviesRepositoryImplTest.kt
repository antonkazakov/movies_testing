package com.otus.movies.repository

import com.otus.movies.dto.MovieDTO
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.runners.MockitoJUnitRunner

class MoviesRepositoryImplTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var apiService: MoviesApiService

    lateinit var moviesRepository: MoviesRepository

    @Before
    fun setUp() {
        moviesRepository = MoviesRepositoryImpl(apiService)
    }

    @Test
    fun `should emit movie by query`() {
        val expected = MovieDTO(metascore = "10")

        `when`(apiService.getMovies(Mockito.anyString(), Mockito.anyString())).thenReturn(
            Single.just(expected)
        )

        moviesRepository.getMovies("Social")
            .test()
            .assertResult(expected)
            .assertNoErrors()
    }
}