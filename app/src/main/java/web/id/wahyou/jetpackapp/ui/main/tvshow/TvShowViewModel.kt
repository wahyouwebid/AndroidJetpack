package web.id.wahyou.jetpackapp.ui.main.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import web.id.wahyou.jetpackapp.data.database.entity.TvShowEntity
import web.id.wahyou.jetpackapp.data.repository.DataRepository
import web.id.wahyou.jetpackapp.state.Resource
import javax.inject.Inject

class TvShowViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {

    fun getOnTheAirTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return dataRepository.getTvShowOnTheAir()
    }

}