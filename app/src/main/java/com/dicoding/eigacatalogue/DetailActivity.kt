package com.dicoding.eigacatalogue

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.eigacatalogue.databinding.ActivityDetailBinding
import com.dicoding.eigacatalogue.viewmodels.DetailViewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val MOVKEY = "extra_cinema"
        const val STATKEY = "extra_status"
        const val TITLEKEY = "movie"
    }

    private lateinit var activityDetailBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(activityDetailBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val extras = intent.extras
        activityDetailBinding.progressBar.visibility = View.VISIBLE
        if (extras != null) {
            val id = extras.getInt(MOVKEY)
            val movieStatus = extras.getString(STATKEY)
            when(movieStatus) {
                "movie" -> {
                    viewModel.setSelectedMovies(id)
                    viewModel.getMovie().observe(this, {
                        setMovieItem(it, movieStatus)
                        activityDetailBinding.progressBar.visibility = View.GONE
                    })
                }
                "tvshow" -> {
                    viewModel.setSelectedMovies(id)
                    viewModel.getTVShow().observe(this, {
                        setMovieItem(it, movieStatus)
                        activityDetailBinding.progressBar.visibility = View.GONE
                    })
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setMovieItem(movie: MovieEntity, movieStatus: String?) {
        Log.d("Item", movie.toString())
        movie.apply {
            with(activityDetailBinding) {
                tvGenre.text = genre
                tvScore.text = score.toString()
                tvRelease.text = release
                tvSynopsis.text = overview
                tvCreator.text = creator
                tvTagline.text = tagLine
                textViewCinemaTitle.text = title

                Glide.with(this@DetailActivity)
                    .load(image)
                    .transform(RoundedCorners(20))
                    .apply(
                        RequestOptions
                        .placeholderOf(R.drawable.poster_fairytail)
                        .error(R.drawable.ic_baseline_error_outline_24)
                    )
                    .into(ivImage)

            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}