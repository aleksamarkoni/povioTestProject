package com.poviolabs.poviotestproject.di

import com.poviolabs.poviotestproject.util.LiveDataCallAdapterFactory
import com.poviolabs.poviotestproject.api.PovioService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideGithubService(): PovioService {
        return Retrofit.Builder()
                .baseUrl("https://flowrspot-api.herokuapp.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()
                .create(PovioService::class.java)
    }
}
