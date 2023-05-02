package com.lahdev.platformscience.presenter

import com.lahdev.platformscience.contract.AssignedShipmentsContract
import com.lahdev.platformscience.model.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AssignedShipmentsPresenter(
    private val view: AssignedShipmentsContract.View,
    private val db: AppDatabase
) : AssignedShipmentsContract.Presenter {

    override fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            val shipmentsWithDriver = db.shipmentHasDriverDao().getAllShipmentsWithDrivers()

            withContext(Dispatchers.Main) {
                view.showData(shipmentsWithDriver)
            }
        }
    }
}