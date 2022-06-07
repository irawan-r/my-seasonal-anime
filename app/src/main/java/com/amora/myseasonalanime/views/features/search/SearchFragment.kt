package com.amora.myseasonalanime.views.features.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.amora.myseasonalanime.data.source.paging.load.ReposLoadStateAdapter
import com.amora.myseasonalanime.databinding.FragmentSearchAnimeBinding
import com.amora.myseasonalanime.di.Injection
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: FragmentSearchAnimeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSearchAnimeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModelFactory = Injection.provideViewModelFactory(requireContext(), this)
        viewModel = ViewModelProvider(this, viewModelFactory)[SearchViewModel::class.java]

        // bind the state
        binding.bindState(
            uiState = viewModel.state,
            pagingData = viewModel.pagingDataFlow,
            uiActions = viewModel.accept
        )
    }

    /**
     * Binds the [UiState] provided  by the [SearchViewModel] to the UI,
     * and allows the UI to feed back user actions to it.
     */
    private fun FragmentSearchAnimeBinding.bindState(
        uiState: StateFlow<UiState>,
        pagingData: Flow<PagingData<UiModel>>,
        uiActions: (UiAction) -> Unit,
    ) {
        val adapter = SearchAnimeAdapter(SearchAnimeAdapter.AnimeListener { id -> showDetail(id) })
        val header = ReposLoadStateAdapter { adapter.retry() }
        binding.searchAnimeRv.adapter = adapter.withLoadStateHeaderAndFooter(
            header,
            footer = ReposLoadStateAdapter { adapter.retry() }
        )

        bindSearch(
            uiState = uiState,
            onQueryChanged = uiActions
        )

        bindList(
            header = header,
            repoAdapter = adapter,
            uiState = uiState,
            pagingData = pagingData,
            onScrollChanged = uiActions
        )
    }

    private fun FragmentSearchAnimeBinding.bindSearch(
        uiState: StateFlow<UiState>,
        onQueryChanged: (UiAction.Search) -> Unit,
    ) {
        searchAnime.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                updateRepoListFromInput(onQueryChanged)
                true
            } else {
                false
            }
        }

        searchAnime.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                updateRepoListFromInput(onQueryChanged)
                true
            } else {
                false
            }
        }

        lifecycleScope.launch {
            uiState.map { it.query }
                .distinctUntilChanged()
                .collect(searchAnime::setText)
        }
    }

    private fun FragmentSearchAnimeBinding.updateRepoListFromInput(onQueryChanged: (UiAction.Search) -> Unit) {
        searchAnime.text.trim().let {
            if (it.isNotEmpty()) {
                searchAnimeRv.scrollToPosition(0)
                onQueryChanged(UiAction.Search(query = it.toString()))
            }
        }
    }

    private fun FragmentSearchAnimeBinding.bindList(
        header: ReposLoadStateAdapter,
        repoAdapter: SearchAnimeAdapter,
        uiState: StateFlow<UiState>,
        pagingData: Flow<PagingData<UiModel>>,
        onScrollChanged: (UiAction.Scroll) -> Unit,
    ) {
        retryButton.setOnClickListener { repoAdapter.retry() }
        searchAnimeRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy != 0) onScrollChanged(UiAction.Scroll(currentQuery = uiState.value.query))
            }
        })

        val notLoading = repoAdapter.loadStateFlow
            // Only emit when REFRESH LoadState for the paging sources changes
            .distinctUntilChangedBy { it.source.refresh }
            // Only react to cases where REFRESH completes i.e.,  NotLoading.
            .map { it.source.refresh is LoadState.NotLoading }

        val hasNotScrolledForCurrentSearch = uiState
            .map { it.hasNotScrolledForCurrentSearch }
            .distinctUntilChanged()

        val shouldScrollTop = combine(
            notLoading,
            hasNotScrolledForCurrentSearch,
            Boolean::and
        )
            .distinctUntilChanged()

        lifecycleScope.launch {
            pagingData.collectLatest(repoAdapter::submitData)
        }

        lifecycleScope.launch {
            shouldScrollTop.collect { shouldScrollTop ->
                if (shouldScrollTop) searchAnimeRv.scrollToPosition(0)
            }
        }

        lifecycleScope.launch {
            repoAdapter.loadStateFlow.collect { loadState ->
                header.loadState = loadState.mediator
                    ?.refresh
                    ?.takeIf { it is LoadState.Error && repoAdapter.itemCount > 0 }
                    ?: loadState.prepend

                val isListEmpty =
                    loadState.refresh is LoadState.NotLoading && repoAdapter.itemCount == 0
                //show empty list
                emptyList.isVisible = isListEmpty &&
                        loadState.mediator?.refresh is LoadState.Error
                //only show list if refresh succeeds
                searchAnimeRv.isVisible =
                    loadState.source.refresh is LoadState.NotLoading ||
                            loadState.mediator?.refresh is LoadState.NotLoading
                //show loading paperplane for initial load or refresh
                loadingPaperplane.isVisible = loadState.source.refresh is LoadState.Loading
                // Show the retry state if initial load or refresh fails.
                retryButton.isVisible =
                    loadState.source.refresh is LoadState.Error && repoAdapter.itemCount == 0

                // Toast on any error, regardless of whether it came from SearchAnimeMediator or PagingSource
                val errorState = loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error
                errorState?.let {
                    Toast.makeText(requireContext(),
                        "\uD83D\uDE28 Whooops ${it.error}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

    }

    private fun showDetail(id: Int) {
        this.findNavController()
            .navigate(SearchFragmentDirections.actionFeedFragmentToDetailFragment(id))
    }
}