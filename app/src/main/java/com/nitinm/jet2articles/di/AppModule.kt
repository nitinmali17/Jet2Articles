package com.nitinm.jet2articles.di

import android.content.Context
import com.nitinm.jet2articles.application.Jet2Application
import com.nitinm.jet2articles.data.db.ArticleDao
import com.nitinm.jet2articles.data.db.ArticleRoomDataBase
import com.nitinm.jet2articles.domain.Jet2ArticlesService
import com.nitinm.jet2articles.network.NetworkClient
import dagger.Module
import dagger.Provides

@Module
class AppModule() {

    @Provides
    fun provideJet2ArticleService(networkClient: NetworkClient): Jet2ArticlesService =
        Jet2ArticlesService(networkClient)

    @Provides
    fun provideApplication(jet2Application: Jet2Application): Context = jet2Application

    @Provides
    fun provideArticleDataBase(context: Context): ArticleRoomDataBase = ArticleRoomDataBase.getDatabase(context)


    @Provides
    fun provideArticleDao(articleRoomDataBase: ArticleRoomDataBase): ArticleDao = articleRoomDataBase.articleDao()

}