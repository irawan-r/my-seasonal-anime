package com.amora.myseasonalanime.views.features.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.amora.myseasonalanime.databinding.FragmentPopularAnimeBinding

class PopularAnimeFragment: Fragment() {

    private lateinit var binding: FragmentPopularAnimeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopularAnimeBinding.inflate(inflater)
        return binding.root
    }
}