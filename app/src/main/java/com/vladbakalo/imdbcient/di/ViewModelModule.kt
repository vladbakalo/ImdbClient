package com.vladbakalo.imdbcient.di

import androidx.lifecycle.ViewModel
import com.vladbakalo.imdbcient.ui.movie_detailed.MovieDetailedViewModel
import com.vladbakalo.imdbcient.ui.movie_list.MovieListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    abstract fun movieListViewModel(viewModel: MovieListViewModel?): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailedViewModel::class)
    abstract fun movieDetailedViewModel(viewModel: MovieDetailedViewModel?): ViewModel
}