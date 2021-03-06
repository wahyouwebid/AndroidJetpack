package web.id.wahyou.jetpackapp.ui.main.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import web.id.wahyou.jetpackapp.data.model.DataModel
import web.id.wahyou.jetpackapp.data.repository.DataRepository
import web.id.wahyou.jetpackapp.utils.DataDummy

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private val dummyMovie = DataDummy.generateDataMovieDummy()

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<List<DataModel>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(dataRepository)
    }

    @Test
    fun getNowPlayingMovies() {
        val movie = MutableLiveData<List<DataModel>>()
        movie.value = dummyMovie

        Mockito.`when`(dataRepository.getNowPlayingMovies()).thenReturn(movie)

        val dataListMovie = viewModel.getNowPlayingMovies().value

        verify(dataRepository).getNowPlayingMovies()
        Assert.assertNotNull(dataListMovie)
        Assert.assertEquals(10, dataListMovie?.size)

        viewModel.getNowPlayingMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}