package com.nitinm.jet2articles.domain

import com.nitinm.jet2articles.data.db.ArticleDao
import com.nitinm.jet2articles.data.model.ArticleDataEntity
import com.nitinm.jet2articles.data.model.ArticlesData
import com.nitinm.jet2articles.util.ArticlesConverter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ArticlesDataRepository @Inject constructor(var articleDao: ArticleDao) {

    fun insertArticlesInDataBase(pageNumber: Int, articlesDataList: ArrayList<ArticlesData>): Observable<Unit> {
        return Observable.fromCallable {
            articleDao.insert(
                ArticleDataEntity(
                    pageNumber,
                    ArticlesConverter.convertArticleListToString(articlesDataList)
                )
            )
        }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getNumberOfPagesFromDb(): Observable<Int> {
        return Observable.fromCallable { articleDao.getNumberOfData() }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getArticlesByPageNumber(pageNumber: Int): Observable<ArrayList<ArticlesData>> {
        return Observable.fromCallable {
            articleDao.getListByPageNumber(pageNumber)
        }.map { articleString: String ->
            ArticlesConverter.convertJsonToArticleList(articleString)
        }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }
}