package com.oss.gallery.di

import android.content.Context
import androidx.room.Room
import com.oss.gallery.BuildConfig
import com.oss.gallery.feature_authorization.data.storage.TokenStorage
import com.oss.gallery.feature_posts.data.database.PostsDB
import com.oss.gallery.feature_posts.data.database.PostsDao
import com.oss.gallery.feature_posts.data.database.PostsStorage
import com.oss.gallery.feature_posts.data.repository.BasePictureModelMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StoragesModule {

    @Singleton
    @Provides
    fun provideBasePictureModelMapper(): BasePictureModelMapper = BasePictureModelMapper()

    @Singleton
    @Provides
    fun provideTokenStorage(
        @ApplicationContext app: Context,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): TokenStorage = TokenStorage(app, ioDispatcher)

    @Singleton
    @Provides
    fun providePostsDB(
        @ApplicationContext app: Context,
    ): PostsDB {
        return Room.databaseBuilder(
            app,
            PostsDB::class.java,
            BuildConfig.DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providePostsDao(postsDB: PostsDB): PostsDao {
        return postsDB.postsDao()
    }

    @Singleton
    @Provides
    fun providePostsStorage(
        dao: PostsDao,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): PostsStorage = PostsStorage(dao, ioDispatcher)
}