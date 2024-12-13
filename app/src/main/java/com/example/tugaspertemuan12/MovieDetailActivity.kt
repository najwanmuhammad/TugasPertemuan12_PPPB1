package com.example.tugaspertemuan12

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.tugaspertemuan12.databinding.ActivityMovieDetailBinding
import com.example.tugaspertemuan12.model.Movie

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIES = "extra_movies"
    }

    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.imgItemPhoto.clipToOutline = true

        val detailMovies = intent.getParcelableExtra<Movie>(EXTRA_MOVIES)

        if (detailMovies != null) {
            val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
            Glide.with(this).load(IMAGE_BASE + detailMovies.poster).into(binding.imgItemPhoto)
            binding.movieItemName.text = detailMovies.title
            binding.movieItemDesc.text = detailMovies.overview
        } else {
            binding.movieItemName.text = "Data not found"
            binding.movieItemDesc.text = "Data not found"
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}