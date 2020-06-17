package com.nitinm.jet2articles.di

import com.nitinm.jet2articles.application.Jet2Application
import com.nitinm.jet2articles.view.viewModel.ArticlesViewModel
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Jet2Application): Builder

        fun build(): AppComponent
    }

    fun inject(jet2Application: Jet2Application)
    fun inject(articlesViewModel: ArticlesViewModel)
}