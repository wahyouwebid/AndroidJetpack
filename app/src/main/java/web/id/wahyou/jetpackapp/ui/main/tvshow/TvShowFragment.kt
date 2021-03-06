package web.id.wahyou.jetpackapp.ui.main.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import web.id.wahyou.jetpackapp.data.model.DataModel
import web.id.wahyou.jetpackapp.databinding.FragmentTvshowBinding
import web.id.wahyou.jetpackapp.di.ViewModelFactory
import web.id.wahyou.jetpackapp.ui.detail.DetailActivity
import web.id.wahyou.jetpackapp.utils.Constants.TYPE_TVSHOW

class TvShowFragment : Fragment() {

    private val binding : FragmentTvshowBinding by lazy {
        FragmentTvshowBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: TvShowViewModel

    private val adapter : TvShowAdapter by lazy {
        TvShowAdapter{item -> showDetail(item)}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupViewModel()
    }

    private fun setupView() {
        with(binding) {
            rvTvShow.also {
                it.adapter = adapter
                it.layoutManager = GridLayoutManager(requireContext(), 3)
                it.setHasFixedSize(true)
            }
        }
    }

    private fun setupViewModel() {
        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(
            this,
            factory
        )[TvShowViewModel::class.java]

        with(binding) {
            viewModel.getOnTheAirTvShows().observe(viewLifecycleOwner, { listTvShow ->
                rvTvShow.adapter?.let { adapter ->
                    when (adapter) {
                        is TvShowAdapter -> {
                            adapter.setData(listTvShow)
                            shTvShow.visibility = View.GONE
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