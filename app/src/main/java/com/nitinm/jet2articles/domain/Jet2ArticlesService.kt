package com.nitinm.jet2articles.domain

import com.nitinm.jet2articles.data.model.ArticlesData
import com.nitinm.jet2articles.network.Jet2ArticlesApi
import com.nitinm.jet2articles.network.NetworkClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Jet2ArticlesService @Inject constructor(private val networkClient: NetworkClient) :Jet2ArticlesApi {

    override fun getArticles(pageNumber: Int, limit: Int): Observable<List<ArticlesData>> {
        return networkClient.getRetrofitClient()
            .create(Jet2ArticlesApi::class.java)
            .getArticles(pageNumber,limit)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }
}