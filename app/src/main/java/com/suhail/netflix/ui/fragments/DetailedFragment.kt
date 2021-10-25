package com.suhail.netflix.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.suhail.netflix.databinding.FragmentDetailedBinding
import com.suhail.netflix.ui.viewmodel.DetailedViewModelFactory
import com.suhail.netflix.ui.viewmodel.DetailedViewModel


class DetailedFragment : Fragment() {

    val args: DetailedFragmentArgs by navArgs()
    lateinit var viewModel: DetailedViewModel
    private lateinit var binding: FragmentDetailedBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailedBinding.inflate(inflater,container,false)
        val detailedFactory= DetailedViewModelFactory(args.movieItem)
        viewModel=ViewModelProvider(this,detailedFactory).get(DetailedViewModel::class.java)


        setHasOptionsMenu(true)
        viewModel.movieName.observe(requireActivity(), Observer {
            binding.movieNameDetail.text = it
        })

        viewModel.movieDesc.observe(requireActivity(), Observer {
            binding.movieDescription.text = it
        })

        viewModel.smallImage.observe(requireActivity(), Observer {
            Glide.with(requireActivity()).load("https://image.tmdb.org/t/p/w500${it}").optionalCenterCrop().into(binding.smallPosterDetail)
        })

        viewModel.biggerPoster.observe(requireActivity(), Observer {
            Glide.with(requireActivity()).load("https://image.tmdb.org/t/p/w500${it}").into(binding.posterPathDetail)
        })




        return binding.root
        // Inflate the layout for this fragment

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> findNavController().navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }
}