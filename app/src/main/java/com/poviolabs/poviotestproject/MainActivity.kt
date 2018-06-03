package com.poviolabs.poviotestproject

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.poviolabs.poviotestproject.ui.NavigationControler
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var navigationControler: NavigationControler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationControler.navigateToSearchFragment(this)

        val navigation = findViewById<BottomNavigationView>(R.id.navigation)
        navigation.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.navigation_favorites -> navigationControler.navigateToFavorites(this)
                R.id.navigation_comment -> navigationControler.navigateToCommnets(this)
                R.id.navigation_new_sighting -> navigationControler.navigateToNewSighting(this)
                R.id.navigation_sighting_list -> navigationControler.navigateToSightingsList(this)
            }
        }
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector
}