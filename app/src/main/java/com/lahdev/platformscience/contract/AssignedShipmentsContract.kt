package com.lahdev.platformscience.contract

import com.lahdev.platformscience.model.entity.ShipmentWithDriverRelation

interface AssignedShipmentsContract {
    interface View {
        fun showData(shipments: List<ShipmentWithDriverRelation>)
    }

    interface Presenter {
        fun loadData()
    }
}