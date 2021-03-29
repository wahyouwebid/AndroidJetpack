package web.id.wahyou.jetpackapp.ui.main.favorite.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import web.id.wahyou.jetpackapp.data.database.entity.MovieEntity
import web.id.wahyou.jetpackapp.databinding.FragmentFavoriteMovieBinding
import web.id.wahyou.jetpackapp.di.ViewModelFactory
import web.id.wahyou.jetpackapp.ui.detail.DetailActivity
import web.id.wahyou.jetpackapp.ui.main.movie.MovieAdapter
import web.id.wahyou.jetpackapp.utils.Constants
import javax.inject.Inject

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

class FavoriteMovieFragment : DaggerFragment() {

    private val binding : FragmentFavoriteMovieBinding by lazy {
        FragmentFavoriteMovieBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: FavoriteMovieViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    private val adapter : MovieAdapter by lazy {
        MovieAdapter{item-> showDetail(item)}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentFragment?.let {
            viewModel = ViewModelProvider(it, factory)[FavoriteMovieViewModel::class.java]
        }
        setupAdapter()
        observeFavoriteMovies()
    }

    private fun setupAdapter() {
        with(binding) {
            rvMovie.also {
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
            }
        }
    }

    private fun observeFavoriteMovies() {
        with(binding){
            viewModel.getFavoriteMovie().observe(viewLifecycleOwner, Observer {
                if (it != null){
                    if (it.isNullOrEmpty()){
                        rvMovie.visibility = View.GONE
                        lottieEmpty.visibility = View.VISIBLE
                    } else {
                        lottieEmpty.visibility = View.GONE
                        rvMovie.visibility = View.VISIBLE
                        adapter.submitList(it)
                        adapter.notifyDataSetChanged()
                    }
                }
            })
        }
    }

    private fun showDetail(item: MovieEntity) {
        startActivity(
            Intent(context, DetailActivity::class.java)
                .putExtra(DetailActivity.EXTRA_DATA, item.movieId)
                .putExtra(DetailActivity.EXTRA_TYPE, Constants.TYPE_MOVIE)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }
}