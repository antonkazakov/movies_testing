package com.otus.movies

import com.otus.movies.integration.di.AppComponent
import com.otus.movies.integration.di.DaggerTestAppComponent

class TestApp : App() {

    override fun getAppComponent(): AppComponent {
        return DaggerTestAppComponent.builder().build()
    }
}