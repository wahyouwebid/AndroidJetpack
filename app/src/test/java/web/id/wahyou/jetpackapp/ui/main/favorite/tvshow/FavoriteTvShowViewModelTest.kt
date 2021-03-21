package web.id.wahyou.jetpackapp.ui.main.favorite.tvshow

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
import web.id.wahyou.jetpackapp.data.database.entity.TvShowEntity
import web.id.wahyou.jetpackapp.data.repository.DataRepository

@RunWith(MockitoJUnitRunner::class)
class FavoriteTvShowViewModelTest {

    private lateinit var viewModel: FavoriteTvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var tvShowPagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteTvShowViewModel(dataRepository)
    }

    @Test
    fun getFavoriteTvShow() {
        val dummyTvShow = tvShowPagedList
        Mockito.`when`(dummyTvShow.size).thenReturn(5)
        val tvShow = MutableLiveData<PagedList<TvShowEntity>>()
        tvShow.value = dummyTvShow

        Mockito.`when`(dataRepository.getFavoriteTvShows()).thenReturn(tvShow)
        val tvShowEntity = viewModel.getFavoriteTvShow().value
        Mockito.verify(dataRepository).getFavoriteTvShows()
        Assert.assertNotNull(tvShowEntity)
        Assert.assertEquals(5, tvShowEntity?.size)

        viewModel.getFavoriteTvShow().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTvShow)
    }
}
