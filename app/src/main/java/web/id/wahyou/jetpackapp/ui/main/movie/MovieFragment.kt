package web.id.wahyou.jetpackapp.ui.main.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import web.id.wahyou.jetpackapp.data.database.entity.MovieEntity
import web.id.wahyou.jetpackapp.databinding.FragmentMovieBinding
import web.id.wahyou.jetpackapp.di.ViewModelFactory
import web.id.wahyou.jetpackapp.state.Status
import web.id.wahyou.jetpackapp.ui.detail.DetailActivity
import web.id.wahyou.jetpackapp.utils.Constants.TYPE_MOVIE
import javax.inject.Inject

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

class MovieFragment : DaggerFragment() {

    private val binding : FragmentMovieBinding by lazy {
        FragmentMovieBinding.inflate(layoutInflater)
    }

    private val adapter : MovieAdapter by lazy {
        MovieAdapter{item-> showDetail(item)}
    }

    private lateinit var viewModel: MovieViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        activity?.let { setupViewModel(it) }
        observeMovies()
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

    private fun setupViewModel(fragmentActivity: FragmentActivity) {
        viewModel = ViewModelProvider(fragmentActivity, factory)[MovieViewModel::class.java]
    }

    private fun observeMovies(){
        with(binding) {
            viewModel.getNowPlayingMovies().observe(viewLifecycleOwner, Observer { listMovie ->
                if (listMovie != null) {
                    when (listMovie.status) {
                        Status.LOADING -> shMovie.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            shMovie.visibility = View.GONE
                            rvMovie.adapter?.let { adapter ->
                                when (adapter) {
                                    is MovieAdapter -> {
                                        adapter.submitList(listMovie.data)
                                        adapter.notifyDataSetChanged()
                                    }
                                }
                            }
                        }
                        Status.ERROR -> {
                            shMovie.visibility = View.GONE
                            Toast.makeText(context, "Check your internet connection", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
    }

    private fun showDetail(item: MovieEntity) {
        startActivity(
            Intent(context, DetailActivity::class.java)
                .putExtra(DetailActivity.EXTRA_DATA, item.movieId)
                .putExtra(DetailActivity.EXTRA_TYPE, TYPE_MOVIE)
        )
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}