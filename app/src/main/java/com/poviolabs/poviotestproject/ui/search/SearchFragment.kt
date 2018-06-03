package com.poviolabs.poviotestproject.ui.search

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingComponent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.IBinder
import android.support.v4.app.Fragment
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import com.poviolabs.poviotestproject.AppExecutors
import com.poviolabs.poviotestproject.R
import com.poviolabs.poviotestproject.binding.FragmentDataBindingComponent
import com.poviolabs.poviotestproject.databinding.SearchFragmentBinding
import com.poviolabs.poviotestproject.di.Injectable
import com.poviolabs.poviotestproject.models.Resource
import com.poviolabs.poviotestproject.util.autoCleared
import javax.inject.Inject

class SearchFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    var binding by autoCleared<SearchFragmentBinding>()

    var adapter by autoCleared<FlowerListAdapter>()

    lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.search_fragment,
                container,
                false,
                dataBindingComponent
        )

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        searchViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(SearchViewModel::class.java)
        initRecyclerView()
        val rvAdapter = FlowerListAdapter(
                dataBindingComponent = dataBindingComponent,
                appExecutors = appExecutors
        ) { repo ->
            //navController().navigate(
            //TODO open flower details screen, not needed for the test project
            //)
        }
        binding.repoList.adapter = rvAdapter
        adapter = rvAdapter

        initSearchInputListener()
        val query = savedInstanceState?.getString(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY
        doSearch(query)

        binding.callback = object : RetryCallback {
            override fun retry() {
                searchViewModel.refresh()
            }
        }
    }

    private fun initSearchInputListener() {
        binding.input.setOnEditorActionListener { view: View, actionId: Int, _: KeyEvent? ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                doSearchBasedOnInput(view)
                true
            } else {
                false
            }
        }
        binding.input.setOnKeyListener { view: View, keyCode: Int, event: KeyEvent ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                doSearchBasedOnInput(view)
                true
            } else {
                false
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(LAST_SEARCH_QUERY, searchViewModel.lastQueryValue())
    }

    private fun doSearchBasedOnInput(v: View) {
        val query = binding.input.text.toString()
        // Dismiss keyboard
        dismissKeyboard(v.windowToken)
        doSearch(query)
    }

    private fun doSearch(query: String) {
        binding.query = query
        searchViewModel.setQuery(query)
    }

    private fun initRecyclerView() {
        searchViewModel.flowers.observe(this, Observer { result ->
            binding.searchResource = Resource.success(result)
            binding.resultCount = result?.size ?: 0
            adapter.submitList(result)
            binding.executePendingBindings()
        })
        searchViewModel.networkErrors.observe(this, Observer<String> {
            binding.searchResource = Resource.error<String>(it ?: "Unknown error", null)
            binding.executePendingBindings()
        })
    }

    private fun dismissKeyboard(windowToken: IBinder) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(windowToken, 0)
    }

    companion object {
        fun newInstance(): Fragment {
            return SearchFragment()
        }

        private const val LAST_SEARCH_QUERY: String = "last_search_query"
        private const val DEFAULT_QUERY = ""
    }
}