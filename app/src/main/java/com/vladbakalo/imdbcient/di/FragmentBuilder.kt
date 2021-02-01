package com.vladbakalo.imdbcient.di

import com.vladbakalo.imdbcient.ui.movie_detailed.MovieDetailedFragment
import com.vladbakalo.imdbcient.ui.movie_list.MovieListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract fun contributeMovieListFragment(): MovieListFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieDetailedFragment(): MovieDetailedFragment
}