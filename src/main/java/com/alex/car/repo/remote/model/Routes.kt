package com.alex.car.repo.remote.model

import com.google.gson.annotations.SerializedName

class Routes(@SerializedName("summary") var summary: String? = null,
             @SerializedName("bounds") var bounds: Bounds? = null,
             @SerializedName("copyrights") var copyrights: String? = null,
             @SerializedName("routes") var routes: Array<Routes>? = null,
             @SerializedName("waypoint_order") var waypointOrder: Array<String>? = null,
             @SerializedName("legs") var legs: Array<Legs>? = null,
             @SerializedName("warnings") var warnings: Array<String>? = null,
             @SerializedName("overview_polyline") var overviewPolyline: OverviewPolyline? = null)