package web.id.wahyou.jetpackapp.ui.main.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import web.id.wahyou.jetpackapp.BuildConfig.imageUrl
import web.id.wahyou.jetpackapp.data.database.entity.MovieEntity
import web.id.wahyou.jetpackapp.databinding.AdapterMovieBinding
import web.id.wahyou.jetpackapp.utils.Utils

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

class MovieAdapter (
    private val showDetail: (MovieEntity) -> Unit
) : PagedListAdapter<MovieEntity, MovieAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.movieId == newItem.movieId
            }
            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)

        with(holder.view) {
            tvTitle.text = data?.name
            tvDescription.text = data?.desc.toString()
            tvRating.text = data?.vote_average.toString()
            tvRelease.text = Utils.dateFormat(
                data?.release_date.toString(),
                "yyyy-mm-dd",
                "yyyy"
            )
            holder.itemView.also {
                Glide.with(it.context)
                        .load( imageUrl + data?.poster)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imgPoster)
            }

            root.setOnClickListener {
                if (data != null) {
                    showDetail(data)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class ViewHolder(val view: AdapterMovieBinding) : RecyclerView.ViewHolder(view.root)

}