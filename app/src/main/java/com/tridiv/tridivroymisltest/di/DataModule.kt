package com.tridiv.tridivroymisltest.di

import android.content.Context
import androidx.room.Room
import com.tridiv.tridivroymisltest.data.RepositoryImplementation
import com.tridiv.tridivroymisltest.data.db.AppDatabase
import com.tridiv.tridivroymisltest.data.db.TvDataDao
import com.tridiv.tridivroymisltest.data.network.RestApiService
import com.tridiv.tridivroymisltest.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun bindRepo(
        @ApplicationContext context: Context,
        apiService: RestApiService,
        tvDataDao: TvDataDao,

        ): Repository =
        RepositoryImplementation(
            apiService,
            tvDataDao,
            context
        )

    @Singleton
    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "gm-db"
    ).build()

    @Singleton
    @Provides
    fun providesTvDao(appDatabase: AppDatabase) =
        appDatabase.getTvDao()
}