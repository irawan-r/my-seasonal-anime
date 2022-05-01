package com.amora.myseasonalanime.views.features.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.amora.myseasonalanime.databinding.FragmentMoreAnimeBinding
import com.amora.myseasonalanime.views.base.viewmodel.ViewModelFactory
import com.amora.myseasonalanime.views.features.more.loadstate.ReposLoadStateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MoreAnimeFragment : Fragment() {
    private lateinit var binding: FragmentMoreAnimeBinding
    private lateinit var viewModel: MoreAnimeViewModel
    private lateinit var adapter: MoreAnimeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMoreAnimeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupAdapter()
        setupLayout()

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    private fun setupAdapter() {
        adapter = MoreAnimeAdapter(MoreAnimeAdapter.AnimeListener { id -> showDetail(id) })
        binding.moreAnimeRv.adapter = adapter.withLoadStateHeaderAndFooter(
            header = ReposLoadStateAdapter { adapter.retry() },
            footer = ReposLoadStateAdapter { adapter.retry() }
        )
    }

    private fun setupLayout() {
        val viewModelFactory = ViewModelFactory.getInstance()
        viewModel =
            ViewModelProvider(this, viewModelFactory)[MoreAnimeViewModel::class.java]

        lifecycleScope.launch {
            this@MoreAnimeFragment.viewModel.airingLoadMore().collectLatest(adapter::submitData)
        }

        lifecycleScope.launch {
            adapter.loadStateFlow.collect { loadState ->
                val isListEmpty = loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
                //show empty list
                binding.emptyList.isVisible = isListEmpty
                //only show list if refresh succeeds
                binding.moreAnimeRv.isVisible = !isListEmpty
                //show loading paperplane for initial load or refresh
                binding.loadingPaperplane.isVisible = loadState.source.refresh is LoadState.Loading
                // Show the retry state if initial load or refresh fails.
                binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error
            }
        }
    }

    private fun showDetail(id: Int) {
        this.findNavController()
            .navigate(MoreAnimeFragmentDirections.actionMoreAnimeFragmentToDetailFragment(id))
    }
}