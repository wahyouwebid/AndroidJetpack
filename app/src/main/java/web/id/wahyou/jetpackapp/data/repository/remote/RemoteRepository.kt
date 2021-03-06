package web.id.wahyou.jetpackapp.data.repository.remote

import retrofit2.await
import web.id.wahyou.jetpackapp.data.model.ResponseMovie
import web.id.wahyou.jetpackapp.data.model.ResponseTvShow
import web.id.wahyou.jetpackapp.data.network.ApiClient
import web.id.wahyou.jetpackapp.utils.EspressoIdlingResource

class RemoteRepository {

    companion object {
        @Volatile
        private var instance: RemoteRepository? = null

        fun getInstance(): RemoteRepository =
            instance ?: synchronized(this) {
                instance ?: RemoteRepository()
            }
    }

    suspend fun getNowPlayingMovies(
        callback: LoadNowPlayingMoviesCallback
    ) {
        EspressoIdlingResource.increment()
        ApiClient.instance.getNowPlayingMovies().await().result?.let { listMovie ->
            callback.onAllMoviesReceived(
                listMovie
            )
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getMovieDetail(movieId: Int, callback: LoadMovieDetailCallback) {
        EspressoIdlingResource.increment()
        ApiClient.instance.getDetailMovie(movieId).await().let { movie ->
            callback.onMovieDetailReceived(
                movie
            )
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getTvShowOnTheAir(callback: LoadOnTheAirTvShowCallback) {
        EspressoIdlingResource.increment()
        ApiClient.instance.getTvShowOnTheAir().await().result?.let { listTvShow ->
            callback.onAllTvShowsReceived(
                listTvShow
            )
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getTvShowDetail(tvShowId: Int, callback: LoadTvShowDetailCallback) {
        EspressoIdlingResource.increment()
        ApiClient.instance.getDetailTvShow(tvShowId).await().let { tvShow ->
            callback.onTvShowDetailReceived(
                tvShow
            )
            EspressoIdlingResource.decrement()
        }
    }

    interface LoadNowPlayingMoviesCallback {
        fun onAllMoviesReceived(movieResponse: List<ResponseMovie>)
    }

    interface LoadMovieDetailCallback {
        fun onMovieDetailReceived(movieResponse: ResponseMovie)
    }

    interface LoadOnTheAirTvShowCallback {
        fun onAllTvShowsReceived(tvShowResponse: List<ResponseTvShow>)
    }

    interface LoadTvShowDetailCallback {
        fun onTvShowDetailReceived(tvShowResponse: ResponseTvShow)
    }

}