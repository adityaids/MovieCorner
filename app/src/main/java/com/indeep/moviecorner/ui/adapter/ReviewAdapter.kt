package com.indeep.moviecorner.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.indeep.core.BuildConfig
import com.indeep.core.data.domain.model.MovieDetailModel
import com.indeep.core.data.domain.model.ReviewModel
import com.indeep.moviecorner.R
import com.indeep.moviecorner.databinding.ReviewListItemBinding

class ReviewAdapter: PagedListAdapter<ReviewModel, ReviewAdapter.ReviewViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ReviewModel>() {
            override fun areItemsTheSame(oldItem: ReviewModel, newItem: ReviewModel): Boolean {
                return oldItem.author == newItem.author
            }
            override fun areContentsTheSame(oldItem: ReviewModel, newItem: ReviewModel): Boolean {
                return oldItem.author == newItem.author
            }
        }
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val binding = ReviewListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewViewHolder(binding)
    }

    inner class ReviewViewHolder(private val binding: ReviewListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ReviewModel){
            binding.tvAuthor.text = data.author
            binding.tvRating.text = data.rating.toString()
            binding.tvReview.text = data.content
            binding.tvCreated.text = data.createdAt
            Glide.with(itemView.context)
                .load(BuildConfig.IMAGE_BASE_URL+data.avatarPath)
                .placeholder(R.drawable.ic_error_image)
                .into(binding.imgAuthor)
        }

    }
}