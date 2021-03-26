package web.id.wahyou.jetpackapp.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import web.id.wahyou.jetpackapp.data.database.entity.MovieEntity
import web.id.wahyou.jetpackapp.data.database.entity.TvShowEntity
import web.id.wahyou.jetpackapp.state.Resource

interface Repository {
    fun getNowPlayingMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    fun getMovieDetail(movieId: Int): LiveData<MovieEntity>

    fun getTvShowOnTheAir(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>>

    fun getTvShowDetail(tvShowId: Int): LiveData<TvShowEntity>

    fun setFavoriteMovie(movie: MovieEntity, state : Boolean)

    fun setFavoriteTvShow(tvShow: TvShowEntity, state : Boolean)
}