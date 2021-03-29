package web.id.wahyou.jetpackapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import web.id.wahyou.jetpackapp.data.repository.DataRepository
import web.id.wahyou.jetpackapp.ui.detail.DetailViewModel
import web.id.wahyou.jetpackapp.ui.main.favorite.movie.FavoriteMovieViewModel
import web.id.wahyou.jetpackapp.ui.main.favorite.tvshow.FavoriteTvShowViewModel
import web.id.wahyou.jetpackapp.ui.main.movie.MovieViewModel
import web.id.wahyou.jetpackapp.ui.main.tvshow.TvShowViewModel
import javax.inject.Inject

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

class ViewModelFactory @Inject constructor(
    private val dataRepository: DataRepository
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteMovieViewModel::class.java) -> {
                FavoriteMovieViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteTvShowViewModel::class.java) -> {
                FavoriteTvShowViewModel(dataRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}