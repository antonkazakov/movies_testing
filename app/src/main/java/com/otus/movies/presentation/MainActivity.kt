package com.otus.movies.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.otus.movies.App
import com.otus.movies.R
import com.otus.movies.integration.di.MoviesComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var presenter: MoviesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MoviesComponent.create((application as App).getAppComponent()).inject(this)
        val view = LayoutInflater.from(this).inflate(R.layout.activity_main, null)
        setContentView(view)
        presenter.attachView(view as MainViewImpl)
        presenter.getMovies("star wars")
    }
}
