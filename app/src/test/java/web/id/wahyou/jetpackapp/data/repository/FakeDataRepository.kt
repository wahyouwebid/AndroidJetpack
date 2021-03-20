package web.id.wahyou.jetpackapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import web.id.wahyou.jetpackapp.data.model.MovieResponse
import web.id.wahyou.jetpackapp.data.model.TvShowResponse
import web.id.wahyou.jetpackapp.data.repository.remote.RemoteRepository

class FakeDataRepository(
    private val remoteRepository: RemoteRepository
) : Repository {

    override fun getNowPlayingMovies(): LiveData<List<DataModel>> {
        val listMovieResult = MutableLiveData<List<DataModel>>()
        CoroutineScope(IO).launch {
            remoteRepository.getNowPlayingMovies(object : RemoteRepository.LoadNowPlayingMoviesCallback{
                override fun onAllMoviesReceived(movieResponse: List<MovieResponse>) {
                    val movieList = ArrayList<DataModel>()
                    for (response in movieResponse){
                        val movie = DataModel(
                            response.id,
                            response.name,
                            response.desc,
                            response.poster,
                            response.img_preview,
                            response.release_date,
                            response.vote_average,
                            response.popularity
                        )
                        movieList.add(movie)
                    }
                    listMovieResult.postValue(movieList)
                }
            })
        }

        return listMovieResult
    }

    override fun getMovieDetail(movieId: Int): LiveData<DataModel> {
        val movieResult = MutableLiveData<DataModel>()
        CoroutineScope(IO).launch {
            remoteRepository.getMovieDetail(movieId, object : RemoteRepository.LoadMovieDetailCallback{
                override fun onMovieDetailReceived(movieResponse: MovieResponse) {
                    val movie = DataModel(
                        movieResponse.id,
                        movieResponse.name,
                        movieResponse.desc,
                        movieResponse.poster,
                        movieResponse.img_preview,
                        movieResponse.release_date,
                        movieResponse.vote_average,
                        movieResponse.popularity
                    )

                    movieResult.postValue(movie)
                }
            })
        }

        return movieResult
    }

    override fun getTvShowOnTheAir(): LiveData<List<DataModel>> {
        val listTvShowResult = MutableLiveData<List<DataModel>>()
        CoroutineScope(IO).launch {
            remoteRepository.getTvShowOnTheAir(object : RemoteRepository.LoadOnTheAirTvShowCallback{
                override fun onAllTvShowsReceived(tvShowResponse: List<TvShowResponse>) {
                    val tvShowList = ArrayList<DataModel>()
                    for (response in tvShowResponse){
                        val tvShow = DataModel(
                            response.id,
                            response.name,
                            response.desc,
                            response.poster,
                            response.img_preview,
                            response.first_air_date,
                            response.vote_average,
                            response.popularity
                        )
                        tvShowList.add(tvShow)
                    }

                    listTvShowResult.postValue(tvShowList)
                }
            })
        }

        return listTvShowResult
    }

    override fun getTvShowDetail(tvShowId: Int): LiveData<DataModel> {
        val tvShowResult = MutableLiveData<DataModel>()
        CoroutineScope(IO).launch {
            remoteRepository.getTvShowDetail(tvShowId, object : RemoteRepository.LoadTvShowDetailCallback{
                override fun onTvShowDetailReceived(tvShowResponse: TvShowResponse) {
                    val tvShow = DataModel(
                        tvShowResponse.id,
                        tvShowResponse.name,
                        tvShowResponse.desc,
                        tvShowResponse.poster,
                        tvShowResponse.img_preview,
                        tvShowResponse.first_air_date,
                        tvShowResponse.vote_average,
                        tvShowResponse.popularity
                    )

                    tvShowResult.postValue(tvShow)
                }
            })
        }

        return tvShowResult
    }

}