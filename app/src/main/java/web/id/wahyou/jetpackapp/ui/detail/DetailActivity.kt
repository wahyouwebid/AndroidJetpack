package web.id.wahyou.jetpackapp.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import web.id.wahyou.jetpackapp.BuildConfig
import web.id.wahyou.jetpackapp.R
import web.id.wahyou.jetpackapp.data.database.entity.MovieEntity
import web.id.wahyou.jetpackapp.data.database.entity.TvShowEntity
import web.id.wahyou.jetpackapp.databinding.ActivityDetailBinding
import web.id.wahyou.jetpackapp.di.ViewModelFactory
import web.id.wahyou.jetpackapp.utils.Constants.TYPE_MOVIE
import web.id.wahyou.jetpackapp.utils.Constants.TYPE_TVSHOW
import javax.inject.Inject

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

class DetailActivity : DaggerAppCompatActivity() {

    private val binding : ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: DetailViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViewModel()
        setupStatusBar()
    }

    private fun setupViewModel() {
        val id = intent.getIntExtra(EXTRA_DATA, 0)
        val type = intent.getStringExtra(EXTRA_TYPE)

        viewModel = ViewModelProvider(
            this@DetailActivity,
            factory
        )[DetailViewModel::class.java]

        if (type.equals(TYPE_MOVIE, ignoreCase = true)) {
            observeDetailMovie(id)

        } else if (type.equals(TYPE_TVSHOW, ignoreCase = true)) {
            observeDetailTvShow(id)
        }
    }

    private fun observeDetailMovie(movieId: Int) {
        viewModel.getMovieDetail(movieId).observe(this, Observer {
            setupView(it, null)
        })
    }

    private fun observeDetailTvShow(tvShowId: Int) {
        viewModel.getTvShowDetail(tvShowId).observe(this, Observer {
            it?.let {
                setupView(null, it)
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun setupView(movie: MovieEntity?, tvShow: TvShowEntity?) {
        with(binding) {

            val urlImage = movie?.poster ?: tvShow?.poster
            val urlCover = movie?.imgPreview ?: tvShow?.imgPreview
            val statusFavorite = movie?.isFavorite ?: tvShow?.isFavorite

            tvTitle.text = movie?.name ?: tvShow?.name
            tvDescription.text = movie?.desc ?: tvShow?.desc
            //tvRelease.text = movie?.release_date ?: tvShow?.first_air_date

            statusFavorite?.let { status ->
                setFavoriteState(status)
            }

            Glide.with(this@DetailActivity)
                .load( BuildConfig.imageUrl + urlImage)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgPoster)

            Glide.with(this@DetailActivity)
                .load( BuildConfig.imageUrl + urlCover)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgBackground)

            btnFavorite.setOnClickListener {
                setFavorite(movie, tvShow)
            }

//            tvRelease.text = dateFormat(
//                data.release_date.toString(),
//                "yyyy-mm-dd",
//                "dd MMMM yyyy"
//            )

            imgBack.setOnClickListener { finish() }
        }
    }

    private fun setFavoriteState(status: Boolean){
        with(binding){
            if (status) {
                btnFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                btnFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
        }
    }

    private fun showSnackBar(msg: String) {
        Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT).show()
    }

    private fun setFavorite(movie: MovieEntity?, tvShow: TvShowEntity?) {
        if (movie != null) {
            if (movie.isFavorite){
                showSnackBar("${movie.name} Removed from favorite")
            }else {
                showSnackBar("${movie.name} Added to favorite")
            }
            viewModel.setFavoriteMovie(movie)
        } else {
            if (tvShow != null) {
                if (tvShow.isFavorite){
                    showSnackBar("${tvShow.name} Aemoved from favorite")
                }else {
                    showSnackBar("${tvShow.name} Removed from favorite")
                }
                viewModel.setFavoriteTvShow(tvShow)
            }
        }
    }

    private fun setupStatusBar() {
        with(window) {
            setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_TYPE = "extra_type"
    }
}