package com.amora.myseasonalanime.views.features.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.amora.myseasonalanime.data.source.remote.response.animenow.AnimeListResponse
import com.amora.myseasonalanime.databinding.FragmentMoreAnimeBinding
import com.amora.myseasonalanime.views.base.viewmodel.ViewModelFactory
import kotlinx.coroutines.flow.Flow
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
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadData().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun setupAdapter() {
        adapter = MoreAnimeAdapter(MoreAnimeAdapter.AnimeListener { id -> showDetail(id) })
        binding.moreAnimeRv.adapter = adapter
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