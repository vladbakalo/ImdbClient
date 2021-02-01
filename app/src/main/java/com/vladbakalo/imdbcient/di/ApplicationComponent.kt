package com.vladbakalo.imdbcient.di

import android.app.Application
import com.vladbakalo.imdbcient.api.di.NetworkModule
import com.vladbakalo.imdbcient.application.App
import com.vladbakalo.imdbcient.data.di.RepoModule
import com.vladbakalo.imdbcient.interactor.di.InteractorModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,//
    ViewModelModule::class,//
    ActivityBuilder::class,//
    FragmentBuilder::class,//

    RepoModule::class,//
    NetworkModule::class,//
    InteractorModule::class])
interface ApplicationComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder :AndroidInjector.Builder<App>()
}