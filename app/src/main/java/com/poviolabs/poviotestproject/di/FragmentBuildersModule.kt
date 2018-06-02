package com.poviolabs.poviotestproject.di

import com.poviolabs.poviotestproject.ui.search.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeSearchFragment(): SearchFragment
}
