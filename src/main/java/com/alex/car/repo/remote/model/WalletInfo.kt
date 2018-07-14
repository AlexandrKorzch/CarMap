package com.alex.car.repo.remote.model

import com.google.gson.annotations.SerializedName

class WalletInfo {

    @SerializedName("title") var title: String = ""
    @SerializedName("image") var image: String = ""
    @SerializedName("description") var description: String = ""
    @SerializedName("weight") var weight: Int = 0

    override fun toString(): String {
        return "WalletInfo(title='$title', image='$image', " +
                "description='$description', weight=$weight)"
    }
}