package com.vladbakalo.imdbcient.data.di

import com.vladbakalo.imdbcient.api.MovieApi
import com.vladbakalo.imdbcient.data.repo.MovieRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Provides
    @Singleton
    fun provideMovieRepo(movieApi: MovieApi): MovieRepo{
        return MovieRepo(movieApi)
    }
}