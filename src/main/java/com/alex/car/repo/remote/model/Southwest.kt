package com.alex.car.repo.remote.model

import com.google.gson.annotations.SerializedName

class Southwest(@SerializedName("lat") var lat: String? = null,
                @SerializedName("lng") var lng: String? = null)