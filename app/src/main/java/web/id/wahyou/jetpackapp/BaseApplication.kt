package web.id.wahyou.jetpackapp

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import web.id.wahyou.jetpackapp.di.DaggerAppComponent

class BaseApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().application(this).build()

}