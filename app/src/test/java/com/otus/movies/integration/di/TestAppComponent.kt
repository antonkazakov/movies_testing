package com.otus.movies.integration.di

import com.otus.movies.integration.MainActivityTest
import dagger.Component
import javax.inject.Singleton

@Component(modules = [RetrofitTestModule::class])
@Singleton
interface TestAppComponent : AppComponent {


    fun inject(mainActivityTest: MainActivityTest)
}