package com.otus.movies.presentation

import android.annotation.SuppressLint
import com.otus.movies.repository.MoviesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MoviesPresenterImpl
@Inject constructor(
    private val moviesRepository: MoviesRepository
) : MoviesPresenter {

    private var view: MainView? = null

    override fun attachView(view: MainView) {
        this.view = view
    }

    @SuppressLint("CheckResult")
    override fun getMovies(query: String) {
        moviesRepository.getMovies(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view?.populateMovie(it)
                },
                { error ->
                    view?.showError(error.message.toString())
                })
    }

    override fun detachView() {
        view = null
    }
}