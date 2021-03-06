package web.id.wahyou.jetpackapp.ui.main.tvshow

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
class TvShowViewModelTest {

    private val dummyTvShow = DataDummy.generateDataTvShowDummy()

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<List<DataModel>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(dataRepository)
    }

    @Test
    fun getOnTheAirTvShows() {
        val tvShow = MutableLiveData<List<DataModel>>()
        tvShow.value = dummyTvShow

        Mockito.`when`(dataRepository.getTvShowOnTheAir()).thenReturn(tvShow)

        val dataListTvShow = viewModel.getOnTheAirTvShows().value

        verify(dataRepository).getTvShowOnTheAir()
        Assert.assertNotNull(dataListTvShow)
        Assert.assertEquals(10, dataListTvShow?.size)

        viewModel.getOnTheAirTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}