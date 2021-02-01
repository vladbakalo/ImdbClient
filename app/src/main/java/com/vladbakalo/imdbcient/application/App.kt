package com.vladbakalo.imdbcient.application

import android.app.Application
import com.vladbakalo.imdbcient.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App: DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder()
            .create(this)
    }
}