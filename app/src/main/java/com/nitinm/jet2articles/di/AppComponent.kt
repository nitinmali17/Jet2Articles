package com.nitinm.jet2articles.di

import com.nitinm.jet2articles.view.viewModel.ArticlesViewModel
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(articlesViewModel: ArticlesViewModel)
}