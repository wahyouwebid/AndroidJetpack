package web.id.wahyou.jetpackapp.ui.main.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import web.id.wahyou.jetpackapp.data.model.DataModel
import web.id.wahyou.jetpackapp.databinding.BottomSheetBinding
import web.id.wahyou.jetpackapp.databinding.FragmentMovieBinding
import web.id.wahyou.jetpackapp.di.ViewModelFactory
import web.id.wahyou.jetpackapp.ui.detail.DetailActivity
import web.id.wahyou.jetpackapp.utils.Constants.TYPE_MOVIE

class MovieFragment : Fragment() {

    private val binding : FragmentMovieBinding by lazy {
        FragmentMovieBinding.inflate(layoutInflater)
    }

    private val adapter : MovieAdapter by lazy {
        MovieAdapter{item-> showDetail(item)}
    }

    private lateinit var viewModel: MovieViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupViewModel()
    }

    private fun setupView() {
        with(binding) {
            rvMovie.also {
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
            }
        }
    }

    private fun setupViewModel() {
        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(
            this,
            factory
        )[MovieViewModel::class.java]

        with(binding) {
            viewModel.getNowPlayingMovies().observe(viewLifecycleOwner, { listMovie ->
                rvMovie.adapter?.let { adapter ->
                    when (adapter) {
                        is MovieAdapter -> {
                            adapter.setData(listMovie)
                            shPopular.visibility = View.GONE
                        }
                    }
                }
            })
        }
    }

    private fun showDetail(item: DataModel) {
        startActivity(
            Intent(context, DetailActivity::class.java)
                .putExtra(DetailActivity.EXTRA_DATA, item.id)
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