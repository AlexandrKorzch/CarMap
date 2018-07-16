package com.alex.car.extantions

import com.google.android.gms.maps.model.LatLng


fun LatLng.coordinates(): String {
    return "$latitude,$longitude"
}