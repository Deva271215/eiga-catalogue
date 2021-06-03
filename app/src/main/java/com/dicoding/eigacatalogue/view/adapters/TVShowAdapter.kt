package com.dicoding.eigacatalogue.view.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.eigacatalogue.DetailActivity
import com.dicoding.eigacatalogue.MovieEntity
import com.dicoding.eigacatalogue.R
import com.dicoding.eigacatalogue.databinding.ItemRowBinding

class TVShowAdapter : RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder>() {

    private var listTVShows = ArrayList<MovieEntity>()

    fun setTVShow(tvs: List<MovieEntity>?) {
        if (tvs.isNullOrEmpty()) return
        this.listTVShows.clear()
        this.listTVShows.addAll(tvs)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowViewHolder {
        val irb =
            ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVShowViewHolder(irb)
    }

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        val tvs = listTVShows[position]
        holder.bind(tvs)
    }

    override fun getItemCount(): Int = listTVShows.size

    inner class TVShowViewHolder(private val binding: ItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: MovieEntity) {
            with(binding) {
                itemRowTitle.text = tvShow.title
                itemRowRelease.text = tvShow.release

                Glide.with(itemView.context)
                    .load(tvShow.image)
                    .apply(
                        RequestOptions
                        .placeholderOf(R.drawable.ic_movie_splash)
                        .error(R.drawable.ic_baseline_error_outline_24)
                    )
                    .into(itemRowImage)
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.MOVKEY, tvShow.id)
                intent.putExtra(DetailActivity.STATKEY, "tvshow")
                intent.putExtra(DetailActivity.TITLEKEY, tvShow.title)
                itemView.context.startActivity(intent)
            }
        }
    }
}