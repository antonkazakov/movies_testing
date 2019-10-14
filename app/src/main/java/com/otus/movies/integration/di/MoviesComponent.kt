package com.otus.movies.integration.di

import com.otus.movies.presentation.MainActivity
import dagger.Component

@Component(
    dependencies = [AppComponent::class],
    modules = [MoviesModule::class]
)
@FeatureScope
interface MoviesComponent {

    companion object {

        fun create(appComponent: AppComponent): MoviesComponent {
            return DaggerMoviesComponent.builder().appComponent(appComponent).build()
        }
    }

    fun inject(mainActivity: MainActivity)
}