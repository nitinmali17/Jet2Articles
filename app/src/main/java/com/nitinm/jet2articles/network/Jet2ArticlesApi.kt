package com.nitinm.jet2articles.network

import com.nitinm.jet2articles.data.model.ArticlesData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Jet2ArticlesApi {

    @GET("/jet2/api/v1/blogs")
    fun getArticles(@Query("page") pageNumber:Int, @Query("limit") limit:Int) :Observable<ArrayList<ArticlesData>>
}