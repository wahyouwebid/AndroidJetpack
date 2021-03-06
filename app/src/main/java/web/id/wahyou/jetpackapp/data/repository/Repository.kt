package web.id.wahyou.jetpackapp.data.repository

import androidx.lifecycle.LiveData
import web.id.wahyou.jetpackapp.data.model.DataModel

interface Repository {
    fun getNowPlayingMovies(): LiveData<List<DataModel>>

    fun getMovieDetail(movieId: Int): LiveData<DataModel>

    fun getTvShowOnTheAir(): LiveData<List<DataModel>>

    fun getTvShowDetail(tvShowId: Int): LiveData<DataModel>
}