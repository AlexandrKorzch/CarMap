package com.alex.car.repo.remote.api

import com.alex.car.repo.remote.api.ApiSettings.ROUT
import com.alex.car.repo.remote.model.Rout
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {

    @GET(ROUT)
    fun getRout(
            @Query(ApiSettings.ORIGIN) position: String,
            @Query(ApiSettings.DESTINATION) destination: String,
            @Query(ApiSettings.KEY) key : String
    ): Flowable<Rout>
}

