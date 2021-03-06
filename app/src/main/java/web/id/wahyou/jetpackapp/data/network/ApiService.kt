package web.id.wahyou.jetpackapp.data.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import web.id.wahyou.jetpackapp.BuildConfig
import web.id.wahyou.jetpackapp.data.model.Response
import web.id.wahyou.jetpackapp.data.model.ResponseMovie
import web.id.wahyou.jetpackapp.data.model.ResponseTvShow

interface ApiService {

    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = BuildConfig.apiKey
    ) : Call<Response<ResponseMovie>>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.apiKey
    ) : Call<ResponseMovie>

    @GET("tv/on_the_air")
    fun getTvShowOnTheAir(
        @Query("api_key") apiKey: String = BuildConfig.apiKey
    ) : Call<Response<ResponseTvShow>>

    @GET("tv/{tv_id}")
    fun getDetailTvShow(
        @Path("tv_id") tvShowId: Int,
        @Query("api_key") apiKey: String = BuildConfig.apiKey
    ) : Call<ResponseTvShow>

}