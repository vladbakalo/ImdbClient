package com.vladbakalo.imdbcient.di

import com.vladbakalo.imdbcient.MainActivity
import com.vladbakalo.imdbcient.ui.movie_list.MovieListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}