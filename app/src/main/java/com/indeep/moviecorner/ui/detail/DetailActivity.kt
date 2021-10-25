package com.indeep.moviecorner.ui.detail

import android.annotation.SuppressLint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.util.Util
import com.indeep.core.BuildConfig
import com.indeep.core.data.domain.model.MovieDetailModel
import com.indeep.core.data.source.Resource
import com.indeep.core.util.Constant
import com.indeep.moviecorner.R
import com.indeep.moviecorner.databinding.ActivityDetailBinding
import com.indeep.moviecorner.ui.dialog.MessageDialogFragment
import org.koin.android.ext.android.inject
import android.util.SparseArray
import at.huber.youtubeExtractor.VideoMeta

import at.huber.youtubeExtractor.YtFile

import at.huber.youtubeExtractor.YouTubeExtractor
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory


class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_DATA: String = "extra_data"
    }
    private lateinit var binding: ActivityDetailBinding
    private var trailerLink: String = ""
    private val viewModel: DetailViewModel by inject()
    private var player: SimpleExoPlayer? = null
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<MovieDetailModel>(EXTRA_DATA) as MovieDetailModel

        binding.tvTitle.text = data.movie.title
        Glide.with(this)
            .load(BuildConfig.IMAGE_BASE_URL+data.movie.backdropPath)
            .into(binding.imgBackdrop)
        Glide.with(this)
            .load(BuildConfig.IMAGE_BASE_URL+data.movie.posterPath)
            .into(binding.imgPoster)
        binding.tvDescription.text = data.movie.description
        binding.tvRating.text = data.movie.voteAverage.toString()
        binding.ratingBar.rating = data.movie.voteAverage / 2L
        viewModel.getTrailer(data.movie.movieId).observe(this,{
            if (it != null) {
                when (it) {
                    is Resource.Loading -> null
                    is Resource.Success -> {
                        extractYoutubeUrl(Constant.SITE_URL + it.data?.get(0)?.key)
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

        binding.btnPlay.setOnClickListener{
            binding.player.root.visibility = View.VISIBLE
            startPlayer()
        }
        binding.player.btnClose.setOnClickListener {
            binding.player.root.visibility = View.GONE
            releasePlayer()
        }
    }

    @SuppressLint("StaticFieldLeak")
    fun extractYoutubeUrl(url: String){
        object : YouTubeExtractor(this@DetailActivity) {
            override fun onExtractionComplete(ytFiles: SparseArray<YtFile>?, vMeta: VideoMeta?) {
                if (ytFiles != null) {
                    val itag = 22
                    trailerLink = ytFiles[itag].url
                    Log.d("trailer", trailerLink)
                }
            }
        }.extract(url)
    }

    public override fun onPause() {
        super.onPause()
        if (Util.SDK_INT < 24) {
            releasePlayer()
        }
    }


    public override fun onStop() {
        super.onStop()
        if (Util.SDK_INT >= 24) {
            releasePlayer()
        }
    }
    public override fun onStart() {
        super.onStart()
    }

    public override fun onResume() {
        super.onResume()
    }

    private fun startPlayer(){
        player = SimpleExoPlayer.Builder(this)
            .build()
            .also { exoPlayer ->
                binding.player.playerView.player = exoPlayer
                buildMediaSource()?.let {
                    exoPlayer.prepare(it)
                }
                exoPlayer.playWhenReady = playWhenReady
                exoPlayer.play()
            }
    }

    private fun buildMediaSource(): MediaSource? {
        val dataSourceFactory = DefaultDataSourceFactory(this, "sample")
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(trailerLink))
    }

    private fun releasePlayer() {
        player?.run {
            playbackPosition = this.currentPosition
            currentWindow = this.currentWindowIndex
            playWhenReady = this.playWhenReady
            release()
        }
        player = null
    }

}