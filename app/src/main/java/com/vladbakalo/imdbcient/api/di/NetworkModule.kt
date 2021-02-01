package com.vladbakalo.imdbcient.api.di

import com.vladbakalo.imdbcient.api.MovieApi
import com.vladbakalo.imdbcient.common.Constants.API_KEY
import com.vladbakalo.imdbcient.common.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideMovieRepo(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .cache(null)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor { chain ->
                var request = chain.request()

                val url = request.url().newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .build()

                request = request.newBuilder()
                    .url(url)
                    .build()

                return@addInterceptor chain.proceed(request)
            }

        return builder.build()
    }
}