package com.amora.myseasonalanime.views.features.detail.characters.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.amora.myseasonalanime.databinding.FragmentDetailCharacterBinding
import com.amora.myseasonalanime.di.Injection
import com.amora.myseasonalanime.utils.gone
import com.amora.myseasonalanime.views.base.viewmodel.ViewModelFactory

class DetailCharaFragment : Fragment() {

    private lateinit var viewModel: DetailCharaViewModel
    private lateinit var binding: FragmentDetailCharacterBinding
    private lateinit var adapter: VoiceActorAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetailCharacterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLayout()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    private fun setupLayout() {
        val id = DetailCharaFragmentArgs.fromBundle(requireArguments()).id
        adapter = VoiceActorAdapter()
        val viewModelFactory = Injection.provideViewModelFactory(requireContext(), this)

        viewModel =
            ViewModelProvider(this, viewModelFactory)[DetailCharaViewModel::class.java].apply {
                setDetailChar(id)
                detailCharaItem.observe(viewLifecycleOwner) { detailChar ->
                    binding.charaDetail = detailChar
                    if (detailChar?.name?.isNotEmpty() == true) {
                        binding.progressBar.gone()
                    }
                }
//                voiceActors.observe(viewLifecycleOwner) { voiceActors ->
//                    binding.apply {
//                        if (voiceActors?.isNotEmpty() == true) {
//                            progressBar.gone()
//                        } else {
//                            Snackbar.make("Too much request, comeback after 4 second")
//                        }
//                    }
//                }
            }

        binding.rvActor.adapter = adapter
    }
}