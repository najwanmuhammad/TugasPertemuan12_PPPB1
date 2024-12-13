package com.example.tugaspertemuan12

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tugaspertemuan12.databinding.MovieItemBinding
import com.example.tugaspertemuan12.model.Movie

class MovieAdapter(
    private val movies: List<Movie>,
    private val onItemClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        fun bindMovie(movie: Movie, onItemClick: (Movie) -> Unit) {
            binding.movieTitle.text = movie.title
            binding.movieOverview.text = movie.overview
            Glide.with(binding.root)
                .load(IMAGE_BASE + movie.poster)
                .into(binding.moviePoster)

            binding.root.setOnClickListener {
                onItemClick(movie) // Panggil callback saat item diklik
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bindMovie(movie, onItemClick)

    }


}