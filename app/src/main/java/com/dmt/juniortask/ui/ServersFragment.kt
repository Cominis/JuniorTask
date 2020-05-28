package com.dmt.juniortask.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dmt.juniortask.AppApplication
import com.dmt.juniortask.R
import com.dmt.juniortask.databinding.FragmentServersBinding
import com.dmt.juniortask.repository.AppRepository
import com.dmt.juniortask.viewmodels.MainViewModel
import com.dmt.juniortask.viewmodels.ServersViewModel
import com.dmt.juniortask.viewmodels.factories.ServersViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ServersFragment : DaggerFragment() {

    lateinit var viewModel: ServersViewModel

    @Inject
    lateinit var repo: AppRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentServersBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_servers, container, false
        )

        ViewModelProvider(requireActivity()).get(MainViewModel::class.java).setTitle(resources.getString(R.string.servers))

        val token = (requireActivity().application as AppApplication).userManager.token
        val factory = ServersViewModelFactory(repo, token)
        viewModel = ViewModelProvider(this, factory).get(ServersViewModel::class.java)

        val adapter = ServersAdapter(ServerListener {serverId ->
            viewModel.onServerItemClicked(serverId)
        })

        binding.serversList.adapter = adapter

        viewModel.servers.observe(viewLifecycleOwner, Observer {
            it?.let {serversList ->
                adapter.submitList(serversList)
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navigateToServerDetails.observe(viewLifecycleOwner, Observer { serverId ->
            serverId?.let {
                val action = ServersFragmentDirections
                    .actionServersFragmentToServerDetailFragment(serverId)
                this.findNavController().navigate(action)
                viewModel.onServerDetailsNavigated()
            }
        })
    }
}
