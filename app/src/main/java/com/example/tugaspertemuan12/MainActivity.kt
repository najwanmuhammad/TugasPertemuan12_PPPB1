package com.example.tugaspertemuan12

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugaspertemuan12.databinding.ActivityMainBinding
import com.example.tugaspertemuan12.model.Movie
import com.example.tugaspertemuan12.model.MovieResponse
import com.example.tugaspertemuan12.network.MovieApiService
import com.example.tugaspertemuan12.network.MovieApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val movies = arrayListOf<Movie>()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        getMovieData { newMovies: List<Movie> ->
            movies.clear()
            movies.addAll(newMovies)
            movieAdapter.notifyDataSetChanged() // Refresh adapter dengan data baru
        }
    }

    private fun setupRecyclerView() {
        movieAdapter = MovieAdapter(movies) { movie ->
            val intent = Intent(this, MovieDetailActivity::class.java)
            intent.putExtra(MovieDetailActivity.EXTRA_MOVIES, movie)
            startActivity(intent)
        }

        binding.rvMovie.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = movieAdapter // Pasang adapter awal (list kosong)
        }
    }

    private fun getMovieData(callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieList("542cd33e1a2ff9c331fea41dc6d6f198").enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("MainActivity", "Failed to fetch data: ${t.message}")
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    callback(response.body()?.movies ?: emptyList())
                } else {
                    Log.e("MainActivity", "Response Error: ${response.errorBody()}")
                    Log.d("MainActivity", "Request URL: ${call.request().url}")

                }
            }
        })
    }
}