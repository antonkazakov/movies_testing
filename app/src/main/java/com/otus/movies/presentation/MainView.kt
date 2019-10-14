package com.otus.movies.presentation

import com.otus.movies.dto.MovieDTO

interface MainView {

    fun populateMovie(movieDTO: MovieDTO)

    fun showError(errorText: String)
}