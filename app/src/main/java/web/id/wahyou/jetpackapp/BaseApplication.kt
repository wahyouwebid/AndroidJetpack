package web.id.wahyou.jetpackapp

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import web.id.wahyou.jetpackapp.di.DaggerAppComponent

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

class BaseApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().application(this).build()

}