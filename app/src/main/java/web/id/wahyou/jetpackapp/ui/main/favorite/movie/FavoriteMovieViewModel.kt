package web.id.wahyou.jetpackapp.ui.main.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import web.id.wahyou.jetpackapp.data.database.entity.MovieEntity
import web.id.wahyou.jetpackapp.data.repository.DataRepository
import javax.inject.Inject

class FavoriteMovieViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {

    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> {
        return dataRepository.getFavoriteMovies()
    }

}