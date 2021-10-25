package com.indeep.moviecorner.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.chip.Chip
import com.indeep.core.data.domain.model.GenreListModel
import com.indeep.core.data.source.Resource
import com.indeep.moviecorner.R
import com.indeep.moviecorner.databinding.ActivityMainBinding
import com.indeep.moviecorner.ui.adapter.MovieAdapter
import com.indeep.moviecorner.ui.dialog.MessageDialogFragment
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by inject()
    private val movieAdapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMovie.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            setHasFixedSize(false)
            adapter = movieAdapter
        }
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
    }

    private fun setChip(data: List<GenreListModel>?){
        if (data != null && data.isNotEmpty()) {
            for (genre in data){
                val chip = Chip(this)
                chip.text = genre.name
                chip.isCheckable = true
                //chip.setTextColor(resources.getColor(R.color.white))
                chip.setChipBackgroundColorResource(R.color.secondary)
                binding.chipGenre.addView(chip)
            }
        }
    }
}