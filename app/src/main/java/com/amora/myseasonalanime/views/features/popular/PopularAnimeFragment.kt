package com.amora.myseasonalanime.views.features.popular

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.amora.myseasonalanime.R
import com.amora.myseasonalanime.data.source.paging.pagingAdapter.ReposLoadStateAdapter
import com.amora.myseasonalanime.databinding.FragmentPopularAnimeBinding
import com.amora.myseasonalanime.di.Injection
import com.amora.myseasonalanime.utils.enum.Filter
import com.amora.myseasonalanime.utils.enum.Misc
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PopularAnimeFragment : Fragment() {

    private lateinit var binding: FragmentPopularAnimeBinding
    private lateinit var viewModel: PopularAnimeViewModel
    private lateinit var adapter: PopularAnimeAdapter

    private val type = Filter.AIRING
    private val page = Misc.STARTING_PAGE_INDEX.item

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPopularAnimeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        setupAdapter()

        // Initialize first list when fragment created
        lifecycleScope.launch {
            viewModel.topAnime(Filter.AIRING.filter).collectLatest(adapter::submitData)
        }

        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setupAdapter() {
        val viewModelFactory = Injection.provideViewModelFactory(requireContext(), this)
        viewModel = ViewModelProvider(this, viewModelFactory)[PopularAnimeViewModel::class.java]

        adapter = PopularAnimeAdapter(PopularAnimeAdapter.AnimeListener { id -> showDetail(id) })

        binding.popularAnimeList.adapter = adapter.withLoadStateHeaderAndFooter(
            header = ReposLoadStateAdapter { adapter.retry() },
            footer = ReposLoadStateAdapter { adapter.retry() }
        )

        setTitle(type)

        lifecycleScope.launch {
            adapter.loadStateFlow.collect { loadState ->
                val isListEmpty =
                    loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
                //show empty list
                binding.emptyList.isVisible = isListEmpty
                //only show list if refresh succeeds
                binding.popularAnimeList.isVisible =
                    loadState.refresh is LoadState.NotLoading
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
                        "\uD83D\uDE28 Whooops ${it.error}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        binding.retryButton.setOnClickListener {
            adapter.retry()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.popular_menu, menu)
        setTitle(Filter.AIRING)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.airing -> refreshList(Filter.AIRING)
            R.id.upcoming -> refreshList(Filter.UPCOMING)
            R.id.popularity -> refreshList(Filter.BYPOPULARITY)
            R.id.favorite -> refreshList(Filter.FAVORITE)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun refreshList(type: Filter) {
        setTitle(type)
        lifecycleScope.launch {
            when (type) {
                Filter.UPCOMING -> this@PopularAnimeFragment.viewModel.topAnime(Filter.UPCOMING.filter, ).collectLatest(adapter::submitData)
                Filter.BYPOPULARITY -> this@PopularAnimeFragment.viewModel.topAnime(Filter.BYPOPULARITY.filter, ).collectLatest(adapter::submitData)
                Filter.FAVORITE -> this@PopularAnimeFragment.viewModel.topAnime(Filter.FAVORITE.filter, ).collectLatest(adapter::submitData)
                Filter.AIRING -> this@PopularAnimeFragment.viewModel.topAnime(Filter.AIRING.filter, ).collectLatest(adapter::submitData)
            }
        }
    }

    private fun setTitle(filter: Filter) {
        when (filter) {
            Filter.AIRING -> {
                (activity as AppCompatActivity).supportActionBar?.title = "${Filter.AIRING}"
            }
            Filter.UPCOMING -> {
                (activity as AppCompatActivity).supportActionBar?.title = "${Filter.UPCOMING}"
            }
            Filter.BYPOPULARITY -> {
                (activity as AppCompatActivity).supportActionBar?.title = "${Filter.BYPOPULARITY}"
            }
            Filter.FAVORITE -> {
                (activity as AppCompatActivity).supportActionBar?.title = "${Filter.FAVORITE}"
            }
        }
    }

    private fun showDetail(id: Int) {
        this.findNavController()
            .navigate(PopularAnimeFragmentDirections.actionPopularAnimeFragmentToDetailFragment(id))
    }

}