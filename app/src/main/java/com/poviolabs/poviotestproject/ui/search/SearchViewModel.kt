package com.poviolabs.poviotestproject.ui.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.poviolabs.poviotestproject.models.Flower
import com.poviolabs.poviotestproject.models.Resource
import com.poviolabs.poviotestproject.repository.FlowersRepository
import com.poviolabs.poviotestproject.util.AbsentLiveData
import java.util.*
import javax.inject.Inject

class SearchViewModel @Inject constructor(repoRepository: FlowersRepository) : ViewModel() {

    private val query = MutableLiveData<String>()

    val results: LiveData<Resource<PagedList<Flower>>> = Transformations
            .switchMap(query) {
                if (it.isNullOrBlank()) {
                    AbsentLiveData.create()
                } else {
                    repoRepository.search(it)
                }
            }

    fun setQuery(originalInput: String) {
        val input = originalInput.toLowerCase(Locale.getDefault()).trim()
        if (input == query.value) {
            return
        }
        query.postValue(input)
    }

    fun refresh() {
        query.value?.let {
            query.postValue(it)
        }
    }

    fun lastQueryValue(): String? = query.value
}