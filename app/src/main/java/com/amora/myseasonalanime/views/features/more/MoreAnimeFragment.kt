package com.amora.myseasonalanime.views.features.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
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
        setupView()

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    private fun setupView() {
        lifecycleScope.launch {
            this@MoreAnimeFragment.viewModel.loadData().collectLatest(adapter::submitData)
        }
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
        viewModel = ViewModelProvider(this, viewModelFactory)[MoreAnimeViewModel::class.java]
    }

    private fun showDetail(id: Int) {
        this.findNavController()
            .navigate(MoreAnimeFragmentDirections.actionMoreAnimeFragmentToDetailFragment(id))
    }
}