package web.id.wahyou.jetpackapp.data.repository.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.await
import web.id.wahyou.jetpackapp.data.model.MovieResponse
import web.id.wahyou.jetpackapp.data.model.TvShowResponse
import web.id.wahyou.jetpackapp.data.network.ApiService
import web.id.wahyou.jetpackapp.data.repository.state.ApiResponse
import web.id.wahyou.jetpackapp.utils.EspressoIdlingResource
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val apiService: ApiService) {

    fun getNowPlayingMovies(): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()
        val resultMovieResponse = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getNowPlayingMovies().await()
                resultMovieResponse.postValue(ApiResponse.success(response.result!!))
            } catch (e: IOException) {
                e.printStackTrace()
                resultMovieResponse.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return resultMovieResponse
    }

    fun getTvShowOnTheAir(): LiveData<ApiResponse<List<TvShowResponse>>> {
        EspressoIdlingResource.increment()
        val resultTvShowResponse = MutableLiveData<ApiResponse<List<TvShowResponse>>>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getTvShowOnTheAir().await()
                resultTvShowResponse.postValue(ApiResponse.success(response.result!!))
            } catch (e: IOException) {
                e.printStackTrace()
                resultTvShowResponse.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return resultTvShowResponse
    }

}