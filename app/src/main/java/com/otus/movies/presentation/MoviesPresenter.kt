package com.otus.movies.presentation

interface MoviesPresenter {

    fun getMovies(query: String)

    fun attachView(view: MainView)

    fun detachView()
}