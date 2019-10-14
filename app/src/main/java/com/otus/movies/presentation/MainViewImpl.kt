package com.otus.movies.presentation

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.Toast
import com.otus.movies.dto.MovieDTO
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.view.*

class MainViewImpl
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), MainView {

    override fun populateMovie(movieDTO: MovieDTO) {
        titleTextView.text = movieDTO.title
        scoreTextView.text = movieDTO.imdbRating
        Picasso.get().load(movieDTO.poster!!).into(imageView)
    }

    override fun showError(errorText: String) {
        Toast.makeText(context, errorText, Toast.LENGTH_LONG).show()
    }
}