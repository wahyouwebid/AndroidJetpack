package web.id.wahyou.jetpackapp.ui.main.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import web.id.wahyou.jetpackapp.data.database.entity.TvShowEntity
import web.id.wahyou.jetpackapp.data.repository.DataRepository
import web.id.wahyou.jetpackapp.state.Resource

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var tvShowPagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(dataRepository)
    }

    @Test
    fun getOnTheAirTvShows() {
        val dummyTvShow = Resource.success(tvShowPagedList)
        Mockito.`when`(dummyTvShow.data?.size).thenReturn(11)
        val tvShow = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShow.value = dummyTvShow

        Mockito.`when`(dataRepository.getTvShowOnTheAir()).thenReturn(tvShow)
        val tvShowEntity = viewModel.getOnTheAirTvShows().value?.data
        Mockito.verify(dataRepository).getTvShowOnTheAir()
        org.junit.Assert.assertNotNull(tvShowEntity)
        org.junit.Assert.assertEquals(11, tvShowEntity?.size)

        viewModel.getOnTheAirTvShows().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTvShow)
    }
}