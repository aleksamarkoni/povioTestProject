package com.poviolabs.poviotestproject.di

import com.poviolabs.poviotestproject.BuildConfig
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
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()
                .create(PovioService::class.java)
    }
}
