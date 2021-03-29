package web.id.wahyou.jetpackapp.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import web.id.wahyou.jetpackapp.ui.detail.DetailActivity
import web.id.wahyou.jetpackapp.ui.main.MainActivity

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeHomeActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailActivity(): DetailActivity

}