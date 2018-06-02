package com.poviolabs.poviotestproject.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.poviolabs.poviotestproject.ui.search.SearchViewModel
import com.poviolabs.poviotestproject.viewmodel.PovioViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: PovioViewModelFactory): ViewModelProvider.Factory
}