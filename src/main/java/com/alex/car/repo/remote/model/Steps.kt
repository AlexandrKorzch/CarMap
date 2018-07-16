package com.alex.car.repo.remote.model

import com.google.gson.annotations.SerializedName

class Steps(@SerializedName("html_instructions") var htmlInstructions: String? = null,
            @SerializedName("travel_mode") var travelMode: String? = null,
            @SerializedName("duration") var duration: Duration? = null,
            @SerializedName("distance") var distance: Distance? = null,
            @SerializedName("polyline") var polyline: Polyline? = null,
            @SerializedName("end_location") var endLocation: EndLocation? = null,
            @SerializedName("start_location") var startLocation: StartLocation? = null)