package web.id.wahyou.jetpackapp.ui.main.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.support.DaggerFragment
import web.id.wahyou.jetpackapp.R
import web.id.wahyou.jetpackapp.databinding.AdapterTabBinding
import web.id.wahyou.jetpackapp.databinding.FragmentFavoriteBinding
import web.id.wahyou.jetpackapp.ui.main.favorite.movie.FavoriteMovieFragment
import web.id.wahyou.jetpackapp.ui.main.favorite.tvshow.FavoriteTvShowFragment

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

class FavoriteFragment : DaggerFragment() {

    private val binding : FragmentFavoriteBinding by lazy {
        FragmentFavoriteBinding.inflate(layoutInflater)
    }

    private val adapter : TabPagerAdapter by lazy {
        TabPagerAdapter(this, arrayListOf(FavoriteMovieFragment(), FavoriteTvShowFragment()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
    }

    private fun setupAdapter() {
        binding.pager.adapter = adapter
        TabLayoutMediator(binding.tab, binding.pager) { tab, position ->
            when (position) {
                0 -> { tab.customView = getTabLayout(getString(R.string.title_movie), R.drawable.movie_selector) }
                1 -> { tab.customView = getTabLayout(getString(R.string.title_tvshow), R.drawable.tv_show_selector) }
            }
        }.attach()
        binding.pager.setCurrentItem(0, true)
    }

    private fun getTabLayout(title: String, icon: Int): View {
        val tabBinding = AdapterTabBinding.inflate(layoutInflater)
        tabBinding.title.text = title
        tabBinding.icon.setImageResource(icon)
        return tabBinding.root
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

}