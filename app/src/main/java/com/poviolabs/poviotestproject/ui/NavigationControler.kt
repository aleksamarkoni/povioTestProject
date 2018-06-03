package com.poviolabs.poviotestproject.ui

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.poviolabs.poviotestproject.R
import com.poviolabs.poviotestproject.ui.search.SearchFragment
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationControler @Inject constructor() {

    fun navigateToSearchFragment(context: FragmentActivity) {
        val fragmentManager = context.supportFragmentManager
        var fragment: Fragment? = fragmentManager.findFragmentByTag(SEARCH_FRAGMENT_TAG)
        if (fragment == null) {
            fragment = SearchFragment.newInstance()
        }
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment, SEARCH_FRAGMENT_TAG)
                .commit()
    }

    fun navigateToFavorites(context: FragmentActivity) {
        val fragmentManager = context.supportFragmentManager
        var fragment: Fragment? = fragmentManager.findFragmentByTag(FAVORITES_FRAGMENT_TAG)
        if (fragment == null) {
            //TODO instantiate favorites fragment
            //fragment = FavoritesFragment.newInstance()
        }
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment, FAVORITES_FRAGMENT_TAG)
                .commit()
    }

    fun navigateToCommnets(context: FragmentActivity) {
        val fragmentManager = context.supportFragmentManager
        var fragment: Fragment? = fragmentManager.findFragmentByTag(COMMENTS_FRAGMENT_TAG)
        if (fragment == null) {
            //TODO instantiate comments fragment
            //fragment = CommentsFragment.newInstance()
        }
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment, COMMENTS_FRAGMENT_TAG)
                .commit()
    }

    fun navigateToNewSighting(context: FragmentActivity) {
        val fragmentManager = context.supportFragmentManager
        var fragment: Fragment? = fragmentManager.findFragmentByTag(NEW_SIGHTING_FRAGMENT_TAG)
        if (fragment == null) {
            //TODO instantiate new sightings fragment
            //fragment = NewSightingsFragment.newInstance()
        }
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment, NEW_SIGHTING_FRAGMENT_TAG)
                .commit()
    }

    fun navigateToSightingsList(context: FragmentActivity) {
        val fragmentManager = context.supportFragmentManager
        var fragment: Fragment? = fragmentManager.findFragmentByTag(SIGHTING_LIST_FRAGMENT_TAG)
        if (fragment == null) {
            //TODO instantiate sightings list fragment
            //fragment = SightingsListFragment.newInstance()
        }
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment, SIGHTING_LIST_FRAGMENT_TAG)
                .commit()
    }

    companion object {
        private const val FAVORITES_FRAGMENT_TAG = "favorites_fragment_tag"
        private const val COMMENTS_FRAGMENT_TAG = "comments_fragment_tag"
        private const val NEW_SIGHTING_FRAGMENT_TAG = "new_sighting_fragment_tag"
        private const val SIGHTING_LIST_FRAGMENT_TAG = "sighting_list_fragment_tag"
        private const val SEARCH_FRAGMENT_TAG = "search_fragment_tag"
    }
}