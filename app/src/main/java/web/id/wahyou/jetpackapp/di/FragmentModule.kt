package web.id.wahyou.jetpackapp.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import web.id.wahyou.jetpackapp.ui.main.favorite.FavoriteFragment
import web.id.wahyou.jetpackapp.ui.main.favorite.movie.FavoriteMovieFragment
import web.id.wahyou.jetpackapp.ui.main.favorite.tvshow.FavoriteTvShowFragment
import web.id.wahyou.jetpackapp.ui.main.movie.MovieFragment
import web.id.wahyou.jetpackapp.ui.main.tvshow.TvShowFragment

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeMovieFragment() : MovieFragment

    @ContributesAndroidInjector
    abstract fun contributeTvShowFragment() : TvShowFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoriteFragment() : FavoriteFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoriteMovieFragment() : FavoriteMovieFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoriteTvShowFragment() : FavoriteTvShowFragment
}