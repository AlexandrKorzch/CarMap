package com.alex.car.repo.remote.model

import com.google.gson.annotations.SerializedName

class Polyline(@SerializedName("points") var points: String? = null)