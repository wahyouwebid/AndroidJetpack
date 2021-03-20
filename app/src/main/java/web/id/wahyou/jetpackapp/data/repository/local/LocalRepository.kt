package web.id.wahyou.jetpackapp.data.repository.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import web.id.wahyou.jetpackapp.data.database.dao.MovieDao
import web.id.wahyou.jetpackapp.data.database.entity.MovieEntity
import web.id.wahyou.jetpackapp.data.database.entity.TvShowEntity
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val movieDao: MovieDao
) {

    fun getMovies() : DataSource.Factory<Int, MovieEntity> = movieDao.getMovies()

    fun getFavoriteMovies() : DataSource.Factory<Int, MovieEntity> = movieDao.getFavoriteMovies()

    fun getTvShows() : DataSource.Factory<Int, TvShowEntity> = movieDao.getTvShows()

    fun getFavoriteTvShows() : DataSource.Factory<Int, TvShowEntity> = movieDao.getFavoriteTvShows()

    fun getDetailMovie(movieId: Int) : LiveData<MovieEntity> = movieDao.getDetailMovieById(movieId)

    fun getDetailTvShow(tvShowId: Int) : LiveData<TvShowEntity> = movieDao.getDetailTvShowById(tvShowId)

    fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)

    fun insertTvShows(tvShows: List<TvShowEntity>) = movieDao.insertTvShows(tvShows)

    fun setFavoriteMovie(movie : MovieEntity) {
        movie.isFavorite = !movie.isFavorite
        movieDao.updateMovie(movie)
    }

    fun setFavoriteTvShow(tvShow : TvShowEntity) {
        tvShow.isFavorite = !tvShow.isFavorite
        movieDao.updateTvShow(tvShow)
    }
}