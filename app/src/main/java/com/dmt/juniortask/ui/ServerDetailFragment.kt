package com.dmt.juniortask.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.dmt.juniortask.R
import com.dmt.juniortask.databinding.FragmentServerDetailsBinding
import com.dmt.juniortask.repository.AppRepository
import com.dmt.juniortask.viewmodels.MainViewModel
import com.dmt.juniortask.viewmodels.ServerDetailViewModel
import com.dmt.juniortask.viewmodels.factories.ServerDetailViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ServerDetailFragment : DaggerFragment() {

    @Inject
    lateinit var repo: AppRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentServerDetailsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_server_details, container, false
        )

        val arguments = ServerDetailFragmentArgs.fromBundle(requireArguments())
        val factory = ServerDetailViewModelFactory(arguments.serverId, repo)
        val viewModel = ViewModelProvider(this, factory).get(ServerDetailViewModel::class.java)


        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewModelProvider(requireActivity()).get(MainViewModel::class.java).setTitle(resources.getString(R.string.details))
    }
}
