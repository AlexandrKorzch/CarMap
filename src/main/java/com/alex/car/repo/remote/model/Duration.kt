package com.alex.car.repo.remote.model

import com.google.gson.annotations.SerializedName

class Duration(@SerializedName("text") var text: String? = null,
               @SerializedName("value") var value: String? = null)