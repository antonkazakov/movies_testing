package com.otus.movies.presentation

import android.view.LayoutInflater
import com.otus.movies.R
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric.buildActivity
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowToast

@RunWith(RobolectricTestRunner::class)
class MainViewImplTest {

    lateinit var mainViewImpl: MainViewImpl

    @Test
    fun `should show toast with error message`() {
        val activity =
            buildActivity(MainActivity::class.java).create().get()

        mainViewImpl =
            LayoutInflater.from(activity).inflate(R.layout.activity_main, null) as MainViewImpl

        mainViewImpl.showError("test")

        assertEquals(ShadowToast.getTextOfLatestToast(), "test")
    }
}