package web.id.wahyou.jetpackapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import web.id.wahyou.jetpackapp.data.model.DataModel
import web.id.wahyou.jetpackapp.data.repository.DataRepository

class DetailViewModel(
    private val dataRepository: DataRepository
    ) : ViewModel() {

    fun getMovieDetail(movieId: Int): LiveData<DataModel> = dataRepository.getMovieDetail(
        movieId
    )

    fun getTvShowDetail(tvShowId: Int): LiveData<DataModel> = dataRepository.getTvShowDetail(
        tvShowId
    )
}