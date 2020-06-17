package com.nitinm.jet2articles.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nitinm.jet2articles.data.model.ArticlesData

class ArticlesConverter {

    companion object {
        fun convertArticleListToString(articlesList: ArrayList<ArticlesData>): String = Gson().toJson(articlesList)

        fun convertJsonToArticleList(dataString: String): ArrayList<ArticlesData> {
            val listType = object : TypeToken<ArrayList<ArticlesData>>() {}.type
            return Gson().fromJson(dataString, listType)
        }
    }
}