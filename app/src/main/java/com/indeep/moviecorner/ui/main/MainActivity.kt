package com.indeep.moviecorner.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.chip.Chip
import com.indeep.core.data.domain.model.GenreListModel
import com.indeep.core.data.vo.Resource
import com.indeep.moviecorner.R
import com.indeep.moviecorner.databinding.ActivityMainBinding
import com.indeep.moviecorner.ui.adapter.MovieAdapter
import com.indeep.moviecorner.ui.detail.DetailActivity
import com.indeep.moviecorner.ui.detail.DetailActivity.Companion.EXTRA_DATA
import com.indeep.moviecorner.ui.dialog.MessageDialogFragment
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by inject()
    private val movieAdapter = MovieAdapter()
    private val listData = ArrayList<GenreListModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMovie.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            setHasFixedSize(false)
            adapter = movieAdapter
        }

        viewModel.getPopularMovie.observe(this,{
            if (it != null) {
                when (it) {
                    is Resource.Loading -> binding.pgsBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        movieAdapter.submitList(it.data)
                        binding.pgsBar.visibility = View.GONE
                    }
                    is Resource.Error -> {
                        binding.pgsBar.visibility = View.GONE
                        MessageDialogFragment.newInstance(
                            R.string.error,
                            it.message
                        ).show(supportFragmentManager, it.message)
                    }
                }
            }
        })
        viewModel.getAllGenre.observe(this, {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> binding.pgsBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        setChip(it.data)
                    }
                    is Resource.Error -> {
                        MessageDialogFragment.newInstance(
                            R.string.error,
                            it.message
                        ).show(supportFragmentManager, it.message)
                    }
                }
            }
        })

        binding.chipGenre.setOnCheckedChangeListener { group, checkedId ->
            binding.pgsBar.visibility = View.VISIBLE
            val position = checkedId - 1
            binding.tvType.text = listData[position].name
            getMovieByGenre(listData[position].id)
        }

        movieAdapter.onItemClick = {
            binding.pgsBar.visibility = View.VISIBLE
            val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {
                putExtra(EXTRA_DATA, it)
            }
            startActivity(intent)
        }
    }

    private fun setChip(data: List<GenreListModel>?){
        if (data != null && data.isNotEmpty()) {
            if (data != listData) {
                listData.addAll(data)
                for (genre in data){
                    val chip = Chip(this)
                    chip.text = genre.name
                    chip.isCheckable = true
                    chip.setChipBackgroundColorResource(R.color.secondary)
                    binding.chipGenre.addView(chip)
                }
            }
        }
    }

    private fun getMovieByGenre(genreId: Int){
        movieAdapter.submitList(null)
        viewModel.getMovieByGenre(genreId).observe(this,{
            if (it != null) {
                when (it) {
                    is Resource.Loading -> binding.pgsBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        movieAdapter.submitList(it.data)
                        binding.pgsBar.visibility = View.GONE
                    }
                    is Resource.Error -> {
                        binding.pgsBar.visibility = View.GONE
                        MessageDialogFragment.newInstance(
                            R.string.error,
                            it.message
                        ).show(supportFragmentManager, it.message)
                    }
                }
            }
        })
    }
}