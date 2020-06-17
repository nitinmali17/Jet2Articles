package com.nitinm.jet2articles.view.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nitinm.jet2articles.application.Jet2Application
import com.nitinm.jet2articles.data.model.ArticlesData
import com.nitinm.jet2articles.domain.ArticlesDataRepository
import com.nitinm.jet2articles.domain.Jet2ArticlesService
import com.nitinm.jet2articles.util.Constants
import javax.inject.Inject

class ArticlesViewModel() : ViewModel() {

    @Inject
    lateinit var jet2ArticlesService: Jet2ArticlesService

    @Inject
    lateinit var articlesDataRepository: ArticlesDataRepository

    @Inject
    lateinit var jet2Application: Jet2Application

    var articlesListLiveData = MutableLiveData<ArrayList<ArticlesData>>()
    var articlesLoadErrorLiveData = MutableLiveData<Boolean>()
    var articlesLoadingLiveData = MutableLiveData<Boolean>()

    init {
        Jet2Application.component.inject(this)
    }

    fun CheckDbCountAndCallArticleList(pageNumber: Int, limit: Int = Constants.ARTICLES_FETCH_LIMIT) {
        articlesDataRepository.getNumberOfPagesFromDb()
            .subscribe(
                { dbRowCount ->
                    getArticlesList(pageNumber, limit, dbRowCount)
                },
                { t: Throwable ->
                    getArticlesList(pageNumber, limit, 0)
                }
            )
    }

    private fun getArticlesList(pageNumber: Int, limit: Int = Constants.ARTICLES_FETCH_LIMIT, dbDataCount: Int) {
        articlesLoadingLiveData.value = true
        if (dbDataCount >= pageNumber) {
            getArticleListFromDataBase(pageNumber)
        } else {
            jet2ArticlesService
                .getArticles(pageNumber, limit)
                .subscribe(
                    { articles ->
                        insertArticleListInDataBase(pageNumber, articles)
                    },
                    { error: Throwable ->
                        articlesLoadErrorLiveData.value = true
                        articlesLoadingLiveData.value = false
                    })
        }
    }

    private fun getArticleListFromDataBase(pageNumber: Int) {
        articlesDataRepository.getArticlesByPageNumber(pageNumber)
            .subscribe({ articleListFromDb: ArrayList<ArticlesData> ->
                articlesListLiveData.value = articleListFromDb
                articlesLoadingLiveData.value = false
                articlesLoadErrorLiveData.value = false
            }, { t: Throwable ->
                articlesLoadErrorLiveData.value = true
                articlesLoadingLiveData.value = false
            })
    }

    private fun insertArticleListInDataBase(pageNumber: Int, articleListToInsert: ArrayList<ArticlesData>) {
        articlesDataRepository.insertArticlesInDataBase(pageNumber, articleListToInsert)
            .subscribe({ t ->
                articlesListLiveData.value = articleListToInsert
                articlesLoadingLiveData.value = false
            }, { t: Throwable ->
                articlesLoadErrorLiveData.value = true
                articlesLoadingLiveData.value = false
            })
    }
}