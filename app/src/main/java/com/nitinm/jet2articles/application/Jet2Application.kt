package com.nitinm.jet2articles.application

import android.app.Application
import com.nitinm.jet2articles.di.AppComponent
import com.nitinm.jet2articles.di.DaggerAppComponent

class Jet2Application : Application() {

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().application(this)
            .build()
    }
}