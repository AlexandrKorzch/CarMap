package com.alex.car.repo.remote.model

import com.google.gson.annotations.SerializedName

class Rout(@SerializedName("geocoded_waypoints") var geocodedWaypoints: Array<GeocodedWaypoints>? = null,
           @SerializedName("status") var status: String? = null,
           @SerializedName("routes") var routes: Array<Routes>? = null)