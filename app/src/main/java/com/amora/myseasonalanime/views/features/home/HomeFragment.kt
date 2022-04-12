package com.amora.myseasonalanime.views.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.amora.myseasonalanime.databinding.HomeFragmentBinding
import com.amora.myseasonalanime.utils.gone
import com.amora.myseasonalanime.utils.visible
import com.amora.myseasonalanime.views.base.viewmodel.ViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = HomeFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModelFactory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

        // Set the lifecycleOwner so DataBinding can observe LiveData
        binding.animeViewItem.adapter = HomeAdapter()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        // Preparing the layout from repository
        setupLayout()
    }


    private fun setupLayout() {
        binding.apply {
            moreThisSeason.gone()
            loadingThisSeason.visible()
            thisSeasonTitle.gone()
        }

        viewModel.apply {
            thisSeason.observe(viewLifecycleOwner) { anime ->
                if (anime.isNotEmpty()) {
                    binding.loadingThisSeason.gone()
                    binding.thisSeasonTitle.visible()
                    binding.moreThisSeason.visible()
                }
            }
        }
    }


}