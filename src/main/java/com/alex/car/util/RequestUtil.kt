package com.alex.car.util

import com.alex.car.extantions.coordinates
import com.alex.car.repo.Repository
import com.alex.car.repo.remote.model.Rout
import com.google.android.gms.maps.model.LatLng
import io.reactivex.Flowable

fun getRoutFlowables(repository: Repository, startPlace: LatLng?,
                             intermediatePlaces: ArrayList<LatLng>,
                             endPlace: LatLng?): ArrayList<Flowable<Rout>> {
    val points = preparePointList(startPlace, intermediatePlaces, endPlace)
    val flowables = ArrayList<Flowable<Rout>>()
    points.forEachIndexed { index, _ ->
        if (index <= points.size - 2) {
            flowables.add(repository.getRout(points[index].coordinates(),
                    points[index + 1].coordinates()))
        }
    }
    return flowables
}

private fun preparePointList(startPlace: LatLng?, intermediatePlaces: ArrayList<LatLng>,
                             endPlace: LatLng?): ArrayList<LatLng> {
    return ArrayList<LatLng>().apply {
        add(startPlace!!)
        addAll(intermediatePlaces)
        add(endPlace!!)
    }
}