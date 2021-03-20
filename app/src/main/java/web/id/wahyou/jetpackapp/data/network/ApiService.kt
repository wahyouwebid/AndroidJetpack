package web.id.wahyou.jetpackapp.data.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import web.id.wahyou.jetpackapp.BuildConfig
import web.id.wahyou.jetpackapp.data.model.MovieResponse
import web.id.wahyou.jetpackapp.data.model.Response
import web.id.wahyou.jetpackapp.data.model.TvShowResponse

interface ApiService {

    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = BuildConfig.apiKey
    ) : Call<Response<MovieResponse>>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.apiKey
    ) : Call<MovieResponse>

    @GET("tv/on_the_air")
    fun getTvShowOnTheAir(
        @Query("api_key") apiKey: String = BuildConfig.apiKey
    ) : Call<Response<TvShowResponse>>

    @GET("tv/{tv_id}")
    fun getDetailTvShow(
        @Path("tv_id") tvShowId: Int,
        @Query("api_key") apiKey: String = BuildConfig.apiKey
    ) : Call<TvShowResponse>

}