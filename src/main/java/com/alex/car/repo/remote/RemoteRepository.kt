package com.alex.car.repo.remote

import com.alex.car.repo.remote.api.Api
import com.alex.car.repo.remote.api.RetrofitCreator.initRetrofit
import com.alex.car.repo.remote.api.RetrofitCreator.initServices
import com.alex.car.repo.remote.model.Rout
import io.reactivex.Flowable


object RemoteRepository : RemoteDataSource {

    lateinit var api: Api

    init {
        initServices(initRetrofit())
    }

    override fun getRout(position: String, destination: String, key: String): Flowable<Rout>
            = api.getRout(position, destination, key)

}