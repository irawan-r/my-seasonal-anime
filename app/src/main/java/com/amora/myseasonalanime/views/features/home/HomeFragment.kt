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
        setupLayout()

        // Set the lifecycleOwner so DataBinding can observe LiveData on SeasonNow Anime
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    private fun setupAdapter() {
        // Send id to detail fragment, so the fragment can get Api with the id
        val airingAdapter = HomeAdapter(HomeAdapter.AnimeListener { id -> showDetail(id) })
        val upComingAdapter = HomeAdapter(HomeAdapter.AnimeListener { id -> showDetail(id) })

        binding.apply {
            animeSeasonNowRv.adapter = airingAdapter
            animeUpcomingSeason.adapter = upComingAdapter

            moreThisSeason.setOnClickListener { showMore() }
            moreUpcomingSeason.setOnClickListener { showMore() }
        }
    }

    private fun setupLayout() {
        val viewModelFactory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

        // Setup shimmer
        binding.apply {
            loadingThisSeason.visible()
            loadingUpcomingSeason.visible()

            thisSeasonTitle.gone()
            upcomingSeasonTitle.gone()
        }

        viewModel.apply {
            animeSeasonsNow.observe(viewLifecycleOwner) { anime ->
                if (anime?.isNotEmpty() == true) {
                    binding.loadingThisSeason.gone()
                    binding.thisSeasonTitle.visible()
                }
            }

            upComingSeason.observe(viewLifecycleOwner) { anime ->
                if (anime?.isNotEmpty() == true) {
                    binding.loadingUpcomingSeason.gone()
                    binding.upcomingSeasonTitle.visible()
                }
            }
        }
    }

    private fun showDetail(id: Int) {
        this.findNavController()
            .navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(id))
    }

    private fun showMore() {
        this.findNavController()
            .navigate(HomeFragmentDirections.actionHomeFragmentToMoreAnimeFragment())
    }
}


