package web.id.wahyou.jetpackapp.ui.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import web.id.wahyou.jetpackapp.BuildConfig
import web.id.wahyou.jetpackapp.R
import web.id.wahyou.jetpackapp.data.model.DataModel
import web.id.wahyou.jetpackapp.databinding.ActivityDetailBinding
import web.id.wahyou.jetpackapp.di.ViewModelFactory
import web.id.wahyou.jetpackapp.utils.Constants.TYPE_MOVIE
import web.id.wahyou.jetpackapp.utils.Constants.TYPE_TVSHOW
import web.id.wahyou.jetpackapp.utils.Utils.dateFormat

class DetailActivity : AppCompatActivity() {

    private val binding : ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViewModel()
        setupStatusBar()
    }

    private fun setupViewModel() {
        val id = intent.getIntExtra(EXTRA_DATA, 0)
        val type = intent.getStringExtra(EXTRA_TYPE)

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(
            this@DetailActivity,
            factory
        )[DetailViewModel::class.java]

        if (type.equals(TYPE_MOVIE, ignoreCase = true)) {
            viewModel.getMovieDetail(id).observe(this, {
                setupView(it)
            })

        } else if (type.equals(TYPE_TVSHOW, ignoreCase = true)) {

            viewModel.getTvShowDetail(id).observe(this, {
                it?.let {
                    setupView(it)
                }
            })
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupView(data: DataModel) {
        with(binding) {
            tvTitle.text = data.name
            tvDescription.text = data.desc
            tvRelease.text = dateFormat(
                data.release_date.toString(),
                "yyyy-mm-dd",
                "dd MMMM yyyy"
            )
            tvPopularity.text = data.popularity.toString() + getString(R.string.title_viewers)
            tvRating.text = data.vote_average.toString()

            Glide.with(this@DetailActivity)
                .load( BuildConfig.imageUrl + data.poster)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgPoster)

            Glide.with(this@DetailActivity)
                .load( BuildConfig.imageUrl + data.img_preview)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgBackground)

            imgBack.setOnClickListener { finish() }
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