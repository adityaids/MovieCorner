package com.indeep.moviecorner.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.indeep.moviecorner.R
import com.indeep.moviecorner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}