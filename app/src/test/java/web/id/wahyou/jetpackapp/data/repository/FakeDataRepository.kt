package web.id.wahyou.jetpackapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import web.id.wahyou.jetpackapp.data.database.entity.MovieEntity
import web.id.wahyou.jetpackapp.data.database.entity.TvShowEntity
import web.id.wahyou.jetpackapp.data.model.MovieResponse
import web.id.wahyou.jetpackapp.data.model.TvShowResponse
import web.id.wahyou.jetpackapp.data.network.NetworkBoundResource
import web.id.wahyou.jetpackapp.data.repository.local.LocalRepository
import web.id.wahyou.jetpackapp.data.repository.remote.RemoteRepository
import web.id.wahyou.jetpackapp.data.repository.remote.RemoteRepository_Factory
import web.id.wahyou.jetpackapp.data.repository.state.ApiResponse
import web.id.wahyou.jetpackapp.state.Resource
import javax.inject.Inject

class FakeDataRepository @Inject constructor(
        private val remoteRepository: RemoteRepository,
        private val localRepository: LocalRepository
) : Repository {
    override fun getNowPlayingMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>() {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(10)
                    setPageSize(10)
                }.build()
                return LivePagedListBuilder(localRepository.getMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                    data == null || data.isEmpty()


            public override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                    remoteRepository.getNowPlayingMovies()

            public override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (item in data) {
                    val movie = MovieEntity(
                            null,
                            item.id,
                            item.name,
                            item.desc,
                            item.poster,
                            item.img_preview,
                            item.release_date,
                            item.vote_average,
                            item.popularity,
                            false
                    )
                    movieList.add(movie)
                }

                localRepository.insertMovies(movieList)
            }

        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(10)
            setPageSize(10)
        }.build()
        return LivePagedListBuilder(localRepository.getFavoriteMovies(), config).build()
    }

    override fun getMovieDetail(movieId: Int): LiveData<MovieEntity> {
        return localRepository.getDetailMovie(movieId)
    }

    override fun getTvShowOnTheAir(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResponse>>() {
            public override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(10)
                    setPageSize(10)
                }.build()
                return LivePagedListBuilder(localRepository.getTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                    data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                    remoteRepository.getTvShowOnTheAir()


            public override fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (item in data) {
                    val tvShow = TvShowEntity(
                            null,
                            item.id,
                            item.name,
                            item.desc,
                            item.poster,
                            item.img_preview,
                            item.first_air_date,
                            item.vote_average,
                            item.popularity,
                            false
                    )
                    tvShowList.add(tvShow)
                }

                localRepository.insertTvShows(tvShowList)
            }

        }.asLiveData()
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(10)
            setPageSize(10)
        }.build()
        return LivePagedListBuilder(localRepository.getFavoriteTvShows(), config).build()
    }

    override fun getTvShowDetail(tvShowId: Int): LiveData<TvShowEntity> {
        return localRepository.getDetailTvShow(tvShowId)
    }

    override fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        localRepository.setFavoriteMovie(movie, state)
    }

    override fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean) {
        localRepository.setFavoriteTvShow(tvShow, state)
    }
}