package com.alex.car.repo.remote.model

import com.google.gson.annotations.SerializedName

class Bounds(@SerializedName("southwest") var southWest: Southwest? = null,
             @SerializedName("northeast") var northEast: Northeast? = null)