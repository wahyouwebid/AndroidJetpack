package web.id.wahyou.jetpackapp.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import web.id.wahyou.jetpackapp.data.database.entity.MovieEntity
import web.id.wahyou.jetpackapp.data.database.entity.TvShowEntity
import web.id.wahyou.jetpackapp.data.repository.DataRepository
import web.id.wahyou.jetpackapp.utils.DataDummy

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private val dummyMovie = DataDummy.generateDataMovieDummy()[0]
    private val movieId = dummyMovie.movieId
    private val dummyTvShow = DataDummy.generateDataTvShowDummy()[0]
    private val tvShowId = dummyTvShow.tvShowId

    private lateinit var viewModel: DetailViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observerMovie: Observer<MovieEntity>

    @Mock
    private lateinit var observerTvShow: Observer<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(dataRepository)
    }

    @Test
    fun getMovieDetail() {
        val movieDummy = MutableLiveData<MovieEntity>()
        movieDummy.value = dummyMovie

        Mockito.`when`(dataRepository.getMovieDetail(movieId)).thenReturn(movieDummy)

        val movieData = viewModel.getMovieDetail(movieId).value

        org.junit.Assert.assertNotNull(movieData)
        assertEquals(dummyMovie.id, movieData?.id)
        assertEquals(dummyMovie.movieId, movieData?.movieId)
        assertEquals(dummyMovie.name, movieData?.name)
        assertEquals(dummyMovie.desc, movieData?.desc)
        assertEquals(dummyMovie.poster, movieData?.poster)
        assertEquals(dummyMovie.imgPreview, movieData?.imgPreview)

        viewModel.getMovieDetail(movieId).observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyMovie)

    }

    @Test
    fun getTvShowDetail() {
        val tvShowDummy = MutableLiveData<TvShowEntity>()
        tvShowDummy.value = dummyTvShow

        Mockito.`when`(dataRepository.getTvShowDetail(tvShowId)).thenReturn(tvShowDummy)

        val tvShowData = viewModel.getTvShowDetail(tvShowId).value

        org.junit.Assert.assertNotNull(tvShowData)
        assertEquals(dummyTvShow.id, tvShowData?.id)
        assertEquals(dummyTvShow.tvShowId, tvShowData?.tvShowId)
        assertEquals(dummyTvShow.name, tvShowData?.name)
        assertEquals(dummyTvShow.desc, tvShowData?.desc)
        assertEquals(dummyTvShow.poster, tvShowData?.poster)
        assertEquals(dummyTvShow.imgPreview, tvShowData?.imgPreview)

        viewModel.getTvShowDetail(tvShowId).observeForever(observerTvShow)
        verify(observerTvShow).onChanged(dummyTvShow)
    }

    @Test
    fun setFavoriteMovies(){
        val dummyDetailMovie = DataDummy.generateDataMovieDummy()[0]
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyDetailMovie

        Mockito.lenient().`when`(dataRepository.getMovieDetail(movieId)).thenReturn(movie)

        viewModel.setFavoriteMovie(movie.value!!)
        verify(dataRepository).setFavoriteMovie(movie.value!!, true)
        verifyNoMoreInteractions(observerMovie)
    }

    @Test
    fun setFavoriteTvShow(){
        val dummyDetailTvShow = DataDummy.generateDataTvShowDummy()[0]
        val tvShow = MutableLiveData<TvShowEntity>()
        tvShow.value = dummyDetailTvShow

        Mockito.lenient().`when`(dataRepository.getTvShowDetail(tvShowId)).thenReturn(tvShow)
        viewModel.setFavoriteTvShow(tvShow.value!!)
        verify(dataRepository).setFavoriteTvShow(tvShow.value!!, true)
        verifyNoMoreInteractions(observerTvShow)
    }
}