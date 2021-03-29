package web.id.wahyou.jetpackapp.ui.main.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import dagger.android.support.DaggerFragment
import web.id.wahyou.jetpackapp.data.database.entity.TvShowEntity
import web.id.wahyou.jetpackapp.databinding.FragmentTvshowBinding
import web.id.wahyou.jetpackapp.di.ViewModelFactory
import web.id.wahyou.jetpackapp.state.Status
import web.id.wahyou.jetpackapp.ui.detail.DetailActivity
import web.id.wahyou.jetpackapp.utils.Constants.TYPE_TVSHOW
import javax.inject.Inject

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

class TvShowFragment : DaggerFragment() {

    private val binding : FragmentTvshowBinding by lazy {
        FragmentTvshowBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: TvShowViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    private val adapter : TvShowAdapter by lazy {
        TvShowAdapter{item -> showDetail(item)}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        activity?.let { setupViewModel(it) }
        observeTvShow()
    }

    private fun setupAdapter() {
        with(binding) {
            rvTvShow.also {
                it.adapter = adapter
                it.layoutManager = GridLayoutManager(requireContext(), 3)
                it.setHasFixedSize(true)
            }
        }
    }

    private fun setupViewModel(fragmentActivity: FragmentActivity) {
        fragmentActivity.let {
            viewModel = ViewModelProvider(
                it,
                factory
            )[TvShowViewModel::class.java]
        }
    }

    private fun observeTvShow() {
        with(binding){
            viewModel.getOnTheAirTvShows().observe(viewLifecycleOwner, Observer { listTvShow ->
                if (listTvShow != null) {
                    when (listTvShow.status) {
                        Status.LOADING -> shTvShow.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            shTvShow.visibility = View.GONE
                            rvTvShow.adapter?.let { adapter ->
                                when (adapter) {
                                    is TvShowAdapter -> {
                                        adapter.submitList(listTvShow.data)
                                        adapter.notifyDataSetChanged()
                                    }
                                }
                            }
                        }
                        Status.ERROR -> {
                            shTvShow.visibility = View.GONE
                            Toast.makeText(context, "Check your internet connection", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
    }

    private fun showDetail(item: TvShowEntity) {
        startActivity(
            Intent(context, DetailActivity::class.java)
                .putExtra(DetailActivity.EXTRA_DATA, item.tvShowId)
                .putExtra(DetailActivity.EXTRA_TYPE, TYPE_TVSHOW)
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