package com.amora.myseasonalanime.views.features.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.amora.myseasonalanime.data.source.paging.pagingAdapter.ReposLoadStateAdapter
import com.amora.myseasonalanime.databinding.FragmentMoreAnimeBinding
import com.amora.myseasonalanime.di.Injection
import com.amora.myseasonalanime.utils.enum.Misc
import com.amora.myseasonalanime.utils.enum.More
import com.amora.myseasonalanime.views.base.viewmodel.ViewModelFactory
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
    }

    private fun setupAdapter() {
        adapter = MoreAnimeAdapter(MoreAnimeAdapter.AnimeListener { id -> showDetail(id) })
        binding.moreAnimeRv.adapter = adapter.withLoadStateHeaderAndFooter(
            header = ReposLoadStateAdapter { adapter.retry() },
            footer = ReposLoadStateAdapter { adapter.retry() }
        )
        binding.retryButton.setOnClickListener {
            adapter.retry()
        }
    }

    private fun setupLayout() {
        val type = MoreAnimeFragmentArgs.fromBundle(requireArguments()).type
        val page = Misc.STARTING_PAGE_INDEX.item
        val viewModelFactory = Injection.provideViewModelFactory(context = requireContext(), owner = this)
        viewModel =
            ViewModelProvider(this, viewModelFactory)[MoreAnimeViewModel::class.java]

        lifecycleScope.launch {
            when (type) {
                AIRING -> this@MoreAnimeFragment.viewModel.animeLoadMore(More.AIRING.type, page)
                    .collectLatest(adapter::submitData)
                UPCOMING -> this@MoreAnimeFragment.viewModel.animeLoadMore(More.UPCOMING.type, page)
                    .collectLatest(adapter::submitData)
            }
        }

        lifecycleScope.launch {
            adapter.loadStateFlow.collect { loadState ->
                val isListEmpty =
                    loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
                //show empty list
                binding.emptyList.isVisible = isListEmpty
                //only show list if refresh succeeds
                binding.moreAnimeRv.isVisible = !isListEmpty
                //show loading paperplane for initial load or refresh
                binding.loadingPaperplane.isVisible = loadState.source.refresh is LoadState.Loading
                // Show the retry state if initial load or refresh fails.
                binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error

                // Toast on any error, regardless of whether it came from SearchAnimeMediator or PagingSource
                val errorState = loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error
                errorState?.let {
                    Toast.makeText(requireContext(),
                        "\uD83D\uDE28 Wooops ${it.error}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun showDetail(id: Int) {
        this.findNavController()
            .navigate(MoreAnimeFragmentDirections.actionMoreAnimeFragmentToDetailFragment(id))
    }

    companion object {
        const val AIRING = "now"
        const val UPCOMING = "upcoming"
    }
}