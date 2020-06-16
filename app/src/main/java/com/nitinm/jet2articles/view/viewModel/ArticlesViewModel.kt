package com.nitinm.jet2articles.view.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.nitinm.jet2articles.data.model.ArticlesData
import com.nitinm.jet2articles.di.DaggerAppComponent
import com.nitinm.jet2articles.domain.Jet2ArticlesService
import io.reactivex.functions.Consumer
import javax.inject.Inject

class ArticlesViewModel:ViewModel() {

    @Inject
    lateinit var jet2ArticlesService: Jet2ArticlesService

    init {
        DaggerAppComponent.create().inject(this)
    }
}