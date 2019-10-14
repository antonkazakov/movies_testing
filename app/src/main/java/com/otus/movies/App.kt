package com.otus.movies

import android.app.Application
import com.otus.movies.integration.di.AppComponent

open class App : Application() {

    companion object {

        private var appComponent: AppComponent? = null
    }

    override fun onCreate() {
        super.onCreate()
        getAppComponent().inject(this)
    }

     open fun getAppComponent(): AppComponent {
        return appComponent ?: AppComponent.create().also {
            appComponent = it
        }
    }
}