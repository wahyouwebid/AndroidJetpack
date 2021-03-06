package web.id.wahyou.jetpackapp.ui.main.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import web.id.wahyou.jetpackapp.data.model.DataModel
import web.id.wahyou.jetpackapp.data.repository.DataRepository

class TvShowViewModel(
    private val dataRepository: DataRepository
) : ViewModel() {

    fun getOnTheAirTvShows(): LiveData<List<DataModel>> = dataRepository.getTvShowOnTheAir()

}