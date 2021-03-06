package web.id.wahyou.jetpackapp.ui.main.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import web.id.wahyou.jetpackapp.data.model.DataModel
import web.id.wahyou.jetpackapp.data.repository.DataRepository

class MovieViewModel(
    private val dataRepository: DataRepository
) : ViewModel() {

    fun getNowPlayingMovies(): LiveData<List<DataModel>> = dataRepository.getNowPlayingMovies()
}