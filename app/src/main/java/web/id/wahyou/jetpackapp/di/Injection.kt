package web.id.wahyou.jetpackapp.di

import web.id.wahyou.jetpackapp.data.repository.DataRepository
import web.id.wahyou.jetpackapp.data.repository.remote.RemoteRepository

object Injection {
    fun provideCatalogRepository(): DataRepository {
        val remoteDataSource = RemoteRepository.getInstance()
        return DataRepository.getInstance(remoteDataSource)
    }
}