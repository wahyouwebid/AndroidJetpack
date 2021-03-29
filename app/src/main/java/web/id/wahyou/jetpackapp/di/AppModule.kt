package web.id.wahyou.jetpackapp.di

import android.app.Application
import dagger.Module
import dagger.Provides
import web.id.wahyou.jetpackapp.data.database.dao.MovieDao
import web.id.wahyou.jetpackapp.data.database.room.RoomDb
import web.id.wahyou.jetpackapp.data.network.ApiService
import web.id.wahyou.jetpackapp.data.repository.DataRepository
import web.id.wahyou.jetpackapp.data.repository.local.LocalRepository
import web.id.wahyou.jetpackapp.data.repository.remote.RemoteRepository
import javax.inject.Singleton

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

@Module
class AppModule {

    companion object {

        @Singleton
        @Provides
        fun provideDatabase(application: Application): RoomDb =
            RoomDb.getInstance(application)

        @Singleton
        @Provides
        fun provideDao(roomDb: RoomDb): MovieDao =
            roomDb.movieDao()

        @Singleton
        @Provides
        fun provideLocalRepository(movieDao: MovieDao): LocalRepository =
            LocalRepository(movieDao)

        @Singleton
        @Provides
        fun provideRemoteRepository(apiService: ApiService): RemoteRepository =
            RemoteRepository(apiService)

        @Singleton
        @Provides
        fun provideDataRepository(
            remoteRepository: RemoteRepository,
            localRepository: LocalRepository
        ): DataRepository = DataRepository(remoteRepository, localRepository)

        @Singleton
        @Provides
        fun provideViewModelFactory(dataRepository: DataRepository): ViewModelFactory =
            ViewModelFactory(dataRepository)

    }
}