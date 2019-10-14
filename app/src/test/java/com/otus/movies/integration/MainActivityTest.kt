package com.otus.movies.integration

import android.widget.TextView
import com.otus.movies.R
import com.otus.movies.TestApp
import com.otus.movies.base.RxRule
import com.otus.movies.presentation.MainActivity
import junit.framework.Assert.assertEquals
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.AfterClass
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric.buildActivity
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowToast
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit

@RunWith(RobolectricTestRunner::class)
@Config(application = TestApp::class)
class MainActivityTest {

    @get:Rule
    val rxRule = RxRule()

    var mockWebServer: MockWebServer = MockWebServer()

    @Test
    fun `should populate movie info in views`() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(getBody())
            .setBodyDelay(0, TimeUnit.MILLISECONDS)
        mockWebServer.enqueue(response)
        mockWebServer.start(8080)

        val activity = buildActivity(MainActivity::class.java).setup().get()

        assertEquals(
            (activity.findViewById(R.id.titleTextView) as TextView).text.toString(),
            "Galaxy"
        )
    }

    private fun getBody(): String {
        return """{"Title":"Galaxy","Year":"1986","Rated":"N/A","Released":"01 Jan 1986","Runtime":"91 min","Genre":"Action, Adventure, Sci-Fi","Director":"Brett Piper","Writer":"Brett Piper","Actors":"Matt Mitler, Denise Coward, Joe Gentissi, Bill MacGlaughlin","Plot":"After hijacking a space shuttle, a spy finds that the controls are malfunctioning and sees alien battleships approaching Earth. Many years later, when the arc of his flight path returns to earth, he finds the planet under alien domination.","Language":"English, French","Country":"USA","Awards":"N/A","Poster":"https://m.media-amazon.com/images/M/MV5BOTBhMGNhMTUtNTUwNS00NmYwLWI1ZmEtZWVlMTU4NDE3ZTFhXkEyXkFqcGdeQXVyMTQ2MjQyNDc@._V1_SX300.jpg","Ratings":[{"Source":"Internet Movie Database","Value":"4.4/10"}],"Metascore":"N/A","imdbRating":"4.4","imdbVotes":"191","imdbID":"tt0404977","Type":"movie","DVD":"N/A","BoxOffice":"N/A","Production":"N/A","Website":"N/A","Response":"True"}"""
    }

    @Test
    fun `should show toast when server respond with error status`() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_FORBIDDEN)
            .setBody(getBody())
            .setBodyDelay(0, TimeUnit.MILLISECONDS)
        mockWebServer.enqueue(response)
        mockWebServer.start(8080)

        buildActivity(MainActivity::class.java).setup().get()

        Assert.assertEquals(ShadowToast.getTextOfLatestToast(), "HTTP 403 Client Error")
    }

    @After
    fun clear(){
        mockWebServer.shutdown()
    }
}