package com.amora.myseasonalanime.views.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.amora.myseasonalanime.databinding.FragmentHomeBinding
import com.amora.myseasonalanime.utils.gone
import com.amora.myseasonalanime.utils.visible
import com.amora.myseasonalanime.views.base.viewmodel.ViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Preparing the layout
        setupAdapter()
        setupLoading()

        // Set the lifecycleOwner so DataBinding can observe LiveData on SeasonNow Anime
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    private fun setupAdapter() {
        // Send id to detail fragment, so the fragment can get Api with the id
        binding.animeSeasonNowRv.adapter =
            HomeAdapter(HomeAdapter.AnimeListener { id -> showDetail(id) })
    }

    private fun setupLoading() {
        val viewModelFactory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
        // Setup shimmer
        binding.apply {
            loadingThisSeason.visible()
            thisSeasonTitle.gone()
        }

        viewModel.apply {
            animeSeasonsNow.observe(viewLifecycleOwner) { anime ->
                if (anime.isNotEmpty()) {
                    binding.loadingThisSeason.gone()
                    binding.thisSeasonTitle.visible()
                }
            }
        }
    }

    private fun showDetail(id: Int) {
        this.findNavController()
            .navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(id))
    }
}


