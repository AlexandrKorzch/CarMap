package com.alex.car.repo.remote.model

import com.google.gson.annotations.SerializedName
import com.google.maps.model.Distance


class Legs(@SerializedName("duration") var duration: Duration? = null,
           @SerializedName("distance") var distance: Distance? = null,
           @SerializedName("end_address") var endAddress: String? = null,
           @SerializedName("start_address") var startAddress: String? = null,
           @SerializedName("end_location") var endLocation: EndLocation? = null,
           @SerializedName("start_location") var startLocation: StartLocation? = null,
           @SerializedName("traffic_speed_entry") var trafficSpeedEntry: Array<String>? = null,
           @SerializedName("via_waypoint") var viaWaypoint: Array<String>? = null,
           @SerializedName("steps") var steps: Array<Steps>? = null)