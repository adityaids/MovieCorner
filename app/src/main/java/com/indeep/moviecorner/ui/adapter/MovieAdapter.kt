package com.indeep.moviecorner.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.indeep.core.BuildConfig
import com.indeep.core.data.domain.model.MovieDetailModel
import com.indeep.moviecorner.databinding.MovieListItemBinding

class MovieAdapter: PagedListAdapter<MovieDetailModel, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieDetailModel>() {
            override fun areItemsTheSame(oldItem: MovieDetailModel, newItem: MovieDetailModel): Boolean {
                return oldItem.movie.title == newItem.movie.title
            }
            override fun areContentsTheSame(oldItem: MovieDetailModel, newItem: MovieDetailModel): Boolean {
                return oldItem.movie.title == newItem.movie.title
            }
        }
    }
    var onItemClick: ((MovieDetailModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    inner class MovieViewHolder(
        private val binding: MovieListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MovieDetailModel){
            binding.tvTitle.text = data.movie.title
            binding.tvRating.text = data.movie.voteAverage.toString()
            binding.ratingBar.rating = data.movie.voteAverage / 2
            Glide.with(itemView.context)
                .load(BuildConfig.IMAGE_BASE_URL+data.movie.posterPath)
                .into(binding.imgPoster)
        }

        init {
            binding.root.setOnClickListener {
                getItem(adapterPosition)?.let { it1 -> onItemClick?.invoke(it1) }
            }
        }
    }
}