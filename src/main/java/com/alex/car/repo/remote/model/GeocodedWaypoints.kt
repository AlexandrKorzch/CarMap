package com.alex.car.repo.remote.model

import com.google.gson.annotations.SerializedName

class GeocodedWaypoints(
        @SerializedName("place_id") var placeId: String? = null,
        @SerializedName("geocoder_status") var geocoderStatus: String? = null,
        @SerializedName("types") var types: Array<String>? = null
)