package com.otus.movies.presentation

import com.otus.movies.base.RxRule
import com.otus.movies.dto.MovieDTO
import com.otus.movies.repository.MoviesRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule


class MoviesPresenterImplTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val rxRule: RxRule = RxRule()

    @Mock
    lateinit var mainView: MainView

    @Mock
    lateinit var repository: MoviesRepository

    lateinit var moviesPresenter: MoviesPresenterImpl

    @Before
    fun setUp() {
        moviesPresenter = MoviesPresenterImpl(repository)
    }

    @Test
    fun `should populate view with movie`() {
        moviesPresenter.attachView(mainView)
        val expectedArgument = MovieDTO(title = "Lord of the Rings")
        Mockito.`when`(repository.getMovies(Mockito.anyString()))
            .thenReturn(Single.just(expectedArgument))

        moviesPresenter.getMovies("Lord")

        Mockito.verify(mainView, Mockito.times(1)).populateMovie(expectedArgument)
    }

    @Test
    fun `should call showError when repository emit error`() {
        moviesPresenter.attachView(mainView)
        Mockito.`when`(repository.getMovies(Mockito.anyString()))
            .thenReturn(Single.error(RuntimeException("Timeout exception")))

        moviesPresenter.getMovies("1")

        Mockito
            .verify(mainView, Mockito.times(1))
            .showError("Timeout exception")
    }
}