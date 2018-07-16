package com.alex.car.repo.remote.model

import com.google.gson.annotations.SerializedName

class EndLocation(@SerializedName("lat") var lat: String? = null,
                  @SerializedName("lng") var lng: String? = null){

    override fun toString(): String {
        return "EndLocation(lat=$lat, lng=$lng)"
    }
}

