package com.amora.myseasonalanime.views.features.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.amora.myseasonalanime.databinding.FragmentFeedAnimeBinding
import com.amora.myseasonalanime.databinding.FragmentPopularAnimeBinding

class FeedFragment: Fragment() {

    private lateinit var viewModel: FeedViewModel
    private lateinit var binding: FragmentFeedAnimeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFeedAnimeBinding.inflate(inflater)
        return binding.root
    }
}