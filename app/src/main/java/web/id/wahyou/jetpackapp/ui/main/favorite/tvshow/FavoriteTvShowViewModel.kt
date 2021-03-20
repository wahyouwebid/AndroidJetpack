package web.id.wahyou.jetpackapp.ui.main.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import web.id.wahyou.jetpackapp.data.database.entity.TvShowEntity
import web.id.wahyou.jetpackapp.data.repository.DataRepository
import javax.inject.Inject

class FavoriteTvShowViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {

    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> {
        return dataRepository.getFavoriteTvShows()
    }

}