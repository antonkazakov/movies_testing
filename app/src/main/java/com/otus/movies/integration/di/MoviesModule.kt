package com.otus.movies.integration.di

import com.otus.movies.presentation.MoviesPresenter
import com.otus.movies.presentation.MoviesPresenterImpl
import com.otus.movies.repository.MoviesApiService
import com.otus.movies.repository.MoviesRepository
import com.otus.movies.repository.MoviesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit


@Module
abstract class MoviesModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @FeatureScope
        fun provideMoviesApiService(retrofit: Retrofit): MoviesApiService {
            return retrofit.create(MoviesApiService::class.java)
        }
    }

    @Binds
    @Reusable
    abstract fun bindsMoviesPresenter(moviesPresenterImpl: MoviesPresenterImpl): MoviesPresenter

    @Binds
    @Reusable
    abstract fun bindsMoviesRepository(moviesRepositoryImpl: MoviesRepositoryImpl): MoviesRepository
}