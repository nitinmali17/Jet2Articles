package com.nitinm.jet2articles.di

import com.nitinm.jet2articles.domain.Jet2ArticlesService
import com.nitinm.jet2articles.network.NetworkClient
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideJet2ArticleService(networkClient: NetworkClient) : Jet2ArticlesService{
        return Jet2ArticlesService(networkClient)
    }
}