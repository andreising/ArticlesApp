package com.andreisingeleytsev.articlesapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.andreisingeleytsev.articlesapp.data.datastore.DataStoreRepository
import com.andreisingeleytsev.articlesapp.data.room.MainDataBase
import com.andreisingeleytsev.articlesapp.data.room.repository.ArticleItemRepository
import com.andreisingeleytsev.articlesapp.data.room.repository.implementations.ArticleItemRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ) = DataStoreRepository(context = context)
    @Provides
    @Singleton
    fun provideMainDatabase(app: Application): MainDataBase {
        return Room.databaseBuilder(
            app,
            MainDataBase::class.java,
            "event_db"
        ).build()
    }
    @Provides
    @Singleton
    fun provideArticleItemRepository(dataBase: MainDataBase): ArticleItemRepository{
        return ArticleItemRepositoryImpl(dataBase.articleItemDao)
    }
}