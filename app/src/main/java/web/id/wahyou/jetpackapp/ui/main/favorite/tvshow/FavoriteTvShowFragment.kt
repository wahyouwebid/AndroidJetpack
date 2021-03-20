package web.id.wahyou.jetpackapp.ui.main.favorite.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import dagger.android.support.DaggerFragment
import web.id.wahyou.jetpackapp.data.database.entity.TvShowEntity
import web.id.wahyou.jetpackapp.databinding.FragmentFavoriteTvBinding
import web.id.wahyou.jetpackapp.di.ViewModelFactory
import web.id.wahyou.jetpackapp.ui.detail.DetailActivity
import web.id.wahyou.jetpackapp.ui.main.tvshow.TvShowAdapter
import web.id.wahyou.jetpackapp.utils.Constants
import javax.inject.Inject

class FavoriteTvShowFragment : DaggerFragment() {

    private val binding : FragmentFavoriteTvBinding by lazy {
        FragmentFavoriteTvBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: FavoriteTvShowViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    private val adapter : TvShowAdapter by lazy {
        TvShowAdapter{item-> showDetail(item)}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentFragment?.let {
            viewModel = ViewModelProvider(it, factory)[FavoriteTvShowViewModel::class.java]
        }
        setupAdapter()
        observeFavoriteTvShow()
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

    private fun observeFavoriteTvShow() {
        with(binding){
            viewModel.getFavoriteTvShow().observe(viewLifecycleOwner, Observer {
                if (it != null){
                    if (it.isNullOrEmpty()){
                        rvTvShow.visibility = View.GONE
                        lottieEmpty.visibility = View.VISIBLE
                    } else {
                        lottieEmpty.visibility = View.GONE
                        rvTvShow.visibility = View.VISIBLE
                        adapter.submitList(it)
                        adapter.notifyDataSetChanged()
                    }
                }
            })
        }
    }

    private fun showDetail(item: TvShowEntity) {
        startActivity(
            Intent(context, DetailActivity::class.java)
                .putExtra(DetailActivity.EXTRA_DATA, item.tvShowId)
                .putExtra(DetailActivity.EXTRA_TYPE, Constants.TYPE_TVSHOW)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

}