package web.id.wahyou.jetpackapp.ui.main.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import web.id.wahyou.jetpackapp.data.database.entity.MovieEntity
import web.id.wahyou.jetpackapp.data.repository.DataRepository
import web.id.wahyou.jetpackapp.state.Resource
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {

    fun getNowPlayingMovies(): LiveData<Resource<PagedList<MovieEntity>>>{
        return dataRepository.getNowPlayingMovies()
    }
}