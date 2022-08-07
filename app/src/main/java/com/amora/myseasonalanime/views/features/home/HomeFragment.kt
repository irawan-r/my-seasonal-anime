package com.amora.myseasonalanime.views.features.home

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.amora.myseasonalanime.R
import com.amora.myseasonalanime.databinding.FragmentHomeBinding
import com.amora.myseasonalanime.di.Injection
import com.amora.myseasonalanime.utils.enum.More
import com.amora.myseasonalanime.utils.gone
import com.amora.myseasonalanime.utils.inVisible
import com.amora.myseasonalanime.utils.visible
import com.amora.myseasonalanime.views.MainActivity

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
        setHasOptionsMenu(true)
        // Preparing the layout
        setupAdapter()
        setupLayout()

        // Set the lifecycleOwner so DataBinding can observe LiveData on SeasonNow Anime
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_options -> {
                val action = HomeFragmentDirections.actionHomeFragmentToAboutFragment()
                findNavController().navigate(action)
                return true
            }
        }
        return false
    }

    private fun setupAdapter() {
        // Send id to detail fragment, so the fragment can get Api with the id
        val airingAdapter = HomeAdapter(HomeAdapter.AnimeListener { id -> showDetail(id) })
        val upComingAdapter = HomeAdapter(HomeAdapter.AnimeListener { id -> showDetail(id) })

        binding.apply {
            animeSeasonNowRv.adapter = airingAdapter
            animeUpcomingSeason.adapter = upComingAdapter

            moreThisSeason.setOnClickListener { showMore(More.AIRING) }
            moreUpcomingSeason.setOnClickListener { showMore(More.UPCOMING) }
        }
    }

    private fun setupLayout() {
        val viewModelFactory =
            Injection.provideViewModelFactory(context = requireContext(), owner = this)
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

        // Setup shimmer
        binding.apply {
            loadingThisSeason.startShimmer()
            loadingUpcomingSeason.startShimmer()

            thisSeasonTitle.gone()
            upcomingSeasonTitle.gone()

            moreUpcomingSeason.gone()
            moreThisSeason.gone()
        }

        viewModel.apply {
            animeSeasonsNow.observe(viewLifecycleOwner) { anime ->
                if (anime?.isNotEmpty() == true) {
                    binding.apply {
                        loadingThisSeason.apply {
                            inVisible()
                            stopShimmer()
                        }
                        thisSeasonTitle.visible()
                        moreThisSeason.visible()
                    }

                }
            }

            upComingSeason.observe(viewLifecycleOwner) { anime ->
                if (anime?.isNotEmpty() == true) {
                    binding.apply {
                        loadingUpcomingSeason.apply {
                            inVisible()
                            stopShimmer()
                        }
                        upcomingSeasonTitle.visible()
                        moreUpcomingSeason.visible()
                    }

                }
            }
        }
    }

    private fun showDetail(id: Int) {
        this.findNavController()
            .navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(id))
    }

    private fun showMore(more: More) {
        this.findNavController()
            .navigate(HomeFragmentDirections.actionHomeFragmentToMoreAnimeFragment(more.type))
    }
}


