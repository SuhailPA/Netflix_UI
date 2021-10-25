package com.suhail.netflix.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.suhail.netflix.adapters.MoviesAdapter
import com.suhail.netflix.dataClass.Result
import com.suhail.netflix.databinding.FragmentHomeBinding
import com.suhail.netflix.repository.MoviesRepository
import com.suhail.netflix.ui.viewmodel.ViewModelFactory
import com.suhail.netflix.ui.viewmodel.HomeViewModel
import com.suhail.netflix.util.Resource


class HomeFragment : Fragment() {

    lateinit var navController: NavController
    private var binding: FragmentHomeBinding?=null
    lateinit var viewModel: HomeViewModel
    lateinit var movieAdapter:MoviesAdapter
    lateinit var popularMovieAdapter:MoviesAdapter
    lateinit var upcomingMovieAdapter:MoviesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val movieRepository = MoviesRepository()
        val viewModelFactory = ViewModelFactory(movieRepository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
        // Inflate the layout for this fragment


        binding = FragmentHomeBinding.inflate(inflater, container, false)


        navController=findNavController()

        viewModel.latestMovie.observe(viewLifecycleOwner, Observer {
            respoce->
            when(respoce){
                is Resource.Success->{
                    respoce.data.let { movieResponce->
                        Glide.with(requireActivity()).load("https://image.tmdb.org/t/p/w500${movieResponce?.results?.get(0)?.posterPath}").into(
                            binding!!.introImage)
                    }
                }
                is Resource.Error->{
                    respoce.message?.let {
                        Log.i("HomeFragment", it)
                    }
                }
            }
        })
        setUpPopularRecyclerView()
        viewModel.popularMovies.observe(viewLifecycleOwner, Observer { responce ->
            when (responce) {
                is Resource.Success -> {
                    responce.data.let { movieResponce ->
                        popularMovieAdapter.differ.submitList(movieResponce?.results)
                    }
                }
                is Resource.Error -> {
                    responce.message?.let {
                        Log.i("HomeFragment", it)
                    }
                }
            }
        })
        setUpTopRatedMovies()
        viewModel.topRatedMovies.observe(viewLifecycleOwner, Observer { responce->
            when(responce){
                is Resource.Success->{
                    responce.data.let { movieResponce ->
                        movieAdapter.differ.submitList(movieResponce?.results)
                    }
                }
                is Resource.Error->{
                    responce.message?.let {
                        Log.i("HomeFragment", it)
                    }
                }

            }

        })
        setUpComingMovies()
        viewModel.upcomingMovies.observe(viewLifecycleOwner, Observer {
            responce->
            when(responce){
                is Resource.Success->{
                    responce.data.let { movieResponce ->
                        upcomingMovieAdapter.differ.submitList(movieResponce?.results)
                    }
                }
                is Resource.Error->{
                    responce.message?.let{
                        Log.i("HomeFragment",it)
                    }
                }
            }
        })


        movieAdapter.setOnClickListner {
            navigation(it)
        }
        popularMovieAdapter.setOnClickListner {
            navigation(it)
        }
        upcomingMovieAdapter.setOnClickListner {
            navigation(it)
        }

        return binding?.root
    }
    private fun navigation(it:Result){
        val action:NavDirections=HomeFragmentDirections.actionHomeFragmentToDetailedFragment(it)
        navController.navigate(action)
    }

    private fun setUpComingMovies(){
        upcomingMovieAdapter= MoviesAdapter()
        binding?.upcomingMovieList?.apply {
            adapter=upcomingMovieAdapter
            layoutManager=LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)

        }
    }
    private fun setUpTopRatedMovies(){
        movieAdapter= MoviesAdapter()
        binding?.topRatedMovieRecyclerView?.apply {
            adapter=movieAdapter
            layoutManager=LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        }
    }
    private fun setUpPopularRecyclerView() {
       popularMovieAdapter= MoviesAdapter()
        binding?.popularMovieRecyclerView?.apply {
            adapter=popularMovieAdapter
            layoutManager=LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding=null
    }
}