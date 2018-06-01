package com.poviolabs.poviotestproject.di

import android.arch.lifecycle.ViewModelProvider
import com.poviolabs.poviotestproject.viewmodel.PovioViewModelFactory
import dagger.Binds
import dagger.Module

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: PovioViewModelFactory): ViewModelProvider.Factory
}