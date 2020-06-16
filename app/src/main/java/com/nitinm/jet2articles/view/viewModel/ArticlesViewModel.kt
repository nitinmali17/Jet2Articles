package com.nitinm.jet2articles.view.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nitinm.jet2articles.data.model.ArticlesData
import com.nitinm.jet2articles.di.DaggerAppComponent
import com.nitinm.jet2articles.domain.Jet2ArticlesService
import com.nitinm.jet2articles.util.Constants
import javax.inject.Inject

class ArticlesViewModel : ViewModel() {

    @Inject
    lateinit var jet2ArticlesService: Jet2ArticlesService
    var articlesList = MutableLiveData<ArrayList<ArticlesData>>()
    var articlesLoadError = MutableLiveData<Boolean>()
    var articlesLoadingLiveData = MutableLiveData<Boolean>()

    init {
        DaggerAppComponent.create().inject(this)
    }


    fun getArticlesList(pageNumber: Int, limit: Int = Constants.ARTICLES_FETCH_LIMIT) {
        articlesLoadingLiveData.value = true

        jet2ArticlesService
            .getArticles(pageNumber, limit)
            .subscribe(
                { articles ->

                    articlesList.value = articles
                    articlesLoadingLiveData.value = false
                },
                { error: Throwable ->

                    articlesLoadError.value = true
                    articlesLoadingLiveData.value = false
                })
    }
}