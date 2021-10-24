package com.indeep.moviecorner.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.indeep.moviecorner.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}