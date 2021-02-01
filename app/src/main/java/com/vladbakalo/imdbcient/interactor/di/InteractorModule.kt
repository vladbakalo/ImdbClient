package com.vladbakalo.imdbcient.interactor.di

import com.vladbakalo.imdbcient.data.repo.MovieRepo
import com.vladbakalo.imdbcient.interactor.MovieInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorModule {

    @Provides
    @Singleton
    fun provideMovieInteractor(movieRepo: MovieRepo): MovieInteractor{
        return MovieInteractor(movieRepo)
    }
}