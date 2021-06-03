package com.dicoding.eigacatalogue.view.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.eigacatalogue.DetailActivity
import com.dicoding.eigacatalogue.MovieEntity
import com.dicoding.eigacatalogue.R
import com.dicoding.eigacatalogue.databinding.ItemRowBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var listMovies = ArrayList<MovieEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val irb =
            ItemRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        return MovieViewHolder(irb)
    }

    fun setMovie(movie: List<MovieEntity>?) {
        if (movie.isNullOrEmpty()) return
        this.listMovies.clear()
        this.listMovies.addAll(movie)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size

    inner class MovieViewHolder(private val binding: ItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                itemRowTitle.text = movie.title
                itemRowRelease.text = movie.release

                Glide.with(itemView.context)
                    .load(movie.image)
                    .apply(
                        RequestOptions
                            .placeholderOf(R.drawable.ic_movie_splash)
                            .error(R.drawable.ic_baseline_error_outline_24)
                    )
                    .into(itemRowImage)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.MOVKEY, movie.id)
                    intent.putExtra(DetailActivity.STATKEY, "movie")
                    intent.putExtra(DetailActivity.TITLEKEY, movie.title)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}