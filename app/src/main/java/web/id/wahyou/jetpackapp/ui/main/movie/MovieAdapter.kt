package web.id.wahyou.jetpackapp.ui.main.movie

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import web.id.wahyou.jetpackapp.BuildConfig.imageUrl
import web.id.wahyou.jetpackapp.data.model.DataModel
import web.id.wahyou.jetpackapp.databinding.AdapterMovieBinding
import web.id.wahyou.jetpackapp.utils.Utils

class MovieAdapter (
    private val showDetail: (DataModel) -> Unit
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var data = ArrayList<DataModel>()

    fun setData(movieList: List<DataModel>?) {
        if (movieList.isNullOrEmpty()) return
        data.clear()
        data.addAll(movieList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view) {
            tvTitle.text = data[position].name
            tvDescription.text = data[position].desc.toString()
            tvRating.text = data[position].vote_average.toString()
            tvRelease.text = Utils.dateFormat(
                data[position].release_date.toString(),
                "yyyy-mm-dd",
                "yyyy"
            )
            holder.itemView.also {
                Glide.with(it.context)
                        .load( imageUrl + data[position].poster)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imgPoster)
            }

            root.setOnClickListener {
                showDetail(data[position])
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class ViewHolder(val view: AdapterMovieBinding) : RecyclerView.ViewHolder(view.root)

}