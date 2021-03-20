package web.id.wahyou.jetpackapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import web.id.wahyou.jetpackapp.data.database.entity.MovieEntity
import web.id.wahyou.jetpackapp.data.database.entity.TvShowEntity
import web.id.wahyou.jetpackapp.data.repository.DataRepository
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {

    fun getMovieDetail(movieId: Int): LiveData<MovieEntity> =
        dataRepository.getMovieDetail(movieId)

    fun getTvShowDetail(tvShowId: Int): LiveData<TvShowEntity> =
        dataRepository.getTvShowDetail(tvShowId)

    fun setFavoriteMovie(movie: MovieEntity){
        dataRepository.setFavoriteMovie(movie)
    }

    fun setFavoriteTvShow(tvShow: TvShowEntity){
        dataRepository.setFavoriteTvShow(tvShow)
    }
}