package com.example.physicswallahassignment.di

import com.example.physicswallahassignment.data.remote.CharacterDataSource
import com.example.physicswallahassignment.data.remote.api.RickAndMortyApiService
import com.example.physicswallahassignment.data.repository.CharacterRepositoryImpl
import com.example.physicswallahassignment.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    private const val BASE_URL = "https://rickandmortyapi.com/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): RickAndMortyApiService {
        return retrofit.create(RickAndMortyApiService::class.java)
    }

    @Provides
    fun characterDataSource(apiService: RickAndMortyApiService): CharacterDataSource {
        return CharacterDataSource(apiService)
    }

    @Provides
    fun characterRepository(characterDataSource: CharacterDataSource): CharacterRepository {
        return CharacterRepositoryImpl(characterDataSource)
    }

}
