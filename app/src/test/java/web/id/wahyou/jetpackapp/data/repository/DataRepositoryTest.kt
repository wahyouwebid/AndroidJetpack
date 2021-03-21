package web.id.wahyou.jetpackapp.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import web.id.wahyou.jetpackapp.LiveDataTestUtil
import web.id.wahyou.jetpackapp.PagedListUtil
import web.id.wahyou.jetpackapp.data.database.entity.MovieEntity
import web.id.wahyou.jetpackapp.data.database.entity.TvShowEntity
import web.id.wahyou.jetpackapp.data.repository.local.LocalRepository
import web.id.wahyou.jetpackapp.data.repository.remote.RemoteRepository
import web.id.wahyou.jetpackapp.state.Resource
import web.id.wahyou.jetpackapp.utils.DataDummy

class DataRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteRepository::class.java)
    private val local = Mockito.mock(LocalRepository::class.java)
    private val catalogRepository = FakeDataRepository(remote, local)

    private val listMovie = DataDummy.generateDataMovieDummy()
    private val listTvShow = DataDummy.generateDataTvShowDummy()
    private val movie = DataDummy.generateDataMovieDummy()[0]
    private val tvShow = DataDummy.generateDataTvShowDummy()[0]

    @Test
    fun getNowPlayingMovies() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getMovies()).thenReturn(dataSourceFactory)
        catalogRepository.getNowPlayingMovies()

        val movieEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDataMovieDummy()))
        Mockito.verify(local).getMovies()
        org.junit.Assert.assertNotNull(movieEntity.data)
        assertEquals(listMovie.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getTvShowOnTheAir() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        Mockito.`when`(local.getTvShows()).thenReturn(dataSourceFactory)
        catalogRepository.getTvShowOnTheAir()

        val tvShowEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDataTvShowDummy()))
        Mockito.verify(local).getTvShows()
        org.junit.Assert.assertNotNull(tvShowEntity.data)
        assertEquals(listTvShow.size.toLong(), tvShowEntity.data?.size?.toLong())
    }

    @Test
    fun getMovieDetail() {
        val dummyMovie = MutableLiveData<MovieEntity>()
        dummyMovie.value = movie
        Mockito.`when`(local.getDetailMovie(movie.movieId)).thenReturn(dummyMovie)

        val data = LiveDataTestUtil.getValue(catalogRepository.getMovieDetail(movie.movieId))
        Mockito.verify(local).getDetailMovie(movie.movieId)
        org.junit.Assert.assertNotNull(data)
        assertEquals(movie.movieId, data.movieId)
    }

    @Test
    fun getTvShowDetail() {
        val dummyTvShow = MutableLiveData<TvShowEntity>()
        dummyTvShow.value = tvShow
        Mockito.`when`(local.getDetailTvShow(tvShow.tvShowId)).thenReturn(dummyTvShow)

        val data = LiveDataTestUtil.getValue(catalogRepository.getTvShowDetail(tvShow.tvShowId))
        Mockito.verify(local).getDetailTvShow(tvShow.tvShowId)
        org.junit.Assert.assertNotNull(data)
        assertEquals(tvShow.tvShowId, data.tvShowId)
    }

    @Test
    fun getListFavoriteMovies() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getFavoriteMovies()).thenReturn(dataSourceFactory)
        catalogRepository.getFavoriteMovies()

        val movieEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDataMovieDummy()))
        Mockito.verify(local).getFavoriteMovies()
        org.junit.Assert.assertNotNull(movieEntity.data)
        assertEquals(listMovie.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getListFavoriteTvShows() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        Mockito.`when`(local.getFavoriteTvShows()).thenReturn(dataSourceFactory)
        catalogRepository.getFavoriteTvShows()

        val tvShowEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDataTvShowDummy()))
        Mockito.verify(local).getFavoriteTvShows()
        org.junit.Assert.assertNotNull(tvShowEntity.data)
        assertEquals(listTvShow.size.toLong(), tvShowEntity.data?.size?.toLong())
    }
}