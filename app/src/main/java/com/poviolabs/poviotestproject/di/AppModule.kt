package com.poviolabs.poviotestproject.di

import android.app.Application
import android.arch.persistence.room.Room
import com.poviolabs.poviotestproject.BuildConfig
import com.poviolabs.poviotestproject.api.PovioService
import com.poviolabs.poviotestproject.db.FlowersDao
import com.poviolabs.poviotestproject.db.PovioDb
import com.poviolabs.poviotestproject.util.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideGithubService(): PovioService {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()


        return Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()
                .create(PovioService::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): PovioDb {
        return Room
                .databaseBuilder(app, PovioDb::class.java, "povio.db")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun provideFlowersDao(db: PovioDb): FlowersDao {
        return db.flowerDao()
    }
}
