package web.id.wahyou.jetpackapp.ui.main.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import web.id.wahyou.jetpackapp.data.database.entity.MovieEntity
import web.id.wahyou.jetpackapp.data.repository.DataRepository

@RunWith(MockitoJUnitRunner::class)
class FavoriteMovieViewModelTest {

    private lateinit var viewModel: FavoriteMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>


    @Before
    fun setUp() {
        viewModel = FavoriteMovieViewModel(dataRepository)
    }

    @Test
    fun getFavoriteMovie() {

        val dummyMovie = moviePagedList
        Mockito.`when`(dummyMovie.size).thenReturn(5)
        val movie = MutableLiveData<PagedList<MovieEntity>>()
        movie.value = dummyMovie

        Mockito.`when`(dataRepository.getFavoriteMovies()).thenReturn(movie)
        val movieEntity = viewModel.getFavoriteMovie().value
        Mockito.verify(dataRepository).getFavoriteMovies()
        Assert.assertNotNull(movieEntity)
        Assert.assertEquals(5, movieEntity?.size)

        viewModel.getFavoriteMovie().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovie)

    }
}
