package com.lahdev.platformscience.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lahdev.platformscience.PlatformScienceApp
import com.lahdev.platformscience.R
import com.lahdev.platformscience.adapter.ShipmentWithDriverAdapter
import com.lahdev.platformscience.contract.AssignedShipmentsContract
import com.lahdev.platformscience.databinding.FragmentAssignedShipmentsBinding
import com.lahdev.platformscience.model.entity.ShipmentWithDriverRelation
import com.lahdev.platformscience.presenter.AssignedShipmentsPresenter

class AssignedShipmentsFragment : Fragment(), AssignedShipmentsContract.View {
    private lateinit var binding: FragmentAssignedShipmentsBinding
    private lateinit var presenter: AssignedShipmentsContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val db = (requireContext().applicationContext as PlatformScienceApp).db

        binding = FragmentAssignedShipmentsBinding.inflate(layoutInflater)

        binding.backBtn.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_assignedShipmentsFragment_to_shipmentsFragment)
        }
        presenter = AssignedShipmentsPresenter(this, db)
        presenter.loadData()

        return binding.root
    }

    override fun showData(shipments: List<ShipmentWithDriverRelation>) {
        binding.shipmentsRV.layoutManager = LinearLayoutManager(requireContext())
        binding.shipmentsRV.adapter = ShipmentWithDriverAdapter(shipments)

        if (shipments.isEmpty()) binding.empty.visibility = View.VISIBLE
        else binding.empty.visibility = View.GONE
    }

}